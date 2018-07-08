package Gomoku;

public class SetOfCell {
    private int size = 5;
    private Cell[] cells;
    private Cell first = null;
    private Cell last = null;

    public SetOfCell() {
        cells = new Cell[size];

        for (int i = 0; i < size; i++)
            cells[i] = new Cell();
    }

    public SetOfCell(int size) {
        this.size = size;
    }

    public int howManyContains(char figure){
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

    public Cell blockOrFinish(char figure){
        Cell cell = fourFromFive(figure);
        return cell;
    }

    private Cell fourFromFive(char figure){
        if(howManyContains(figure) == size - 1 && howManyContains('.') == 1){
            howManyNearby(figure);
            return first != null ? first : last;
        }
        return null;
    }
}
