package game;

import game.state.MenuState;
import my.engine.config.Config;
import my.engine.core.Engine;
import my.engine.platform.Platform;
import my.engine.platform.awt.AwtPlatform;

public class Main {
    public static void main(String[] args) {
        String opengl = System.getProperty("sun.java2d.opengl");
        if (!Boolean.TRUE.toString().equals(opengl)) {
            System.err.println("OpenGL acceleration is disabled");
        }

        Config config = new Config();
        config.setWidth(800);
        config.setHeight(600);
        config.setFullscreen(false);
        config.setTitle("Space Invaders Game");

        Platform platform = new AwtPlatform();

        platform.initialize(config);

        Engine engine = platform.buildEngine();

        engine.start(new MenuState("Space Invaders"));
    }
}
