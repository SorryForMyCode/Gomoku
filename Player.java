package Gomoku;

import java.util.Scanner;

public class Player {
    private String figure;
    private int lastStepX;
    private int lastStepY;

    public Player(String figure) {
        this.figure = figure;
    }

    public String getFigure() {
        return figure;
    }

    public int getLastStepX() {
        return lastStepX;
    }

    public int getLastStepY() {
        return lastStepY;
    }

    public void displayLastStep(){
        System.out.println("last step player(" + figure +
                ") : [" + getLastStepX() + "][" + getLastStepY() + "]");
    }

    public void doStep(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("x: ");
        lastStepX = scanner.nextInt();
        System.out.print("y: ");
        lastStepY = scanner.nextInt();
    }
}
