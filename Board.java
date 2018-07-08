package Gomoku;

class Board {
    private Cell[][] desk;
    private int size = 19;

    Board() {
        desk = new Cell[size][size];
        for(int i = 0; i < size; i++)
            for(int j = 0; j < size; j++)
                desk[i][j] = new Cell(i, j, '.');
    }

    Cell[][] getDesk() {
        return desk;
    }

    void doStep( int x, int y, char figure) throws StepError {
        if(desk[x][y].isValid())
            desk[x][y].setFigure(figure);
        else throw new StepError(x, y);
    }

    void display(){
        System.out.println("-------------------------------------");
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++)
                System.out.print(desk[j][i].getFigure() + " ");
            System.out.println();
        }
        System.out.println("-------------------------------------");
    }

    boolean isFull(){
        for(Cell[] cells : desk)
            for(Cell cell : cells)
                if(cell.getFigure() == '.') return false;
        return true;
    }

    boolean checkVictory(char figure){
        return checkDiagonal(figure) || checkHorizontal(figure) || checkVertical(figure);
    }

    private boolean checkDiagonal(char figure){
        for(int i = 0; i < size; i++)
            if(leftToRightDiagonal(i, 0, figure)) return true;

        for (int i = 1; i < size; i++)
            if(leftToRightDiagonal(0, i, figure)) return true;

        for (int i = 0; i < size; i++)
            if(rightToLeftDiagonal(i, 0, figure)) return true;

        for (int i = 1; i < size; i++)
            if(rightToLeftDiagonal(size - 1, i, figure)) return true;

        return false;
    }

    private boolean leftToRightDiagonal(int x, int y, char figure){
        int count = 0, maxCount = 0;
        for (int i = y, j = x; i < size && j < size; i++, j++) {
            if(desk[i][j].getFigure() == figure) count++;

            if(desk[i][j].getFigure() != figure && count != 0 || i == size - 1 || j == size - 1){
                if(count > maxCount) maxCount = count;
                count = 0;
            }
        }
        return maxCount >= 5;
    }

    private boolean rightToLeftDiagonal(int x, int y, char figure){
        int count = 0, maxCount = 0;
        for (int i = y, j = x; i < size && j >= 0; i++, j--) {
            if(desk[i][j].getFigure() == figure) count++;
            if(desk[i][j].getFigure() != figure &&
                    count != 0 ||
                    i == size - 1 ||
                    j == 0){
                if(count > maxCount) maxCount = count;
                count = 0;
            }
        }
        return maxCount >= 5;
    }

    private boolean checkVertical(char figure){
        int count = 0 , maxCount = 0;
        for(int i = 0; i < size; i++){ //2coord
            for (int j = 0; j < size; j++) { //1coord
                if(desk[i][j].getFigure() == figure) count++;
                if(desk[i][j].getFigure() != figure && count != 0 || j == size - 1){
                    if(count > maxCount) maxCount = count;
                    count = 0;
                }
            }
        }
        return maxCount >= 5;
    }

    private boolean checkHorizontal(char figure){
        int count = 0 , maxCount = 0;
        for(int i = 0; i < size; i++){ //1coord
            for (int j = 0; j < size; j++) { //2coord
                if(desk[i][j].getFigure() == figure) count++;
                if(desk[i][j].getFigure() != figure && count != 0 || j == size - 1){
                    if(count > maxCount) maxCount = count;
                    count = 0;
                }
            }
        }
        return maxCount >= 5;
    }
}

class StepError extends Exception{
    StepError(int x, int y) {
        super("Invalid step by: [" + x + "][" + y + "] !");
    }
}
