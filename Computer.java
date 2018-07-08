package Gomoku;

import javafx.util.Pair;

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

    private boolean finish(Cell[][] desk){
        checkVertical(4, figure, desk);
        return checkDiagonal(figure, desk) || checkHorizontal(figure, desk) ;
    }

    private boolean validPair(Pair<Integer, Integer> pair, Cell[][] desk){
        return validCell(pair.getKey(), pair.getValue(), desk);
    }

    private boolean validCell(int x, int y, Cell[][] desk){
        return x >= 0 && x <= 18 &&
                y >= 0 && y <= 18 &&
                desk[y][x].equals(".");
    }

    private boolean checkDiagonal(char figure, Cell[][] desk){
        for(int i = 0; i < desk.length; i++)
            if(leftToRightDiagonal(i, 0, figure, desk)) return true;

        for (int i = 1; i < desk.length; i++)
            if(leftToRightDiagonal(0, i, figure, desk)) return true;

        for (int i = 0; i < desk.length; i++)
            if(rightToLeftDiagonal(i, 0, figure, desk)) return true;

        for (int i = 1; i < desk.length; i++)
            if(leftToRightDiagonal(desk.length - 1, i, figure, desk)) return true;

        return false;
    }

    private boolean leftToRightDiagonal(int x, int y, char figure, String[][] desk){
        int count = 0, maxCount = 0;
        for (int i = y, j = x; i < desk.length && j < desk.length; i++, j++) {
            if(desk[i][j].equals(figure)) count++;
            if(!desk[i][j].equals(figure) &&
                    count != 0 ||
                    i == desk.length - 1 ||
                    j == desk.length - 1){
                if(count > maxCount) maxCount = count;
                count = 0;
            }
        }
        return maxCount >= 5;
    }

    private boolean rightToLeftDiagonal(int x, int y, String figure, String[][] desk){
        int count = 0, maxCount = 0;
        for (int i = y, j = x; i < desk.length && j >= 0; i++, j--) {
            if(desk[i][j].equals(figure)) count++;
            if(!desk[i][j].equals(figure) &&
                    count != 0 ||
                    i == desk.length - 1 ||
                    j == 0){
                if(count > maxCount) maxCount = count;
                count = 0;
            }
        }
        return maxCount >= 5;
    }

    private Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> checkVertical(int numberCount, String figure, String[][] desk){
        Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> pair = null;
        Pair<Integer, Integer> first = null, last = null;
        int count = 0 , maxCount = 0;
        boolean in = false;

        for(int i = 0; i < desk.length; i++){ //2coord
            for (int j = 0; j < desk.length; j++) { //1coord
                if(desk[j][i].equals(figure)){
                    count++;
                }
                if(!desk[j][i].equals(figure) && count != 0 || j == desk.length - 1){
                    if(count > maxCount){
                        last = new Pair<>(i, j);
                        maxCount = count;
                    }
                    count = 0;
                }
            }
        }
        if(last != null) first = new Pair<>(last.getKey(), last.getValue() - maxCount); // maxCount -> 4 - 3

        if(maxCount >= numberCount){
            return pair;
        }

        return null;
    }

    private boolean checkHorizontal(String figure, String[][] desk){
        int count = 0 , maxCount = 0;
        for(int i = 0; i < desk.length; i++){ //1coord
            for (int j = 0; j < desk.length; j++) { //2coord
                if(desk[i][j].equals(figure)) count++;
                if(!desk[i][j].equals(figure) && count != 0 || j == desk.length - 1){
                    if(count > maxCount) maxCount = count;
                    count = 0;
                }
            }
        }
        return maxCount >= 5;
    }
}
