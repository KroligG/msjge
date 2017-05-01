package my.game.state;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import my.engine.core.Engine;
import my.engine.display.Canvas;
import my.engine.input.Keyboard;
import my.engine.state.GameState;

public class MenuState implements GameState {
    private static final String START_ACTION = "Start";
    private static final String EXIT_ACTION = "Exit";
    private String status;
    private Engine engine;

    private List<String> menu = new ArrayList<>();
    private int currenElem;

    public MenuState(String status) {
        this.status = status;
    }

    @Override
    public void init(Engine e) {
        this.engine = e;

        menu.add(START_ACTION);
        menu.add(EXIT_ACTION);
    }

    @Override
    public void destroy(Engine e) {
        menu.clear();
    }

    @Override
    public void render(Canvas c) {
        int fontSize = 34;
        int margin = fontSize / 2;
        int xMargin = 20;
        int yMargin = 50;

        c.drawText(status, xMargin, yMargin, "#ffffff", fontSize);

        for (int i = 0; i < menu.size(); i++) {
            c.drawText(menu.get(i), xMargin, yMargin + margin * 2 + (i + 1) * (fontSize + margin), i == currenElem ? "#ff0000" : "#ffffff", fontSize);
        }
    }

    @Override
    public void update(double delta) {
        Keyboard keyboard = engine.getPlatform().getInput().getKeyboard();

        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)) {
            if (++currenElem == menu.size()) {
                currenElem = 0;
            }
        } else if (keyboard.isKeyPressed(KeyEvent.VK_UP)) {
            if (--currenElem == -1) {
                currenElem = menu.size() - 1;
            }
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
            menuSelected(menu.get(currenElem));
        }
    }

    private void menuSelected(String action) {
        switch (action) {
            case START_ACTION:
                engine.setGameState(new InvadersState());
                return;
            case EXIT_ACTION:
                System.exit(0);
        }
    }
}
