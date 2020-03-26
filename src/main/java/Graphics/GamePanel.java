package main.java.Graphics;

import main.java.Intefaces.Updatable;
import main.java.Logic.GameState;
import main.java.Logic.Updater;
import main.java.Util.ImageLoader;
import main.java.Util.Urls;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements Updatable {

    private Drawer drawer;
    private GameState gameState;
    private Updater updater;
    private Timer timer;
    private GameAction gameAction;
    private BufferedImage bgImage;

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
        timer = new Timer();
        gameState = GameState.getInstance();
        gameAction = new GameAction(this, updater);
        configurePanel();

    }

    private void start() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                update();
                draw();

            }
        }, 100, 16);
    }

    private void configurePanel() {

        try (Scanner input = new Scanner(new File(Urls.GAMEPANEL_CONFIG_FILE))) {

            this.bgImage = ImageLoader.getInstance().loadImage(input.next());
            this.bgImageX = input.nextInt();
            this.bgImageY = input.nextInt();
            this.bgImageSpeedX = input.nextInt();
            this.bgImageSpeedY = input.nextInt();
            this.coefficient = input.nextInt();
            this.timeCounter = input.nextInt();

            this.setSize(input.nextInt(), input.nextInt());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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

    public void draw(){
        repaint();
        revalidate();

    }


}