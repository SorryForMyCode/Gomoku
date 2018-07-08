package Gomoku;

public class FirstAndLast {
    private Cell first;
    private Cell last;

    public FirstAndLast() {
        first = new Cell();
        last = new Cell();
    }

    public void setFirst(int x, int y) {
        first.setX(x);
        first.setY(y);
    }

    public void setLast(int x, int y) {
        last.setX(x);
        last.setY(y);
    }
}
