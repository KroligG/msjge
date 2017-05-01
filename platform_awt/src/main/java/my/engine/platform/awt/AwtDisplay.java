package my.engine.platform.awt;


import javax.swing.*;

import my.engine.display.Canvas;
import my.engine.display.Display;

public class AwtDisplay extends Display {
    private JFrame frame;
    private AwtCanvas canvas;

    public AwtDisplay(JFrame frame, java.awt.Canvas canvas) {
        this.frame = frame;
        this.canvas = new AwtCanvas(canvas);
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }
}
