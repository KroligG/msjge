package my.engine.util;

public class FpsCounter {
    private double window;
    private double frameTime;
    private int frames;
    private double fps;

    public FpsCounter(double window) {
        this.window = window;
    }

    public void frame(double delta) {
        frameTime += delta;
        frames++;
        if(frameTime > window) {
            fps = 1.0 / frameTime * frames;
            frameTime = 0;
            frames = 0;
        }
    }


    public double getFps() {
        return fps;
    }
}
