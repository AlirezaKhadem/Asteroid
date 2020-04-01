package main.java.Models;

//import main.java.Util.Configs;
import main.java.Util.ConfigLoader;
import main.java.Util.Configs;
import main.java.Util.GameConstants;
import main.java.Util.Vector2D;

import java.io.File;
import java.io.Serializable;

public class Player implements Serializable {

    private String username;
    private String password;
    private String id;
    private GameConstants constants;

    private File dataFile;

    private SpaceShip ship;


    public Player(String username, String password, String id) {

        this.username = username;
        this.password = password;
        this.id = id;
        constants =  GameConstants.getInstance();

        this.ship = new SpaceShip(new Vector2D(constants.getConstant("maxWidth") / 2 - 25, constants.getConstant("maxHeight") / 2 + 200));

    }

    public SpaceShip getSpaceShip() {
        return ship;
    }
}
