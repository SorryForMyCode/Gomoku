package Gomoku;

public class Main {
    public static void main(String[] args) {
//        Board board = new Board();
//        board.display();
//        try {
//            board.doStep("x",5, 0);
//            board.doStep("x",4, 1);
//            board.doStep("x",3, 2);
//            board.doStep("x",2, 3);
//            board.doStep("x",1, 4);
//            board.doStep("x",0, 6);
//            board.doStep("x",0, 7);
//            board.doStep("x",0, 8);
//
//        } catch (StepError stepError) {
//            stepError.printStackTrace();
//            System.out.println("Try to make a step again!");
//        }
//
//        System.out.println(board.checkVictory("x"));
//        board.display();
        Game game = new Game();
        game.startGame(true);
    }
}
