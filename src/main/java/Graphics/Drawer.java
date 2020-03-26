package main.java.Graphics;

import main.java.Logic.Constants;
import main.java.Logic.GameState;
import main.java.Models.Asteroid;
import main.java.Models.Bullet;
import main.java.Models.SpaceShip;
import main.java.Util.MyComponent;
import main.java.Util.ObjectPool;

import java.awt.*;
import java.util.List;


class Drawer {

    private Graphics2D graphics2D;
    private GameState gameState;

    public Drawer(Graphics2D graphics2D) {
        gameState = GameState.getInstance();
        setGraphics2D(graphics2D);
    }

    void drawGameState() {
        drawAsteroids();
        drawSpaceShip();
    }

    void drawSpaceShip() {

        //this line draw space ship
        drawImage(gameState.getPlayer().getSpaceShip());

        //this loop draw spaceship.bullets
        List<Bullet> bullets = gameState.getBullets();
        for (Bullet bullet : bullets) {
            drawImage(bullet);
        }

    }

    void drawAsteroids() {
        for (Asteroid asteroid : gameState.getAsteroids()) {
            drawImage(asteroid);
        }
    }

    void drawGameOver() {
        String prompt = "Game Over! :(";
        Font font = new Font("Helvetica", Font.BOLD, 50);
        FontMetrics fontMetrics = graphics2D.getFontMetrics(font);
        int width = fontMetrics.stringWidth(prompt);
        graphics2D.setColor(Color.white);
        graphics2D.setFont(font);
        graphics2D.drawString(prompt, (Constants.maxWidth - width) / 2, (Constants.maxHeight - 50) / 2);
    }

    void drawImage(MyComponent component) {
        graphics2D.drawImage(component.getImage(), component.getPosition().getX(), component.getPosition().getY(),
                component.getSize(), component.getSize(), null);
    }

    public Graphics2D getGraphics2D() {
        return graphics2D;
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }
}



