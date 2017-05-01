package my.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private boolean[] keys = new boolean[700];
    private boolean[] releasedKeys = new boolean[keys.length];

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        releasedKeys[e.getKeyCode()] = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        releasedKeys[e.getKeyCode()] = true;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public boolean isKeyDown(int key) {
        return keys[key];
    }

    public boolean wasKeyDown(int key) {
        boolean wasDown = keys[key] || releasedKeys[key];
        releasedKeys[key] = false;
        return wasDown;
    }

    public boolean isKeyPressed(int key) {
        boolean releasedKey = releasedKeys[key];
        releasedKeys[key] = false;
        return releasedKey;
    }
}
