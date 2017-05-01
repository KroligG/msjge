package my.game.model;

import my.game.Constants;
import my.engine.display.Sprite;
import my.engine.physics.Entity;
import my.game.state.InvadersState;

public class ShipEntity extends Entity {
    private InvadersState game;
    private Sprite sprite;

    private double fireCooldown = 0;

    public ShipEntity(InvadersState game, int x, int y, Sprite sprite) {
        super(x, y, sprite.getWidth(), sprite.getHeight());
        this.sprite = sprite;
        this.game = game;
    }

    @Override
    public void move(double delta) {
        if (((dx < 0) && (x < Constants.MARGIN)) || ((dx > 0) && (x > game.getGameFieldWidth() - width - Constants.MARGIN))) {
            return;
        }
        super.move(delta);

        if (fireCooldown > 0) {
            fireCooldown -= delta;
        }
    }

    public void collidedWith(Entity other) {
        if (other instanceof AlienEntity) {
            game.loose();
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean canFire() {
        return fireCooldown <= 0;
    }

    public void fire() {
        fireCooldown = Constants.FIRE_COOLDOWN;
    }
}