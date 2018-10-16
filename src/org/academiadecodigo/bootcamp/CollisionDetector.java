package org.academiadecodigo.bootcamp;

import org.academiadecodigo.bootcamp.obstacles.Obstacles;
import org.academiadecodigo.bootcamp.player.Player;

public class CollisionDetector {


    private Obstacles[] obstacles;
    private Player player;

    public CollisionDetector(Obstacles[] obstacles, Player player) {
        this.obstacles = obstacles;
        this.player = player;
    }


    public boolean check() {

        for (Obstacles io : obstacles) {
            if (player.getGridPosition().equals(io.getGridPosition())) {
                //player.setDead();
                System.out.println("Morreu");
                return true;
            }
        }
        return false;

    }

}
