package pl.pacinho.flappybird.view.components;

import lombok.Getter;
import lombok.SneakyThrows;
import pl.pacinho.flappybird.utils.FileUtils;
import pl.pacinho.flappybird.utils.ImageUtils;
import pl.pacinho.flappybird.view.controller.BoardController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel implements ActionListener {

    private final BoardController controller;
    private final Dimension dimension;
    private Image background;
    private Image base;
    private Image gameOver;
    private Image start;
    @Getter
    private Bird bird;
    private Font pointsFont;

    public Board(Dimension dimension) {
        this.dimension = dimension;
        this.setDoubleBuffered(true);
        this.setSize(dimension);
        init();

        this.controller = new BoardController(this);
    }

    @SneakyThrows
    private void initFont() {
        pointsFont = Font.createFont(Font.TRUETYPE_FONT, FileUtils.getFile("font/flappy-bird-font.ttf"))
                .deriveFont((float) (dimension.getHeight() * 0.08));

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(pointsFont);
    }

    private void init() {
        initFont();

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                controller.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                controller.keyReleased(e);
            }
        });

        try {
            background = ImageUtils.getScaledImage("background.png", new Dimension(dimension.width, (int) (dimension.height * 0.9)));
            gameOver = ImageUtils.getScaledImage("game-over.png", new Dimension((int) (dimension.width * 0.9), (int) (dimension.height * 0.1)));
            start = ImageUtils.getScaledImage("start.png", new Dimension((int) (dimension.width * 0.9), (int) (dimension.height * 0.1)));
            bird = new Bird(getBackgroundDimension());
            base = ImageUtils.getScaledImage("base.png", new Dimension(dimension.width, (int) (dimension.height * 0.1)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        g.drawImage(base, 0, background.getHeight(null), null);

        g.setFont(pointsFont);
        g.setColor(Color.WHITE);

        if(controller.getTimer().isRunning()){
            controller.getTubes().forEach(tube -> g.drawImage(tube.getImage(), (int) tube.getX(), (int) tube.getY(), null));
            g.drawImage(bird.getImage(), (int) bird.getX(), (int) bird.getY(), null);
        }else{
            g.drawImage(start, (background.getWidth(null) - start.getWidth(null)) / 2, (background.getHeight(null) - start.getHeight(null)) / 2, null);
        }

        String points = String.valueOf(controller.getPoints());
        int textWidth = g.getFontMetrics().stringWidth(points);
        g.drawString(points, (background.getWidth(null) - textWidth) / 2, (int) (background.getHeight(null) * 0.1));

        if (controller.isEnd())
            g.drawImage(gameOver, (background.getWidth(null) - gameOver.getWidth(null)) / 2, (background.getHeight(null) - gameOver.getHeight(null)) / 2, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.gameTick();
    }

    public void resetBird() throws IOException {
        bird = new Bird(getBackgroundDimension());
    }

    public Dimension getBackgroundDimension() {
        return new Dimension(
                background.getWidth(null),
                background.getHeight(null)
        );
    }
}
