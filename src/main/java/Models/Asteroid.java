package main.java.Models;

import main.java.Util.Constants;
import main.java.Util.Vector2D;

import java.util.Random;

public class Asteroid extends MyComponent {

    public static Random random = new Random();

    public Asteroid(Vector2D position, int size, int speedX, int speedY, String componentName) {
        super(position, size, speedX, speedY, componentName);
    }

    public void reset() {
        getPosition().setX(random.nextInt(Constants.maxWidth - Constants.maxSize));
        setSize(random.nextInt(Constants.maxSize - Constants.minSize) + Constants.minSize);
        getPosition().setY(-random.nextInt(Constants.maxHeight));
        setSpeedY(random.nextInt(Constants.maxSpeed - Constants.minSpeed) + Constants.minSpeed);

    }

    public Vector2D getRandomPosition() {
        return new Vector2D(random.nextInt(Constants.maxWidth - Constants.maxSize), -this.getSize());
    }

}
