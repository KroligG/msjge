package my.engine.platform.awt;

import java.awt.*;

import javax.swing.*;

import my.engine.config.Config;
import my.engine.core.Engine;
import my.engine.display.Display;
import my.engine.util.SpriteStore;
import my.engine.input.Input;
import my.engine.input.Keyboard;
import my.engine.input.Mouse;
import my.engine.platform.Platform;

public class AwtPlatform implements Platform {
    private boolean initialized;

    private Input input;
    private Display display;
    private SpriteStore spriteStore;

    @Override
    public synchronized void initialize(Config config) {
        JFrame frame = new JFrame(config.getTitle());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        if (config.isFullscreen()) {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setUndecorated(true);
        }

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(config.getWidth(), config.getHeight()));
        panel.setLayout(null);

        Canvas canvas = new Canvas();
        canvas.setBounds(0, 0, config.getWidth(), config.getHeight());
        panel.add(canvas);
        canvas.setIgnoreRepaint(true);

        frame.pack();
        frame.setSize(frame.getPreferredSize());
        frame.setResizable(false);
        frame.setVisible(true);

        Keyboard keyboard = new Keyboard();
        Mouse mouse = new Mouse();
        canvas.addKeyListener(keyboard);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        canvas.addMouseWheelListener(mouse);
        canvas.requestFocus();

        canvas.createBufferStrategy(2);

        spriteStore = new SpriteStore(new AwtSpriteLoader());
        display = new AwtDisplay(frame, canvas);
        input = new Input(keyboard, mouse);
        initialized = true;
    }

    @Override
    public Input getInput() {
        checkInitialized();
        return input;
    }

    @Override
    public Display geDisplay() {
        checkInitialized();
        return display;
    }

    @Override
    public SpriteStore getSpriteStore() {
        checkInitialized();
        return spriteStore;
    }

    @Override
    public Engine buildEngine() {
        checkInitialized();
        return new Engine(this);
    }

    private void checkInitialized() {
        if (!initialized) {
            throw new IllegalStateException("Platform is not initialized");
        }
    }
}
