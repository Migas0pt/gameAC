package org.academiadecodigo.bootcamp.obstacles;

import org.academiadecodigo.bootcamp.grid.Grid;
import org.academiadecodigo.bootcamp.grid.GridColor;
import org.academiadecodigo.bootcamp.grid.position.GridPosition;

public class Wall extends Obstacles{

    private GridPosition gridPosition;
    private Grid grid;
    private Wall[] walls;
    private int numberOfWalls;
    private int difficultyLevel;//hardship level from 1 to 5


    //Constructor

    public Wall(int col, int row, Grid grid) {

        super(col, row, grid, 0);

        this.gridPosition = grid.makeGridPosition(col, row);
        gridPosition.setColor(GridColor.BLUE, true);

    }



    @Override
    public void move() {

    }

    public void wall1 (int numb) {

    }









    public void setGridPosition(GridPosition gridPosition) {
        this.gridPosition = gridPosition;
    }
}





