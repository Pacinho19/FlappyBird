package pl.pacinho.flappybird.view.components;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import pl.pacinho.flappybird.utils.ImageUtils;
import pl.pacinho.flappybird.view.components.model.TubeState;
import pl.pacinho.flappybird.view.components.model.TubeType;
import pl.pacinho.flappybird.view.tools.TubeTools;

import java.awt.*;

@Getter
public class Tube extends Rectangle {

    private Image image;
    private TubeType type;

    @Setter
    private TubeState state = TubeState.NEW;

    @SneakyThrows
    public Tube(Dimension dimension) {
        this.type = TubeType.DOWN;
        this.width = (int) dimension.getWidth() / 5;
        this.image = ImageUtils.getScaledImage("tube-down.png", new Dimension(width, TubeTools.randomHeight(dimension)));
        this.height = image.getHeight(null);
        this.x = dimension.width;
        this.y = (int) (dimension.getHeight() - height);
    }

    @SneakyThrows
    public Tube(Dimension dimension, int height) {
        this.type = TubeType.UP;
        this.width = (int) dimension.getWidth() / 5;
        this.image = ImageUtils.getScaledImage("tube-up.png", new Dimension(width, height));
        this.height = image.getHeight(null);
        this.x = dimension.width;
        this.y = 0;
    }

    public void move() {
        this.x = x - 4;
    }

    @Override
    public String toString() {
        return "Tube{" +
               "tubeType=" + type +
               ", x=" + x +
               ", y=" + y +
               ", width=" + width +
               ", height=" + height +
               '}';
    }
}
