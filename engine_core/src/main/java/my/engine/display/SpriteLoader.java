package my.engine.display;

import java.io.IOException;

public interface SpriteLoader {
    Sprite loadSprite(String descriptor) throws IOException;
}
