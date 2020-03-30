package main.java.Models;

import main.java.Util.*;

public class SpaceShip extends MyComponent {


    private BulletsObjectPool bulletsObjectPool;


    public SpaceShip(Vector2D position) {
        super(position, 50, 5, 5, "spaceship");
    }


    public void shootBullet(Vector2D position) {
        SoundPlayer.play(ConfigLoader.getInstance("default").getAddress("RESOURCE_URL") + "Sound/laser.wav");
        bulletsObjectPool.checkOut().setPosition(position);
    }

    public BulletsObjectPool getBulletsObjectPool() {
        return bulletsObjectPool;
    }

    public void setBulletsObjectPool(BulletsObjectPool bulletsObjectPool) {
        this.bulletsObjectPool = bulletsObjectPool;
    }


}
