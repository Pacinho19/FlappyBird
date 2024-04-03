package pl.pacinho.flappybird.view.controller;

import lombok.Getter;
import pl.pacinho.flappybird.utils.RandomUtils;
import pl.pacinho.flappybird.view.components.Tube;
import pl.pacinho.flappybird.view.tools.TubeTools;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TubeController {

    private final Dimension dimension;
    @Getter
    private int ticksToFirstTube = 50;
    @Getter
    private int spaceBetweenTubes;
    private List<Tube> tubes;

    public TubeController(Dimension dimension) {
        this.dimension = dimension;
        this.spaceBetweenTubes = (int) (dimension.width * 0.4);
        tubes = new ArrayList<>();
    }

    public List<Tube> getTubes() {
        return tubes;
    }

    public void createTube() {
        Tube tubeDown = new Tube(dimension);
        Tube tubeUp = generateTubeUp(tubeDown, dimension);
        tubes.add(tubeDown);
        tubes.add(tubeUp);
    }

    private Tube generateTubeUp(Tube tubeDown, Dimension dimension) {
        double freeSpaceRatio = RandomUtils.randomInt(2, 3) * 0.1;
        return new Tube(dimension, TubeTools.calculateHeight(dimension.getHeight(), freeSpaceRatio, (int) tubeDown.getHeight()));
    }

    public Optional<Tube> getLastTube() {
        if (tubes.isEmpty())
            return Optional.empty();
        return Optional.of(tubes.get(tubes.size() - 1));
    }

    public void clearUnusedTubes() {
        tubes = tubes.stream()
                .filter(t -> t.getX() + t.getWidth() > 0)
                .collect(Collectors.toList());
    }

    public Optional<Tube> getFirstTube() {
        if (tubes.isEmpty())
            return Optional.empty();
        return Optional.of(tubes.get(0));
    }
}
