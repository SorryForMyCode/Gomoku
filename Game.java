package Gomoku;

import java.util.Scanner;

public class Game {
    private Player player;
    private Computer computer;
    private Board board;
    private String whoFirst;

    public void startGame(boolean showBoard){
        createPlayersAndBoard();
        fight(showBoard);
    }

    private void fight(boolean showBoard){
        boolean xWin = false, oWin = false;
        do{
            if(whoFirst.equals("player")){
                doItUntilItWorksOutForPlayer();
                if(showBoard) board.display();
                if(verificationX()){
                    xWin = true; continue;
                }

                doItUntilWorksOutForComputer();
                if(showBoard) board.display();
                if(verificationO()){
                    oWin = true; continue;
                }
            } else {
                doItUntilWorksOutForComputer();
                if(showBoard) board.display();
                if(verificationX()){
                    xWin = true; continue;
                }
                doItUntilItWorksOutForPlayer();
                if(showBoard) board.display();
                if(verificationO()){
                    oWin = true; continue;
                }
            }
        }while (!board.isFull() && !xWin && !oWin);
        congratulations(xWin, oWin);
    }

    private void congratulations(boolean xWin, boolean oWin){
        if(xWin){
            if(whoFirst.equals("player"))
                System.out.println("Player won!");
            else
                System.out.println("Computer won!");

        } else if(oWin){
            if(whoFirst.equals("player"))
                System.out.println("Computer won!");
             else
                System.out.println("Player won!");
        } else
            System.out.println("Standoff");
    }

    private void doItUntilItWorksOutForPlayer(){
        boolean again = true;
        do{
            try {
                player.doStep();
                board.doStep(player.getFigure(), player.getLastStepX(), player.getLastStepY());
                again = false;
            } catch (StepError stepError) {
                System.out.println("Try again");
            }
        }while (again);
    }

    private void doItUntilWorksOutForComputer(){
        boolean again = true;
        do{
            try {
                computer.doStep(board.getDesk());
                board.doStep(computer.getFigure(), computer.getLastStepX(), computer.getLastStepY());
                again = false;
            } catch (StepError stepError) {
                System.out.println("Try again");
            }
        }while (again);
    }

    private boolean verificationX(){
        return board.checkVictory("X");
    }

    private boolean verificationO(){
        return board.checkVictory("O");
    }

    private void createPlayersAndBoard(){
        String playersFigure, computerFigure;

        playersFigure = chooseFigure();
        if(playersFigure == "X") {
            whoFirst = "player";
            computerFigure = "O";
        } else {
            computerFigure = "X";
            whoFirst = "computer";
        }

        player = new Player(playersFigure);
        computer = new Computer(computerFigure);
        board = new Board();
    }

    private String chooseFigure(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("What figure are you going to play?");
        System.out.println("Input <yes> if you want to play by <X>");
        String answer = scanner.nextLine();
        if(answer.equalsIgnoreCase("yes")) return "X";
        return "O";
    }
}
