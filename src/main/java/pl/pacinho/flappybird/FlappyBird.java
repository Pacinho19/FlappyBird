package pl.pacinho.flappybird;

import pl.pacinho.flappybird.view.GameBoard;

import java.awt.*;

public class FlappyBird {
    private static final int WIDTH = 450;
    private static final Dimension DIMENSION = new Dimension(WIDTH, (int) (WIDTH * ((double) 16 / 9)));

    public static void main(String[] args) {
        new GameBoard(DIMENSION)
                .start();
    }
}
