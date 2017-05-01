package my.engine.physics;

import java.util.ArrayList;
import java.util.List;

public class Physics {
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> entitiesToRemove = new ArrayList<>();

    public boolean addEntity(Entity entity) {
        return entities.add(entity);
    }

    public boolean removeEntity(Entity entity) {
        return entitiesToRemove.add(entity);
    }

    public void clear() {
        entities.clear();
        entitiesToRemove.clear();
    }

    public void step(double delta) {
        entities.removeAll(entitiesToRemove);
        entitiesToRemove.clear();

        entities.forEach(e -> e.move(delta));
        processCollisions();
    }

    private void processCollisions() {
        for (int p = 0; p < entities.size(); p++) {
            for (int s = p + 1; s < entities.size(); s++) {
                Entity me = entities.get(p);
                Entity him = entities.get(s);

                if (me.collidesWith(him)) {
                    me.collidedWith(him);
                    him.collidedWith(me);
                }
            }
        }
    }
}
