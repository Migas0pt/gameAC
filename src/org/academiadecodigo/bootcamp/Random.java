package org.academiadecodigo.bootcamp;

public class Random {
    public static int getRandom(int max) {
        return (int)(Math.random() * ((max - 0) + 1)) + 0;
    }
}
