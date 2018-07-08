package Gomoku;

public class Cell {
    private int x;
    private int y;
    private String figure;

    public Cell(int x, int y, String figure) {
        this.x = x;
        this.y = y;
        this.figure = figure;
    }

    public Cell() {
        x = -1;
        y = -1;
        figure = ".";
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

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }
}
