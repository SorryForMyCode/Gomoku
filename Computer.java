package Gomoku;

import javafx.util.Pair;

import java.util.Random;

public class Computer {
    private String figure;
    private int lastStepX;
    private int lastStepY;


    public Computer(String figure) {
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

    public void doStep(String[][] desk){
        Pair<Integer, Integer> pair = generateStep(desk);
        lastStepX = pair.getKey();
        lastStepY = pair.getValue();
    }

    private Pair<Integer, Integer> generateStep(String[][] desk){
        Pair<Integer, Integer> pair;
        Random random = new Random();
        do{
            pair = new Pair<>(random.nextInt(18), random.nextInt(18));
        }while(!validPair(pair, desk));
        return pair;
    }

    private boolean finish(String[][] desk){
        checkVertical(4, figure, desk);
        return checkDiagonal(figure, desk) || checkHorizontal(figure, desk) ;
    }

    private boolean validPair(Pair<Integer, Integer> pair, String[][] desk){
        return validCell(pair.getKey(), pair.getValue(), desk);
    }

    private boolean validCell(int x, int y, String[][] desk){
        return x >= 0 && x <= 18 &&
                y >= 0 && y <= 18 &&
                desk[y][x].equals(".");
    }

    private boolean checkDiagonal(String figure, String[][] desk){
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

    private boolean leftToRightDiagonal(int x, int y, String figure, String[][] desk){
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
