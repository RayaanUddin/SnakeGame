public class Snake {
    private int length; // Length of the snake (in blocks)
    final private int color;
    private double speedMultiplier; // Speed multiplier (0<speedMultiplier<1)
    private int direction; // 0 = up, 1 = right, 2 = down, 3 = left

    public Snake(int length, int color, double speedMultiplier, int direction) {
        this.length = length;
        this.color = color;
        this.speedMultiplier = speedMultiplier;
        this.direction = direction;
    }

    public Snake(int length, int color, double speedMultiplier) {
        this.length = length;
        this.color = color;
        this.speedMultiplier = speedMultiplier;
        this.direction = 1;
    }

    public int getDirection() {
        return direction;
    }

    public int setDirection(int direction) {
        this.direction = direction;
        return direction;
    }

    public int getLength() {
        return length;
    }

    public int setLength(int length) {
        this.length = length;
        return length;
    }

    public int increaseLength(int amount) {
        this.length += amount;
        return length;
    }

    public int getColor() {
        return color;
    }

    public double getSpeedMultiplier() {
        return speedMultiplier;
    }

    public double setSpeedMultiplier(double speedMultiplier) {
        this.speedMultiplier = speedMultiplier;
        return speedMultiplier;
    }
}
