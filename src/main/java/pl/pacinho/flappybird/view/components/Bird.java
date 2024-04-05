package pl.pacinho.flappybird.view.components;

import lombok.Getter;
import lombok.SneakyThrows;
import pl.pacinho.flappybird.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@Getter
public class Bird extends Rectangle {

    private ImageIcon image;

    private double velocityY = -9;
    private final double gravity = 0.8;

    private final Dimension boardDimension;
    private final Dimension birdDimension;

    public Bird(Dimension boardDimension) throws IOException {
        this.birdDimension = new Dimension((int) boardDimension.getWidth() / 10, (int) boardDimension.getHeight() / 15);
        this.image = ImageUtils.getImageIcon("bird2.gif");
        this.x = (boardDimension.width - image.getImage().getWidth(null)) / 2;
        this.y = (boardDimension.height - image.getImage().getHeight(null)) / 2;
        this.width = birdDimension.width;
        this.height = birdDimension.height;
        this.boardDimension = boardDimension;
    }

    @SneakyThrows
    public void flyDown() {
        if (y + height >= boardDimension.getHeight())
            return;

        velocityY += gravity;
        y += velocityY;

        this.y = Math.min(y, boardDimension.height - height);
    }

    @SneakyThrows
    public void jump() {
        velocityY = -9;
        this.y = Math.max(y, 0);
    }

    @Override
    public String toString() {
        return "Bird{" +
               "x=" + x +
               ", y=" + y +
               ", width=" + width +
               ", height=" + height +
               '}';
    }
}
