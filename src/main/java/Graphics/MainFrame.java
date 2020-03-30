package main.java.Graphics;

import main.java.Util.ConfigLoader;
import javax.swing.*;
import java.awt.*;
import java.util.Properties;

public class MainFrame extends JFrame {

    private final GamePanel gamePanel;

    public MainFrame( GamePanel panel) throws HeadlessException {
        super("Asteroid");

        this.gamePanel =panel;
        this.initFrame();
    }


    private void initFrame() {

        try {

            this.ConfigFrame(ConfigLoader.getInstance("default").getProperties("FRAME_CONFIG_FILE"));

            this.setLocationRelativeTo(null);

            this.setContentPane(this.gamePanel);
            this.setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private void ConfigFrame(Properties configFile) {
            this.setSize(Integer.parseInt(configFile.getProperty("width")), Integer.parseInt(configFile.getProperty("height")));
            this.setDefaultCloseOperation(Integer.parseInt(configFile.getProperty("CloseOperation")));
            this.setResizable(Boolean.parseBoolean(configFile.getProperty("Resizable")));
            this.setUndecorated(Boolean.parseBoolean(configFile.getProperty("Undecorated")));
    }

}