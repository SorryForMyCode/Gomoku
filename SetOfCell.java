package Gomoku;

import java.util.Random;

class SetOfCell {
    private int size = 5;
    private Cell[] cells;
    private Cell first = null;
    private Cell last = null;

    SetOfCell() {
        cells = new Cell[size];

        for (int i = 0; i < size; i++)
            cells[i] = new Cell();
    }

    SetOfCell(Cell[] array) {
        cells = new Cell[size];

        for (int i = 0; i < size; i++)
            cells[i] = new Cell(array[i].getX(), array[i].getY(),array[i].getFigure());
    }

    int howManyContains(char figure){
        int count = 0;

        for (Cell c : cells)
            if(c.getFigure() == figure) {
                count++;
            }
        return count;
    }

    private int howManyNearby(char figure){
        int count = 0, maxCount = 0;
        Cell prev = null, next = null;

        for (Cell c : cells) {
            if (c.getFigure() == figure)
                count++;
            else {
                if(count != 0) next = c;

                if(maxCount < count){
                    maxCount = count;
                    first = prev;
                    last = next;
                }

                count = 0;
            }
            if(count == 0) prev = c;
        }
        return maxCount;
    }

    Cell findStep(char figure){
        if(howManyContains('.') == 0) return null;

        Cell cell = finish(figure);
        if(cell != null) return cell;
        cell = block(figure);
        if(cell != null) return cell;

        return cell;
    }

    Cell block(char figure){
        return threeFromFive(figure);
    }

    Cell finish(char figure){
        return fourFromFive(figure);
    }

    private Cell fourFromFive(char figure){
        if(howManyContains(figure) == size - 1 && howManyContains('.') == 1){
            howManyNearby(figure);
            return first != null ? first : last;
        }
        return null;
    }

    private Cell threeFromFive(char figure){
        figure = (figure == 'X') ? 'O' : 'X';
        if(howManyNearby(figure) >= 3 ) return first != null ? first : last;
        return null;
    }
}
