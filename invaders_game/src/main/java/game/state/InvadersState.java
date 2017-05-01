package game.state;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import game.Constants;
import game.model.AlienEntity;
import game.model.ShipEntity;
import game.model.ShotEntity;
import my.engine.core.Engine;
import my.engine.display.Canvas;
import my.engine.display.Sprite;
import my.engine.input.Keyboard;
import my.engine.physics.Physics;
import my.engine.state.GameState;

public class InvadersState implements GameState {

    private ShipEntity ship;
    private Set<AlienEntity> aliens = new HashSet<>();
    private Set<ShotEntity> shots = new HashSet<>();
    private Physics physics = new Physics();

    private boolean aliensAtEdge;

    private Engine engine;

    @Override
    public void init(Engine e) {
        this.engine = e;
        physics = new Physics();

        Sprite shipSprite = getSprite(Constants.SHIP_SPRITE);
        int shipX = (getGameFieldWidth() - shipSprite.getWidth()) / 2;
        int shipY = (getGameFieldHeight() - shipSprite.getHeight() - Constants.MARGIN);
        ship = new ShipEntity(this, shipX, shipY, shipSprite);
        physics.addEntity(ship);

        for (int row = 0; row < Constants.ALIEN_ROWS; row++) {
            for (int col = 0; col < Constants.ALIEN_COLUMNS; col++) {
                AlienEntity alien = new AlienEntity(this, 100 + (col * 50), (50) + row * 30, getSprite(Constants.ALIEN_SPRITE));
                physics.addEntity(alien);
                aliens.add(alien);
            }
        }
    }

    public int getGameFieldWidth() {
        return engine.getCanvas().getWidth();
    }

    public int getGameFieldHeight() {
        return engine.getCanvas().getHeight();
    }

    @Override
    public void destroy(Engine e) {
        ship = null;
        aliens.clear();
        shots.clear();
    }

    @Override
    public void update(double delta) {
        handleInput();

        physics.step(delta);

        if (aliensAtEdge) {
            aliensAtEdge = false;
            aliens.forEach(AlienEntity::reverse);
        }
    }

    private void handleInput() {
        Keyboard keyboard = engine.getPlatform().getInput().getKeyboard();

        if (keyboard.wasKeyDown(KeyEvent.VK_LEFT)) {
            ship.setHorizontalMovement(-Constants.SHIP_MOVE_SPEED);
        } else if (keyboard.wasKeyDown(KeyEvent.VK_RIGHT)) {
            ship.setHorizontalMovement(Constants.SHIP_MOVE_SPEED);
        } else {
            ship.setHorizontalMovement(0);
        }

        if (keyboard.wasKeyDown(KeyEvent.VK_SPACE)) {
            fireShot();
        }
    }

    @Override
    public void render(Canvas c) {
        c.drawSprite(ship.getSprite(), ship.getX(), ship.getY());
        aliens.forEach(a -> c.drawSprite(a.getSprite(), a.getX(), a.getY()));
        shots.forEach(s -> c.drawSprite(s.getSprite(), s.getX(), s.getY()));

        c.drawText("fps: " + (int) engine.getFpsCounter().getFps(), 10, 10, "#ff0000", 14);
    }

    public void loose() {
        engine.setGameState(new MenuState("You loose :("));
    }

    public void win() {
        engine.setGameState(new MenuState("You win!"));
    }

    public void fireShot() {
        if (ship.canFire()) {
            ship.fire();

            Sprite shotSprite = getSprite(Constants.SHOT_SPRITE);
            int shotY = ship.getY() - shotSprite.getHeight() / 2;
            ShotEntity shot = new ShotEntity(this, ship.getX() - 4, shotY, shotSprite);
            ShotEntity shot2 = new ShotEntity(this, (int) (ship.getX() + ship.getWidth() - 6), shotY, shotSprite);

            shots.add(shot);
            shots.add(shot2);
            physics.addEntity(shot);
            physics.addEntity(shot2);
        }
    }

    public void notifyAlienKilled(AlienEntity entity) {
        if(entity.isDead()) {
            return;
        }
        entity.setDead(true);

        physics.removeEntity(entity);
        aliens.remove(entity);

        aliens.forEach(a -> a.setHorizontalMovement(a.getHorizontalMovement() * Constants.ALIEN_SPEEDUP));

        if (aliens.isEmpty()) {
            win();
        }
    }

    public void notifyAlienReachedEdge() {
        aliensAtEdge = true;
    }

    public void removeShot(ShotEntity shotEntity) {
        physics.removeEntity(shotEntity);
        shots.remove(shotEntity);
    }

    private Sprite getSprite(String name) {
        return engine.getPlatform().getSpriteStore().getSprite(name);
    }
}
