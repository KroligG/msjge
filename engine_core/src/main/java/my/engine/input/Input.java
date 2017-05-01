package my.engine.input;

public class Input {
    private Keyboard keyboard;
    private Mouse mouse;

    public Input(Keyboard keyboard, Mouse mouse) {
        this.keyboard = keyboard;
        this.mouse = mouse;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }
}
