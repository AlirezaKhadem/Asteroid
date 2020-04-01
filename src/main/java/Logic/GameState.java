package main.java.Logic;

import main.java.Models.Asteroid;
import main.java.Models.Bullet;
import main.java.Models.Player;
import main.java.Util.*;
//import main.java.Util.Configs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState {
    private static GameState gameState;

    private Player player;
    private AsteroidsObjectPool asteroidsObjectPool;
    private List<Asteroid> asteroids;


    private List<Bullet> bullets;
    private BulletsObjectPool bulletsObjectPool;
    private GameConstants constants;


    private boolean gameOver = false;

    private GameState() {
        this.player = new Player("", "", "");
        constants = GameConstants.getInstance();

        asteroids = new ArrayList<>();
        asteroidsObjectPool = new AsteroidsObjectPool(asteroids);
        for (int i = 0; i < constants.getConstant("asteroidNumber"); i++) {
            checkOutAsteroid();
        }

        bullets = Collections.synchronizedList( new ArrayList<>());
        bulletsObjectPool = new BulletsObjectPool(bullets);
        player.getSpaceShip().setBulletsObjectPool(bulletsObjectPool);
    }

    public static GameState getInstance() {
        if (gameState == null) gameState = new GameState();
        return gameState;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public List<Asteroid> getAsteroids() {
        return asteroids;
    }

    public Player getPlayer() {
        return player;
    }

    public void checkInAsteroid(Asteroid asteroid) {
        asteroid.reset();
        asteroidsObjectPool.checkIn(asteroid);
    }

    public void checkOutAsteroid() {
        asteroidsObjectPool.checkOut();
    }

    public void checkInBullet(Bullet bullet) {
        bulletsObjectPool.checkIn(bullet);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

}

