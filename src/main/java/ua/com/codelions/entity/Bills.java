package ua.com.codelions.entity;

public enum Bills {
    ONE (100),
    TWO (200),
    THREE (500);

    private int index;

    Bills(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }

}
