package org.academiadecodigo.bootcamp.simplegfx;

import org.academiadecodigo.bootcamp.grid.GridColor;
import org.academiadecodigo.bootcamp.grid.GridDirection;
import org.academiadecodigo.bootcamp.grid.position.AbstractGridPosition;
import org.academiadecodigo.bootcamp.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/**
 * Simple graphics position
 */
public class SimpleGfxGridPosition extends AbstractGridPosition {

    private Rectangle rectangle;
    private SimpleGfxGrid simpleGfxGrid;
    private Picture picture;

    /**
     * Simple graphics position constructor
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(SimpleGfxGrid grid){

        super((int) (Math.random() * grid.getCols()), (int) (Math.random() * grid.getRows()), grid);
        simpleGfxGrid = grid;
        this.rectangle = new Rectangle(grid.columnToX(super.getCol()),grid.rowToY(super.getRow()),grid.getCellSize(),grid.getCellSize());
        showRectangle();

    }

    /**
     * Simple graphics position constructor
     * @param col position column
     * @param row position row
     * @param grid Simple graphics grid
     */
    public SimpleGfxGridPosition(int col, int row, SimpleGfxGrid grid){

        super(col, row, grid);

        simpleGfxGrid = grid;
        this.rectangle = new Rectangle(grid.columnToX(super.getCol()),grid.rowToY(super.getRow()),grid.getCellSize(),grid.getCellSize());

        rectangle.setColor(Color.BLUE);

        showRectangle();
    }

    public SimpleGfxGridPosition(int col, int row, SimpleGfxGrid grid, Picture picture){

        super(col, row, grid);

        simpleGfxGrid = grid;
        this.picture = picture;

        showPicture();
    }

    /**
     * @see GridPosition#showRectangle()
     */
    @Override
    public void showRectangle() {

        rectangle.draw();
    }

    /**
     * @see GridPosition#hideRectangle()
     */
    @Override
    public void hideRectangle() {

        rectangle.delete();
    }


    public void showPicture() {

        picture.draw();
    }


    public void hidePicture() {

        picture.delete();
    }

    /**
     * @see GridPosition#moveInDirection(GridDirection, int)
     */
    @Override
    public void moveInDirection(GridDirection direction, int distance) {

        int posInX = simpleGfxGrid.columnToX(getCol());
        int posInY = simpleGfxGrid.rowToY(getRow());
        super.moveInDirection(direction,distance);
        rectangle.translate(simpleGfxGrid.columnToX(getCol())-posInX,simpleGfxGrid.rowToY(getRow())-posInY);
    }


    @Override
    public void setColor(GridColor color, Boolean filled) {

        super.setColor(color, filled);
        rectangle.setColor(SimpleGfxColorMapper.getColor(color));
        if(filled){
            rectangle.fill();
        }

    }


}
