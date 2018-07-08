package Gomoku;

import java.util.Scanner;

class Player {
    private char figure;
    private int lastStepX;
    private int lastStepY;

    Player(char figure) {
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

    void doStep(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("x: ");
        lastStepX = scanner.nextInt();
        System.out.print("y: ");
        lastStepY = scanner.nextInt();
    }
}
