package pl.pacinho.flappybird.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ImageUtils {

    public static Image getImage(String fileName) throws IOException {
        return ImageIO.read(ImageUtils.class.getClassLoader().getResource("img/" + fileName));
    }

    public static Image getScaledImage(String fileName, Dimension dimension) throws IOException {
        return getScaledImage(
                getImage(fileName),
                dimension
        );
    }

    public static Image getScaledImage(Image image, Dimension dimension) {
        return image.getScaledInstance((int) dimension.getWidth(), (int) dimension.getHeight(), Image.SCALE_SMOOTH);
    }
}
