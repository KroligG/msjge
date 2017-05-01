package my.engine.core;

import my.engine.display.Canvas;
import my.engine.platform.Platform;
import my.engine.state.GameState;
import my.engine.util.FpsCounter;

public class Engine {
    private Platform platform;
    private boolean gameRunning;
    private GameState gameState;
    private FpsCounter fpsCounter = new FpsCounter(0.3);

    public Engine(Platform platform) {
        this.platform = platform;
    }

    public void start(GameState gameState) {
        synchronized (this) {
            gameRunning = true;
            setGameState(gameState);
        }

        mainLoop();
    }

    private void mainLoop() {
        long lastLoopTime = System.nanoTime();

        while (gameRunning) {
            double delta = (System.nanoTime() - lastLoopTime) / 1e9d;
            lastLoopTime = System.nanoTime();
            fpsCounter.frame(delta);

            gameState.update(delta);

            render();
        }
    }

    private void render() {
        Canvas canvas = getCanvas();
        canvas.prepare();
        gameState.render(canvas);
        canvas.done();
    }

    public synchronized void stop() {
        gameRunning = false;
        this.gameState.destroy(this);
    }

    public synchronized void setGameState(GameState gameState) {
        if (this.gameState != null) {
            this.gameState.destroy(this);
        }
        this.gameState = gameState;
        this.gameState.init(this);
    }

    public Canvas getCanvas() {
        return platform.geDisplay().getCanvas();
    }

    public Platform getPlatform() {
        return platform;
    }

    public FpsCounter getFpsCounter() {
        return fpsCounter;
    }
}
