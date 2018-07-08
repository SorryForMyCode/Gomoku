package Gomoku;

public class Cell {
    private int x;
    private int y;
    private char figure;

    public Cell(int x, int y, char figure) {
        this.x = x;
        this.y = y;
        this.figure = figure;
    }

    public Cell() {
        x = -1;
        y = -1;
        figure = '.';
    }

    public boolean isValid(){
        return x >= 0 && x <= 18 &&
                y >= 0 && y <= 18 &&
                figure == '.';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getFigure() {
        return figure;
    }

    public void setFigure(char figure) {
        this.figure = figure;
    }
}
