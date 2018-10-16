package org.academiadecodigo.bootcamp.player;

//import org.academiadecodigo.bootcamp.CollisionDetector;
import org.academiadecodigo.bootcamp.grid.Grid;
import org.academiadecodigo.bootcamp.grid.GridDirection;
import org.academiadecodigo.bootcamp.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;

public class Player implements KeyboardHandler {

    private GridPosition gridPosition;
    private Grid grid;
    private boolean isDead;
    //private CollisionDetector collisionDetector;
    private Keyboard keyboard;
    private GridDirection currentDirection;
    public boolean stopped;


    //Constructor

    public Player(GridPosition gridPosition) {

        this.gridPosition = gridPosition;
        this.isDead = false;
        this.currentDirection = GridDirection.NODIRECTION;
        this.stopped = true;

        keyboard = new Keyboard(this);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(left);

        KeyboardEvent leftstop = new KeyboardEvent();
        leftstop.setKey(KeyboardEvent.KEY_LEFT);
        leftstop.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(leftstop);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
        keyboard.addEventListener(right);

        KeyboardEvent rightstop = new KeyboardEvent();
        rightstop.setKey(KeyboardEvent.KEY_RIGHT);
        rightstop.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        keyboard.addEventListener(rightstop);

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


    }

    //Methods
    public void setGrid(Grid grid) {

        this.grid = grid;
    }

    public GridPosition getGridPosition() {

        return gridPosition;
    }

    /*public void setCollisionDetector(CollisionDetector collisionDetector) {

        this.collisionDetector = collisionDetector;
    }*/


    public boolean isDead() {

        return this.isDead;
    }

    public void setDead() {

        this.isDead = true;
    }

    public void move() {

        if (stopped) {
            return;
        }
        getGridPosition().moveInDirection(currentDirection, 1);

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_LEFT:

                currentDirection = GridDirection.LEFT;
                break;
            case KeyboardEvent.KEY_RIGHT:

                currentDirection = GridDirection.RIGHT;
                break;
            case KeyboardEvent.KEY_UP:

                currentDirection = GridDirection.UP;
                break;
            case KeyboardEvent.KEY_DOWN:

                currentDirection = GridDirection.DOWN;
                break;
            default:

                System.out.println("Error 1!");
                break;
        }

        this.stopped = false;
    }


    public void keyReleased(KeyboardEvent keyboardEvent) {

        this.stopped = true;
    }
}
