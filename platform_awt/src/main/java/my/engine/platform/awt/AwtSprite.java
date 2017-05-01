package my.engine.platform.awt;

import java.awt.*;

import my.engine.display.Sprite;

public class AwtSprite extends Sprite {
    private Image image;

    public AwtSprite(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public int getWidth() {
        return image.getWidth(null);
    }

    public int getHeight() {
        return image.getHeight(null);
    }
}
