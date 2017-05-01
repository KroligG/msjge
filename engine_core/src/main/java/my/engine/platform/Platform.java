package my.engine.platform;

import my.engine.config.Config;
import my.engine.core.Engine;
import my.engine.display.Display;
import my.engine.input.Input;
import my.engine.util.SpriteStore;

public interface Platform {
    void initialize(Config config);

    Input getInput();

    Display geDisplay();

    SpriteStore getSpriteStore();

    Engine buildEngine();
}
