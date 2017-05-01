package my.engine.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

    private static int x;
    private static int y;
    private static int button = -1;
    private static int rotation = -1;

    private static boolean up = false, down = false;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        rotation = e.getWheelRotation();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        button = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button = -1;
    }

    public static boolean isClicked(int button) {
        if ((button == getButton())) down = true;
        if (down == true && getButton() == -1) up = true;

        if (up && down) {
            down = false;
            up = false;
            return true;
        }

        return false;
    }

    public static int wheel() {
        return rotation;
    }

    public static int getButton() {
        return button;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

}
