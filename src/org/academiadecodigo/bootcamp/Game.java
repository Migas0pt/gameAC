package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.grid.Grid;
import org.academiadecodigo.bootcamp.grid.GridColor;
import org.academiadecodigo.bootcamp.home.*;
import org.academiadecodigo.bootcamp.levels.Level;
import org.academiadecodigo.bootcamp.player.Player;
import org.academiadecodigo.bootcamp.simplegfx.SimpleGfxGrid;
import org.academiadecodigo.bootcamp.obstacles.*;


public class Game{

    private Grid grid;
    private Grid homeGrid;
    private Player player;
    private User user;
    private Obstacles[] obstacles; //level
    private Wall[] walls; //level
    private int numberOfLasers = 8; //level
    //private int speed = 1; //level
    private CollisionDetector collisionDetector; //level
    private int numberOfWalls = 3; //level
    private int difficultyLevel = 2; //level
    private boolean activatedHomeScreen;
    private boolean activatedGameScreen;
    private Level currentLevel;


    //Constructor

    public Game(int col, int row) {

       this.activatedHomeScreen = true;
       this.activatedGameScreen = false;
       grid = new SimpleGfxGrid(col, row);
       homeGrid = new SimpleGfxGrid(col, row);

    }

    public void init() throws InterruptedException{

        // TODO: we are creating User and Home Options
        // TODO: New classes: User and Home Options
        // TODO: setcolor needs as argument the filled option


        SimpleGfxGrid userGrid = new SimpleGfxGrid(100, 50);
        user = new User(userGrid.makeGridPosition(10,5), userGrid);
        user.getGridPosition().setColor(GridColor.GREEN,true);

        //while the user is not ready to open the game it won't open the game
        while(!user.allowStartGame) {

            openHomeScreen();
        }

        //if the user allows us to open the game we do it
        if (user.allowStartGame) {

            openGameScreen();
            closeHomeScreen();
            start();
        }
    }

    public void start() throws InterruptedException {

        grid.init();

        player = new Player(grid.makeGridPosition(0, grid.getRows() / 2));

        currentLevel = new Level(grid, player);

        currentLevel.start();

        while (true) {

            // Pause for a while
            Thread.sleep(20);

            moveAll();

            if(currentLevel.collisionCheck()) {
                break;
            }

            // Set the position of the door and make the game stop
            if (canWin()) {
                System.out.println("WIN");
                //TODO add closeGameScreen
                break;
            }

        }
    }

    public boolean canWin() {
        if (player.getGridPosition().getCol() == grid.getCols() -1 && player.getGridPosition().getRow() == (grid.getRows() / 2)-1 || player.getGridPosition().getCol() == grid.getCols() -1 && player.getGridPosition().getRow() == (grid.getRows() / 2)) {
            return true;
        }
        return false;
    }

    public void moveAll() throws InterruptedException {

        player.move();

        currentLevel.moveAll();


    }

    public void openHomeScreen(){

        this.activatedHomeScreen = true;
        homeGrid.init();
    }

    public void openGameScreen(){
        this.activatedGameScreen = true;
    }

    public void closeHomeScreen() {
        this.activatedHomeScreen = false;
    }

    public void closeGameScreen() {
        this.activatedGameScreen = false;
    }
}
