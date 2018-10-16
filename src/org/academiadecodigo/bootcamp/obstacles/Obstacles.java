package org.academiadecodigo.bootcamp.obstacles;

import org.academiadecodigo.bootcamp.grid.Grid;
import org.academiadecodigo.bootcamp.grid.GridDirection;
import org.academiadecodigo.bootcamp.grid.position.GridPosition;

public abstract class Obstacles{

    private GridPosition gridPosition;
    private Grid grid;
    private GridDirection gridDirection;
    private GridDirection currentDirection;
    private int speed;
    //private ObstaclesType;
    //private CollisionDetector collisionDetector;
    //private int directionChangeLevel = 8;


    //Constructors

    public Obstacles(int col, int row, Grid grid, int speed) {

        this.gridPosition = grid.makeGridPosition(col, row);

        this.speed = speed;

        currentDirection = GridDirection.DOWN;
    }

    public Obstacles(Grid grid, int speed) {

        this.gridPosition = grid.makeGridPosition();


    }

    //Getters&Setters

    public void setGrid(Grid grid) {

        this.grid = grid;
    }

    public GridPosition gridPosition() {

        return gridPosition;
    }

    //Methods

    abstract public void move();


    public GridDirection chooseDirection() {

        GridDirection newDirection = currentDirection;

        return newDirection;

    }

    /**
     * Accelerates the car towards a specific direction,
     * as long as we have not bumped against the wall,
     * in which case we bounce back
     *
     * @param direction the direction to which accelerate
     * @param speed     the speed to accelerate at
     */
    public void accelerate(GridDirection direction, int speed) {


        GridDirection newDirection = direction;
        if (isHittingWall()) {
           newDirection = direction.oppositeDirection();
        }

        this.currentDirection = newDirection;

        for (int i = 0; i < speed; i++) {
            gridPosition().moveInDirection(newDirection, 1);
        }

    }

    public boolean isHittingWall() {
        switch (currentDirection) {
            case LEFT:
                if (getGridPosition().getCol() == 0) {
                    return true;
                }
                break;
            case RIGHT:
                if (getGridPosition().getCol() == grid.getCols() - 1) {
                    return true;
                }
                break;
            case UP:
                if (getGridPosition().getRow() == 0) {
                    return true;
                }
                break;
            case DOWN:
                if (getGridPosition().getRow() == grid.getRows() - 1) {
                    return true;
                }
        }

        return false;

    }


    //Getters&Setters


    public int getSpeed() {
        return speed;
    }

    public GridPosition getGridPosition() {
        return gridPosition;
    }


    public GridDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setGridPosition(GridPosition gridPosition) {
        this.gridPosition = gridPosition;
    }

    public void setGridDirection(GridDirection gridDirection) {
        this.gridDirection = gridDirection;
    }

    public void setCurrentDirection(GridDirection currentDirection) {
        this.currentDirection = currentDirection;
    }
}
