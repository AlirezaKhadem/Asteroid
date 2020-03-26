package main.java.Graphics;

import main.java.Logic.Updater;
import main.java.Util.Vector2D;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GameAction implements MouseMotionListener, MouseListener {

    private final GamePanel gamePanel;
    private final Updater updater;

    public GameAction(GamePanel gamePanel, Updater updater) {
        this.gamePanel = gamePanel;
        this.updater = updater;
        this.init();
    }

    private void init() {

        hideCursor();
        gamePanel.addMouseListener(this);
        gamePanel.addMouseMotionListener(this);

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        updater.shootBullet(new Vector2D(e.getX(), e.getY()));

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        updater.moveSpaceShip(new Vector2D(e.getX(), e.getY()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        updater.moveSpaceShip(new Vector2D(e.getX(), e.getY()));
    }

    private void hideCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        Point point = new Point(0, 0);
        BufferedImage cursorImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Cursor cursor = toolkit.createCustomCursor(cursorImg, point, "cursor");

        this.gamePanel.setCursor(cursor);
    }
}
