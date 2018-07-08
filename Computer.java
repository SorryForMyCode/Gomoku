package Gomoku;

import java.util.Random;

class Computer {
    private char figure;
    private int lastStepX;
    private int lastStepY;


    Computer(char figure) {
        this.figure = figure;
    }

    char getFigure() {
        return figure;
    }

    int getLastStepX() {
        return lastStepX;
    }

    int getLastStepY() {
        return lastStepY;
    }

    void displayLastStep(){
        System.out.println("last step player(" + figure +
                ") : [" + getLastStepX() + "][" + getLastStepY() + "]");
    }

    void doStep(Cell cell){
        lastStepX = cell.getX();
        lastStepY = cell.getY();
    }

//    private Cell generateStep(Cell[][] desk){
//        Cell newCell;
//        Random random = new Random();
//        do{
//            newCell = new Cell(random.nextInt(18), random.nextInt(18), figure);
//        }while(!desk[newCell.getX()][newCell.getY()].isValid());
//        return newCell;
//    }

    void doStep(Cell[][] desk){
        Cell newCell;
        Random random = new Random();
        do{
            if(check(desk)) return;
            else {
                newCell = new Cell(random.nextInt(18), random.nextInt(18), figure);
                doStep(newCell);
            }

        }while(!desk[newCell.getX()][newCell.getY()].isValid());
    }

    private boolean check(Cell[][] desk){
        return checkDiagonal(desk) || checkHorizontal(desk) || checkVertical(desk);
    }

    private boolean checkDiagonal(Cell[][] desk){
        int size = desk.length;

        for(int i = 0; i < size; i++)
            if(leftToRightDiagonal(i, 0, desk)) return true;

        for (int i = 1; i < size; i++)
            if(leftToRightDiagonal(0, i, desk)) return true;

        for (int i = 0; i < size; i++)
            if(rightToLeftDiagonal(i, 0, desk)) return true;

        for (int i = 1; i < size; i++)
            if(rightToLeftDiagonal(size - 1, i, desk)) return true;

        return false;
    }

    private boolean leftToRightDiagonal(int x, int y, Cell[][] desk){
        int size = desk.length;
        Cell[] array = new Cell[5];

        for (int i = y, j = x; i < size - 4 && j < size - 4; i++, j++) {
            for(int k = 0; k < 5; k++)
                array[k] = desk[i + k][j + k];

            SetOfCell set = new SetOfCell(array);
            Cell newStep = set.findStep(figure);
            if(newStep != null){
                doStep(newStep);
                return true;
            }

        }
        return false;
    }

    private boolean rightToLeftDiagonal(int x, int y, Cell[][] desk){
        int size = desk.length;
        Cell[] array = new Cell[5];

        for (int i = y, j = x; i < size - 4 && j >= 4; i++, j--) {
            for(int k = 0; k < 5; k++)
                array[k] = desk[i + k][j - k];

            SetOfCell set = new SetOfCell(array);
            Cell newStep = set.findStep(figure);
            if(newStep != null){
                doStep(newStep);
                return true;
            }
        }
        return false;
    }

    private boolean checkVertical(Cell[][] desk){
        int size = desk.length;
        Cell[] array = new Cell[5];

        for(int i = 0; i < size; i++){ //2coord
            for (int j = 0; j < size - 4; j++) { //1coord

                for(int k = 0; k < 5; k++)
                    array[k] = desk[i][j + k];

                SetOfCell set = new SetOfCell(array);
                Cell newStep = set.findStep(figure);
                if(newStep != null){
                    doStep(newStep);
                    return true;
                }

            }
        }
        return false;
    }

    private boolean checkHorizontal(Cell[][] desk){
        int size = desk.length;
        Cell[] array = new Cell[5];

        for(int i = 0; i < size; i++){ //1coord
            for (int j = 0; j < size - 4; j++) { //2coord
                for(int k = 0; k < 5; k++)
                    array[k] = desk[j + k][i];

                SetOfCell set = new SetOfCell(array);
                Cell newStep = set.findStep(figure);
                if(newStep != null){
                    doStep(newStep);
                    return true;
                }
            }
        }
        return false;
    }

}
