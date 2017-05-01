package my.engine.platform.awt;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import my.engine.display.Sprite;
import my.engine.display.SpriteLoader;

public class AwtSpriteLoader implements SpriteLoader {
    @Override
    public Sprite loadSprite(String descriptor) throws IOException {
        BufferedImage sourceImage;

        URL url = this.getClass().getClassLoader().getResource(descriptor);
        if (url == null) {
            throw new IOException("Can't find sprite: " + descriptor);
        }
        sourceImage = ImageIO.read(url);

        // create an accelerated image of the right size to store our sprite in
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        Image image = gc.createCompatibleImage(sourceImage.getWidth(), sourceImage.getHeight(), Transparency.BITMASK);

        // draw our source image into the accelerated image
        image.getGraphics().drawImage(sourceImage, 0, 0, null);

        return new AwtSprite(image);
    }
}
