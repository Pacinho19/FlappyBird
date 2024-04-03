package pl.pacinho.flappybird.view;

import pl.pacinho.flappybird.config.Properties;
import pl.pacinho.flappybird.view.components.Board;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JFrame {

    private final Dimension dimension;

    public GameBoard(Dimension dimension) {
        this.dimension = dimension;
        this.setTitle(Properties.NAME + " " + Properties.VERSION);
        this.setSize(dimension);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        initComponents();
        initView();
    }

    private void initView() {
        Container main = this.getContentPane();
        main.setLayout(new BorderLayout());
        main.add(board, BorderLayout.CENTER);
    }

    private void initComponents() {
        this.board = new Board(dimension);
        board.setFocusable(true);
        board.requestFocusInWindow();
    }

    public void start() {
        this.setVisible(true);
    }

    private Board board;
}
