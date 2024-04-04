package pl.pacinho.flappybird.view.controller;

import lombok.Getter;
import lombok.SneakyThrows;
import pl.pacinho.flappybird.view.components.Board;
import pl.pacinho.flappybird.view.components.Tube;
import pl.pacinho.flappybird.view.components.model.TubeState;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class BoardController {
    private final Board board;
    private TubeController tubeController;
    private final int DELAY = 1000 / 60;

    @Getter
    private Timer timer;
    private boolean jump;
    private int ticks;

    @Getter
    private boolean end;
    private int points = 0;

    private final AtomicBoolean canJump = new AtomicBoolean(true);

    public BoardController(Board board) {
        this.board = board;
        this.timer = new Timer(DELAY, board);
        this.tubeController = new TubeController(board.getBackgroundDimension());
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == KeyEvent.VK_SPACE && !timer.isRunning()) {
            timer.start();
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && timer.isRunning() && !end) {
            if (canJump.compareAndSet(true, false))
                jump();
        } else if (e.getKeyCode() == KeyEvent.VK_R && timer.isRunning()) {
            restart();
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE)
            canJump.set(true);
    }

    @SneakyThrows
    private void restart() {
        board.resetBird();
        tubeController.getTubes().clear();
        end = false;
        timer.restart();
        ticks = 0;
        points = 0;
        refresh();
    }

    public void gameTick() {
        if (!jump)
            board.getBird().flyDown();
        else
            jump();

        if (tubeController.getTicksToFirstTube() == ticks
            || checkLastTubePosition())
            createTube();

        if (!end) {
            checkCollisions();
            moveTubes();
            clearUnusedTubes();
            checkPoints();
        }

        refresh();
        ticks++;
    }

    private void checkPoints() {
        List<Tube> list = tubeController.getTubes()
                .stream()
                .filter(tube -> tube.x <= board.getBird().getX() && tube.getState() == TubeState.NEW)
                .toList();

        if (list.isEmpty())
            return;

        points++;
        list.forEach(t -> t.setState(TubeState.COMPLETED));
    }

    private void checkCollisions() {
        for (Tube tube : tubeController.getTubes()) {
            if (!tube.intersects(board.getBird()))
                continue;

            end = true;
            return;
        }
    }

    private void clearUnusedTubes() {
        tubeController.clearUnusedTubes();
    }

    private boolean checkLastTubePosition() {
        return tubeController.getLastTube()
                .map(t -> t.getX() + t.getWidth() + tubeController.getSpaceBetweenTubes() < board.getWidth())
                .orElse(false);
    }

    private void moveTubes() {
        tubeController.getTubes()
                .forEach(Tube::move);
    }

    private void createTube() {
        tubeController.createTube();
    }

    private void jump() {
        board.getBird().jump();
        jump = false;
    }

    private void refresh() {
        board.repaint();
        board.revalidate();
        board.validate();
    }

    public List<Tube> getTubes() {
        return tubeController.getTubes();
    }

    public int getPoints() {
        return points;
    }

}
