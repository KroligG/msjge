package my.engine.display;

public interface Canvas {
    int getWidth();

    int getHeight();

    void prepare();

    void done();

    void drawSprite(Sprite sprite, int x, int y);

    void drawText(String str, int x, int y, String color, float fontSize);

    void drawRectangle(int x, int y, int width, int height, String color);
}
