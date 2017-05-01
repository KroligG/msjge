package my.engine.state;

import my.engine.core.Engine;
import my.engine.display.Canvas;

public interface GameState {
    void init(Engine e);

    void destroy(Engine e);

    void render(Canvas c);

    void update(double delta);
}
