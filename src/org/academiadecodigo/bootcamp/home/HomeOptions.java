package org.academiadecodigo.bootcamp.home;

import org.academiadecodigo.bootcamp.grid.position.GridPosition;
import org.academiadecodigo.bootcamp.simplegfx.SimpleGfxGrid;

public class HomeOptions{

        public GridPosition gridPosition;
        private SimpleGfxGrid grid;

        //Constructor

        public HomeOptions(GridPosition gridPosition) {

            this.gridPosition = gridPosition;
        }

        //Methods
        public void setGrid(SimpleGfxGrid grid) {

            this.grid = grid;
        }

        public GridPosition getGridPosition() {

            return gridPosition;
        }

        public void end() {


            gridPosition.hideRectangle();
            grid.end();
        }



        public void action() {



        }


}
