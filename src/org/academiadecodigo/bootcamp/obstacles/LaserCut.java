package org.academiadecodigo.bootcamp.obstacles;

import org.academiadecodigo.bootcamp.grid.GridDirection;
import org.academiadecodigo.bootcamp.grid.Grid;

public class LaserCut extends Obstacles{

    private int numberOfLasers;


    //private CollisionDetector collisionDetector;


    //Constructor

    public LaserCut(int col, int row, Grid grid, int speed) {

        super(col, row, grid, speed);

        setCurrentDirection(GridDirection.DOWN);

    }

    //Methods

    public void move() {

        accelerate(chooseDirection(), getSpeed());

    }


    //Getters&Setters


}
