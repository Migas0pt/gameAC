package org.academiadecodigo.bootcamp.levels;

import org.academiadecodigo.bootcamp.CollisionDetector;
import org.academiadecodigo.bootcamp.Random;
import org.academiadecodigo.bootcamp.grid.Grid;
import org.academiadecodigo.bootcamp.grid.GridColor;
import org.academiadecodigo.bootcamp.obstacles.LaserCut;
import org.academiadecodigo.bootcamp.obstacles.Obstacles;
import org.academiadecodigo.bootcamp.obstacles.Wall;
import org.academiadecodigo.bootcamp.player.Player;

public class Level2 {

    private Grid grid;
    private Obstacles[] obstacles; //level
    private Wall[] walls; //level
    private int numberOfLasers = 8; //level
    private CollisionDetector collisionDetector; //level
    private int numberOfWalls = 3; //level
    private int speed;
    private Player player;
    private boolean isOver;



    public Level2(Grid grid, Player player) {
        this.speed = 1;
        this.grid = grid;
        this.player = player;
        this.isOver = false;
    }

    public void start() throws InterruptedException {


        grid.makeGridPosition(grid.getCols() - 1, (grid.getRows() / 2)).setColor(GridColor.GREEN, true); // Change the color of the door //level
        grid.makeGridPosition(grid.getCols() - 1, (grid.getRows() / 2) - 1).setColor(GridColor.GREEN, true);

        obstacles = new LaserCut[numberOfLasers]; //level

        collisionDetector = new CollisionDetector(obstacles, player);

        //walls = new Wall[numberOfWalls * grid.getRows()];
        walls = new Wall[numberOfWalls * grid.getRows() + 50]; //level

        int counter = 0;

        int col = -3;

        int c = 0;

        while (c < numberOfWalls) { // creates multiple walls

            col = col + (grid.getCols() / numberOfWalls);

            counter++;

            for (int d = 0; d < grid.getRows(); d++) { // fills multiple rows each time

                if (Random.getRandom(5) < 1) {
                    continue;
                }

                walls[counter] = new Wall(col, d, grid);

                walls[counter].setGrid(grid);

                counter++;

            }

            c++;
        }

        //set the laser initial position
        for (int i = 0; i < numberOfLasers; i++) {

            col = ((grid.getCols() / numberOfLasers) * (i + 1) - 2);

            if (i % 2 == 0) {
                obstacles[i] = new LaserCut(col, 0, grid, speed);
                obstacles[i].setGrid(grid);

            } else {
                obstacles[i] = new LaserCut(col, grid.getRows() - 1, grid, speed);
                obstacles[i].setGrid(grid);
            }


        }

        System.out.println(player.getGridPosition());


        moveAll();

    }





    public void moveAll() throws InterruptedException{

        while(!isOver) {
            //System.out.println(player.getGridPosition());

            Thread.sleep(100);

            player.move();

            for (int i = 0; i < obstacles.length; i++) {

                obstacles[i].move();
            }

            if(collisionDetector.check()) {
                isOver = true;
                break;
            }

            // Set the position of the door and make the game stop
            if (canWin()) {
                isOver = true;
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

    public boolean isOver() {
        return isOver;
    }
}
