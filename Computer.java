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

    void doStep(Cell[][] desk){
        Cell cell = generateStep(desk);
        lastStepX = cell.getX();
        lastStepY = cell.getY();
    }

    private Cell generateStep(Cell[][] desk){
        Cell newCell;
        Random random = new Random();
        do{
            newCell = new Cell(random.nextInt(18), random.nextInt(18), figure);
        }while(!desk[newCell.getX()][newCell.getY()].isValid());
        return newCell;
    }

}
