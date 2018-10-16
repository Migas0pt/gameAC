package org.academiadecodigo.bootcamp.home;

import org.academiadecodigo.bootcamp.grid.GridColor;
import org.academiadecodigo.bootcamp.grid.GridDirection;
import org.academiadecodigo.bootcamp.grid.position.GridPosition;
import org.academiadecodigo.bootcamp.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class User implements KeyboardHandler{

    private GridPosition gridPosition;
    private SimpleGfxGrid grid;
    private Keyboard keyboard;
    private boolean gameWindow;
    private HomeOptions[] homeOptions;
    private int optionsSpace;
    public boolean allowStartGame;

    //Constructor

    public User(GridPosition gridPosition,SimpleGfxGrid grid) {

        this.gridPosition = gridPosition;
        this.gameWindow = false;
        this.grid = grid;
        this.optionsSpace = 4;
        this.allowStartGame = false;

        homeOptions = new HomeOptions[3];
        Picture[] pictures = new Picture[3];
        pictures[0] = new Picture(10,5, "startGame.jpg");
        pictures[1] = new Picture(10,9,"instructions.png");
        pictures[2] = new Picture(10, 13, "exit.png");

        pictures[0].grow(-800/2,-400/2);
        //System.out.println(pictures[0].getHeight());
        //System.out.println(pictures[0].getWidth());

        //create the options in the HomeMenu
        for (int i = 0; i < homeOptions.length; i++) {

            //homeOptions[i] = new HomeOptions(grid.makeGridPosition(10,optionsSpace*i+5,pictures[i])); //creates the options with images
            homeOptions[i] = new HomeOptions(grid.makeGridPosition(10,optionsSpace*i+5)); //for rectangle options
            homeOptions[i].setGrid(grid);
            homeOptions[i].getGridPosition().setColor(GridColor.BLUE,false); //creates the options with rectangles
        }

        keyboard = new Keyboard(this);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(up);

        KeyboardEvent upstop = new KeyboardEvent();
        upstop.setKey(KeyboardEvent.KEY_UP);
        upstop.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(upstop);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(down);

        KeyboardEvent downstop = new KeyboardEvent();
        downstop.setKey(KeyboardEvent.KEY_DOWN);
        downstop.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(downstop);

        KeyboardEvent enter = new KeyboardEvent();
        enter.setKey(KeyboardEvent.KEY_SPACE);
        enter.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(enter);

    }

    //Methods
    public void setGrid(SimpleGfxGrid grid) {

        this.grid = grid;
    }

    public GridPosition getGridPosition() {

        return gridPosition;
    }

    //setup what we want for the game to do on each Home options
    public void action(int arrayIndex) {

        switch(arrayIndex) {

            case 0:

                allowStartGame = true;

                for (int i = 0; i < homeOptions.length; i++) {
                    homeOptions[i].end();
                }

                gridPosition.hideRectangle();
                grid.end();
                break;

            case 1:

                break;

            case 2:

                System.exit(0);
                break;

        }
    }

    public boolean isAtBottom() {

        if (gridPosition.equals(homeOptions[(homeOptions.length) - 1].getGridPosition())) {
            return true;
        }
        return false;
    }

    public boolean isAtTop() {

        if(gridPosition.equals(homeOptions[0].getGridPosition())) {
            return true;
        }
        return false;
    }

    private void moveUp(){

        if(isAtTop()) {
            return;
        }
        gridPosition.moveInDirection(GridDirection.UP, optionsSpace);
        gridPosition.setColor(GridColor.GREEN,true);

    }

    private void moveDown(){

        if(isAtBottom()) {
            return;
        }
        gridPosition.moveInDirection(GridDirection.DOWN, optionsSpace);
        gridPosition.setColor(GridColor.GREEN,true);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

            switch (keyboardEvent.getKey()) {

                case KeyboardEvent.KEY_UP:
                    if(!allowStartGame) {
                        moveUp();
                    }
                    break;
                case KeyboardEvent.KEY_DOWN:
                    if(!allowStartGame) {
                        moveDown();
                    }
                    break;

                case KeyboardEvent.KEY_SPACE:
                    if(!allowStartGame) {
                        action((getGridPosition().getRow() - 5) / optionsSpace);
                    }
                    break;
                default:

                    System.out.println("Error 1!");
                    break;
            }
    }


    public void keyReleased(KeyboardEvent keyboardEvent) {


    }

    public void setGameWindow(){

        //System.out.println("Option true!");
        this.gameWindow = true;

    }


}
