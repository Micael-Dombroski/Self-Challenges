package domain;

public enum Quadrant {
    FIRST_QUADRANT(1),
    SECOND_QUADRANT(2),
    THIRD_QUADRANT(3),
    FOURTH_QUADRANT(4);
    private final int VALUE;
    Quadrant(int value) {
        this.VALUE = value;
    }
    public int getValue() {
        return VALUE;
    }
}
