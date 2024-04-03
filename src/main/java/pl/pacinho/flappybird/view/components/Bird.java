package pl.pacinho.flappybird.view.components;

import lombok.Getter;
import lombok.SneakyThrows;
import pl.pacinho.flappybird.utils.ImageUtils;

import java.awt.*;
import java.io.IOException;

@Getter
public class Bird extends Rectangle {

    private Image image;

    private int velocityY = -12;
    private final int gravity = 1;

    private final Dimension boardDimension;
    private final Dimension birdDimension;

    public Bird(Dimension boardDimension) throws IOException {
        this.birdDimension = new Dimension((int) boardDimension.getWidth() / 10, (int) boardDimension.getHeight() / 15);
        this.image = ImageUtils.getScaledImage("bird.png", birdDimension);
        this.x = (boardDimension.width - image.getWidth(null)) / 2;
        this.y = (boardDimension.height - image.getHeight(null)) / 2;
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
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
        velocityY = -12;
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
