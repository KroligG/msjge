package my.engine.util;

import java.io.IOException;
import java.util.HashMap;

import my.engine.display.Sprite;
import my.engine.display.SpriteLoader;

public class SpriteStore {

    private SpriteLoader spriteLoader;

    private HashMap<String, Sprite> sprites = new HashMap<>();

    public SpriteStore(SpriteLoader spriteLoader) {
        this.spriteLoader = spriteLoader;
    }

    public Sprite getSprite(String descriptor) {
        Sprite sprite = sprites.get(descriptor);
        if (sprite == null) {
            try {
                sprite = spriteLoader.loadSprite(descriptor);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (sprite != null) {
                sprites.put(descriptor, sprite);
            }
        }
        return sprite;
    }
}