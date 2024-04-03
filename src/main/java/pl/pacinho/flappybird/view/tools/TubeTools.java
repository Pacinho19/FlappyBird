package pl.pacinho.flappybird.view.tools;

import pl.pacinho.flappybird.utils.RandomUtils;

import java.awt.*;

public class TubeTools {
    public static int randomHeight(Dimension dimension) {
        double heightRatio = RandomUtils.randomInt(3, 6) * 0.1;
        return (int) (dimension.getHeight() * heightRatio);
    }

    public static int calculateHeight(double height, double freeSpaceRatio, int downTubeHeight) {
        return (int) (height - downTubeHeight - (height * freeSpaceRatio));
    }
}
