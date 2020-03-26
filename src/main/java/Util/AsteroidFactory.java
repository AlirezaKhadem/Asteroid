package main.java.Util;

import main.java.Intefaces.ObjectFactory;
import main.java.Logic.Constants;
import main.java.Models.Asteroid;

import java.util.Random;

public class AsteroidFactory implements ObjectFactory<Asteroid> {
    private Random random;
    private int count;


    public AsteroidFactory() {
        random = new Random(System.nanoTime());
    }

    @Override
    public Asteroid createNew() {
        count++;
        System.out.println(count+" asteroids created");
        int x = random.nextInt(Constants.maxWidth - Constants.maxSize);
        int size = random.nextInt(Constants.maxSize - Constants.minSize) + Constants.minSize;
        int y = -random.nextInt(Constants.maxHeight);
        int speedY = random.nextInt(Constants.maxSpeed - Constants.minSpeed) + Constants.minSpeed;
        return new Asteroid(new Vector2D(x, y), size, 0, speedY, "asteroid");
    }
}
