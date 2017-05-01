package my.game.model;

import my.game.Constants;
import my.game.state.InvadersState;
import my.engine.display.Sprite;
import my.engine.physics.Entity;

public class AlienEntity extends Entity {
    private InvadersState game;
    private Sprite sprite;
    private boolean dead;

    public AlienEntity(InvadersState game, int x, int y, Sprite sprite) {
        super(x, y, sprite.getWidth(), sprite.getHeight());
        this.sprite = sprite;
        this.game = game;
        dx = -Constants.ALIEN_MOVE_SPEED;
    }

    @Override
    public void move(double delta) {
        super.move(delta);
        doLogic();
    }

    private void doLogic() {
        if (((dx < 0) && (x < Constants.MARGIN)) || ((dx > 0) && (x > game.getGameFieldWidth() - width - Constants.MARGIN))) {
            game.notifyAlienReachedEdge();
        }

        if (y > (game.getGameFieldHeight() - height)) {
            game.loose();
        }
    }

    public void reverse() {
        dx = -dx;
        y += Constants.ALIEN_DOWN_SPEED;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}