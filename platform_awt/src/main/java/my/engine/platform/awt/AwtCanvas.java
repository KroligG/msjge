package my.engine.platform.awt;

import java.awt.*;

import my.engine.display.Canvas;
import my.engine.display.Sprite;

public class AwtCanvas implements Canvas {

    private java.awt.Canvas canvas;
    private Graphics2D graphics2D;

    public AwtCanvas(java.awt.Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public int getWidth() {
        return canvas.getWidth();
    }

    @Override
    public int getHeight() {
        return canvas.getHeight();
    }

    @Override
    public void prepare() {
        graphics2D = (Graphics2D) canvas.getBufferStrategy().getDrawGraphics();
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void done() {
        graphics2D.dispose();
        graphics2D = null;
        canvas.getBufferStrategy().show();
    }

    @Override
    public void drawSprite(Sprite sprite, int x, int y) {
        if (!(sprite instanceof AwtSprite)) {
            throw new IllegalArgumentException("Unsupported sprite format");
        }
        getGraphics2D().drawImage(((AwtSprite) sprite).getImage(), x, y, null);
    }

    @Override
    public void drawText(String str, int x, int y, String color, float fontSize) {
        Graphics2D g = getGraphics2D();

        Font prevFont = g.getFont();
        Font font = prevFont.deriveFont(fontSize);
        g.setFont(font);

        Color prevColor = g.getColor();
        g.setColor(Color.decode(color));

        g.drawString(str, x, y);

        g.setColor(prevColor);
        g.setFont(prevFont);
    }

    @Override
    public void drawRectangle(int x, int y, int width, int height, String color) {
        Graphics2D g = getGraphics2D();

        Color prevColor = g.getColor();
        g.setColor(Color.decode(color));

        g.fillRect(x, y, width, height);

        g.setColor(prevColor);
    }

    private Graphics2D getGraphics2D() {
        if (graphics2D == null) {
            throw new IllegalStateException("Canvas is not prepared.");
        }
        return graphics2D;
    }
}
