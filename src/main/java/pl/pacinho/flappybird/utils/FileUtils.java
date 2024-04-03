package pl.pacinho.flappybird.utils;

import lombok.SneakyThrows;

import java.io.File;

public class FileUtils {

    @SneakyThrows
    public static File getFile(String fileName) {
        return new File(FileUtils.class.getClassLoader().getResource(fileName).toURI());
    }

}
