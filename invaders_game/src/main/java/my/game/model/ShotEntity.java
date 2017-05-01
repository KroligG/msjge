package my.game.model;

import my.game.Constants;
import my.engine.display.Sprite;
import my.engine.physics.Entity;
import my.game.state.InvadersState;

public class ShotEntity extends Entity {
    private InvadersState game;
    private Sprite sprite;

    public ShotEntity(InvadersState game, int x, int y, Sprite sprite) {
        super(x, y, sprite.getWidth(), sprite.getHeight());
        this.sprite = sprite;
        this.game = game;

        dy = Constants.SHOT_MOVE_SPEED;
    }

    @Override
    public void move(double delta) {
        super.move(delta);

        if (y < -height) {
            game.removeShot(this);
        }
    }

    public void collidedWith(Entity other) {
        if (other instanceof AlienEntity) {
            game.removeShot(this);
            game.notifyAlienKilled((AlienEntity) other);
        }
    }

    public Sprite getSprite() {
        return sprite;
    }
}