package my.engine.physics;

public abstract class Entity {

    protected double x;

    protected double y;

    protected double dx;

    protected double dy;

    protected double width;

    protected double height;

    public Entity(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move(double delta) {
        x += delta * dx;
        y += delta * dy;
    }

    public void collidedWith(Entity other) {
    }

    public boolean collidesWith(Entity other) {
        double tw = this.width;
        double th = this.height;
        double ow = other.width;
        double oh = other.height;
        if (ow <= 0 || oh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        double tx = this.x;
        double ty = this.y;
        double ox = other.x;
        double oy = other.y;
        ow += ox;
        oh += oy;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((ow < ox || ow > tx) &&
                (oh < oy || oh > ty) &&
                (tw < tx || tw > ox) &&
                (th < ty || th > oy));
    }

    public void setHorizontalMovement(double dx) {
        this.dx = dx;
    }

    public void setVerticalMovement(double dy) {
        this.dy = dy;
    }

    public double getHorizontalMovement() {
        return dx;
    }

    public double getVerticalMovement() {
        return dy;
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
