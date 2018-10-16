package org.academiadecodigo.bootcamp.simplegfx;

import org.academiadecodigo.bootcamp.grid.Grid;
import org.academiadecodigo.bootcamp.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class SimpleGfxGrid implements Grid {

    public static final int PADDING = 10;
    private Rectangle grid;
    private Picture picture;
    private int cols;
    private int rows;
    private int sizeOfCell;
    private int heightOfGrid;
    private int widthOfGrid;

    public SimpleGfxGrid(int cols, int rows){

        this.cols = cols;
        this.rows = rows;
        sizeOfCell = getCellSize();
        heightOfGrid = sizeOfCell * rows;
        widthOfGrid = sizeOfCell * cols;
        grid = new Rectangle(PADDING,PADDING,getWidth(),getHeight());
    }

    public SimpleGfxGrid(int cols, int rows, Picture picture){

        this.cols = cols;
        this.rows = rows;
        sizeOfCell = getCellSize();
        heightOfGrid = sizeOfCell * rows;
        widthOfGrid = sizeOfCell * cols;
        picture = new Picture(100,50);
    }

    /**
     * @see Grid#init()
     */
    @Override
    public void init() {

        grid.setColor(Color.GREEN);
        grid.draw();
    }

    @Override
    public void end() {
        grid.delete();
    }

    /**
     * @see Grid#getCols()
     */
    @Override
    public int getCols() {

        return cols;
    }

    /**
     * @see Grid#getRows()
     */
    @Override
    public int getRows() {

        return rows;
    }

    /**
     * Obtains the width of the grid in pixels
     * @return the width of the grid
     */
    public int getWidth() {

        return widthOfGrid;
    }

    /**
     * Obtains the height of the grid in pixels
     * @return the height of the grid
     */
    public int getHeight() {

        return heightOfGrid;
    }

    /**
     * Obtains the grid X position in the SimpleGFX canvas
     * @return the x position of the grid
     */
    public int getX() {

        return grid.getX();
    }

    /**
     * Obtains the grid Y position in the SimpleGFX canvas
     * @return the y position of the grid
     */
    public int getY() {

        return grid.getY();
    }

    /**
     * Obtains the pixel width and height of a grid position
     * @return
     */
    public int getCellSize() {

        return 10;
    }

    /**
     * @see Grid#makeGridPosition()
     */
    @Override
    public GridPosition makeGridPosition() {

        return new SimpleGfxGridPosition(this);
    }

    /**
     * @see Grid#makeGridPosition(int, int)
     */
    @Override
    public GridPosition makeGridPosition(int col, int row) {

        return new SimpleGfxGridPosition(col,row,this);
    }



    public GridPosition makeGridPosition(int col, int row, Picture picture) {

        return new SimpleGfxGridPosition(col,row,this, picture);
    }

    /**
     * Auxiliary method to compute the y value that corresponds to a specific row
     * @param row index
     * @return y pixel value
     */
    public int rowToY(int row) {

        return getCellSize() * row + PADDING;
    }

    /**
     * Auxiliary method to compute the x value that corresponds to a specific column
     * @param column index
     * @return x pixel value
     */
    public int columnToX(int column) {

        return getCellSize() * column + PADDING;
    }

    public Rectangle getGrid() {
        return grid;
    }


}
