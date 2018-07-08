package Gomoku;

public class Board {
    private String[][] desk;

    public Board() {
        desk = new String[19][19];
        for(int i = 0; i < desk.length; i++)
            for(int j = 0; j < desk.length; j++)
                desk[i][j] = ".";
    }

    public String[][] getDesk() {
        return desk;
    }

    public void doStep(String figure, int x, int y) throws StepError {
        if(validCell(x,y))
            desk[y][x] = figure;
        else throw new StepError(x, y);
    }

    private boolean validCell(int x, int y){
        return x >= 0 && x <= 18 &&
                y >= 0 && y <= 18 &&
                desk[y][x].equals(".");
    }

    public void display(){
        System.out.println("-------------------------------------");
        for(int i = 0; i < desk.length; i++) {
            for (int j = 0; j < desk.length; j++)
                System.out.print(desk[i][j] + " ");

            System.out.println();
        }
        System.out.println("-------------------------------------");
    }

    public boolean isFull(){
        for (int i = 0; i < desk.length; i++)
            for (int j = 0; j < desk.length; j++)
                if(desk[i][j].equals(".")) return false;

        return true;
    }

    public boolean checkVictory(String figure){
        return checkDiagonal(figure) || checkHorizontal(figure) || checkVertical(figure);
    }

    private boolean checkDiagonal(String figure){
        for(int i = 0; i < desk.length; i++)
            if(leftToRightDiagonal(i, 0, figure)) return true;

        for (int i = 1; i < desk.length; i++)
            if(leftToRightDiagonal(0, i, figure)) return true;

        for (int i = 0; i < desk.length; i++)
            if(rightToLeftDiagonal(i, 0, figure)) return true;

        for (int i = 1; i < desk.length; i++)
            if(leftToRightDiagonal(desk.length - 1, i, figure)) return true;

        return false;
    }

    private boolean leftToRightDiagonal(int x, int y, String figure){
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

    private boolean rightToLeftDiagonal(int x, int y, String figure){
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

    private boolean checkVertical(String figure){
        int count = 0 , maxCount = 0;
        for(int i = 0; i < desk.length; i++){ //2coord
            for (int j = 0; j < desk.length; j++) { //1coord
                if(desk[j][i].equals(figure)) count++;
                if(!desk[j][i].equals(figure) && count != 0 || j == desk.length - 1){
                    if(count > maxCount) maxCount = count;
                    count = 0;
                }
            }
        }
        return maxCount >= 5;
    }

    private boolean checkHorizontal(String figure){
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

class StepError extends Exception{
    public StepError(int x, int y) {
        super("Invalid step by: [" + x + "][" + y + "] !");
    }
}
