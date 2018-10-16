package org.academiadecodigo.bootcamp.levels;

import org.academiadecodigo.bootcamp.CollisionDetector;
import org.academiadecodigo.bootcamp.Random;
import org.academiadecodigo.bootcamp.grid.Grid;
import org.academiadecodigo.bootcamp.grid.GridColor;
import org.academiadecodigo.bootcamp.obstacles.LaserCut;
import org.academiadecodigo.bootcamp.obstacles.Obstacles;
import org.academiadecodigo.bootcamp.obstacles.Wall;
import org.academiadecodigo.bootcamp.player.Player;

public class Level {

    private Grid grid;
    private Obstacles[] obstacles; //level
    private Wall[] walls; //level
    private int numberOfLasers = 8; //level
    private CollisionDetector collisionDetector; //level
    private int numberOfWalls = 3; //level
    private int difficultyLevel = 2; //level
    private int speed;
    private Player player;


    public Level(Grid grid, Player player) {
        this.speed = 1;
        this.grid = grid;
        this.player = player;
    }

    public void start() {

        grid.makeGridPosition(grid.getCols() -1, (grid.getRows()/ 2)).setColor(GridColor.GREEN, true); // Change the color of the door //level
        grid.makeGridPosition(grid.getCols() -1, (grid.getRows()/ 2)).setColor(GridColor.GREEN, true); //level

        obstacles = new LaserCut[numberOfLasers]; //level

        //walls = new Wall[numberOfWalls * grid.getRows()];
        walls = new Wall[numberOfWalls * grid.getRows() + 50]; //level

        int counter = 0;

        int col = -3;

        int c = 0;

        while (c < numberOfWalls ) { // creates multiple walls

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
        for(int i = 0; i < numberOfLasers; i++) {

            col = ((grid.getCols() / numberOfLasers) * (i + 1) - 2);

            if (i %2 == 0) {
                obstacles[i] = new LaserCut(col, 0, grid, speed);
                obstacles[i].setGrid(grid);

            }else {
                obstacles[i] = new LaserCut(col, grid.getRows()-1, grid, speed);
                obstacles[i].setGrid(grid);
            }


        }

        collisionDetector = new CollisionDetector(obstacles, player);


    }

    public boolean collisionCheck() {

        if (collisionDetector.check()) { // make game stops when hit

            //TODO add closeGameScreen
            return true;
        }
        return false;
    }

    public void moveAll() throws InterruptedException{

        while(true) {

            Thread.sleep(10);

            for (int i = 0; i < obstacles.length; i++) {

                obstacles[i].move();
            }
        }
    }
}
