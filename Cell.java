package Gomoku;

class Cell {
    private int x;
    private int y;
    private char figure;

    Cell(int x, int y, char figure) {
        this.x = x;
        this.y = y;
        this.figure = figure;
    }

    Cell() {
        x = -1;
        y = -1;
        figure = '.';
    }

    boolean isValid(){
        return x >= 0 && x <= 18 &&
                y >= 0 && y <= 18 &&
                figure == '.';
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setX(int x) {
        this.x = x;
    }

    void setY(int y) {
        this.y = y;
    }

    char getFigure() {
        return figure;
    }

    void setFigure(char figure) {
        this.figure = figure;
    }

    @Override
    public String toString() {
        return String.valueOf(figure);
    }
}
