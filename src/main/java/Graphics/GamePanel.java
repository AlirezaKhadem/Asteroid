package main.java.Graphics;

import main.java.Intefaces.Updatable;
import main.java.Logic.GameState;
import main.java.Logic.Updater;
import main.java.Util.ImageLoader;
import main.java.Util.PanelConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Updatable {

    private Drawer drawer;
    private GameState gameState;
    private Updater updater;
    private GameAction gameAction;
    private BufferedImage bgImage;
    private PanelConfig config;

    private int bgImageX;
    private int bgImageY;
    private int bgImageSpeedX;
    private int bgImageSpeedY;
    private int coefficient;
    private int timeCounter;


    public GamePanel() {
        super();
        init();
        start();
    }


    private void init() {
        updater = new Updater();
        gameState = GameState.getInstance();
        gameAction = new GameAction(this, updater);
        config = new PanelConfig("GAMEPANEL_CONFIG_FILE");
        configurePanel();

    }

    private void start() {
        javax.swing.Timer t = new javax.swing.Timer(16, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                draw();
            }
        });
        t.start();

    }

    private void configurePanel() {

        this.bgImage = ImageLoader.getInstance().loadImage(config.getBgImage());
        this.bgImageX = config.getBgImageX();
        this.bgImageY = config.getBgImageY();
        this.bgImageSpeedX = config.getBgImageSpeedX();
        this.bgImageSpeedY = config.getBgImageSpeedY();
        this.coefficient = config.getCoefficient();
        this.timeCounter = config.getTimeCounter();

        this.setSize(config.getWidth(), config.getHeight());

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (drawer == null) drawer = new Drawer((Graphics2D) g);
        drawer.setGraphics2D((Graphics2D) g);
        paintGamePanel((Graphics2D) g);
    }

    private void paintGamePanel(Graphics2D graphics2D) {
        graphics2D.drawImage(this.bgImage, this.bgImageX, this.bgImageY, null);

        if (gameState.isGameOver()) {
            drawer.drawGameOver();
        } else {
            drawer.drawGameState();
        }
    }

    private void updateBackgroundImage() {
        updateCounter();

        bgImageX += coefficient * bgImageSpeedX;
        bgImageY += coefficient * bgImageSpeedY;


    }

    private void updateCounter() {
        if (timeCounter % 100 == 0) {
            coefficient *= -1;
            timeCounter = 1;
        }
        this.timeCounter++;
    }

    @Override
    public void update() {
        updateBackgroundImage();
        updater.update();
    }

    public void draw() {
        repaint();
        revalidate();

    }


}