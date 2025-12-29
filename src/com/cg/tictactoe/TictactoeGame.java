package com.cg.udemy.tictactoe;

import com.cg.udemy.tictactoe.enums.GameStatus;
import com.cg.udemy.tictactoe.enums.PieceType;
import com.cg.udemy.tictactoe.model.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TictactoeGame {
    Deque<Player> players;
    Board gameBoard;
    Player winner;

    public void initializeGame(){
        //Creating 2 new players
        players = new LinkedList<>();
        PlayingPieceX crossPiece = new PlayingPieceX();
        Player player1 = new Player("Player 1",crossPiece);

        PlayingPieceO noughtPiece = new PlayingPieceO();
        Player player2 =  new Player("Player 2",noughtPiece);

        players.add(player1);
        players.add(player2);
        gameBoard = new Board(3);
    }
    public GameStatus startGame(){
        boolean noWinner = true;
        while(noWinner){
            Player currentPlayer = players.removeFirst();
            gameBoard.printBoard();
            List<CellPosition> freeCells = gameBoard.getFreeCells();
            if(freeCells.isEmpty()){
                noWinner = false;
                continue;
            }

            //Read the user input
            System.out.println("Player "+currentPlayer.getName()+" - Please enter [row, col]: ");
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            String[] values = s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputCol = Integer.valueOf(values[1]);

            boolean validMove = gameBoard.addPiece(inputRow, inputCol, currentPlayer.getPlayingPiece());
            if(!validMove){
                //Invalid Move :  Players cannot insert piece into this cell
                System.out.println("Incorrect move! Choose another cell");
             //   gameBoard.printBoard();
                players.addFirst(currentPlayer);
                continue;
            }
            players.addLast(currentPlayer);  //Add the player to the end of the queue
            boolean isWinner = checkForWinner(inputRow, inputCol, currentPlayer.getPlayingPiece().getType());
            if(isWinner){
                gameBoard.printBoard();
                winner = currentPlayer;
                return GameStatus.WIN;
            }
        }
        return GameStatus.DRAW;
    }
    public boolean checkForWinner(int row,  int col, PieceType pieceType){
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagonalMatch = true;
        boolean antiDiagonalMatch = true;

        //Check row
        for (int i=0;i<gameBoard.getSize();i++){
            if(gameBoard.getPiece()[row][i] == null || gameBoard.getPiece()[row][i].getType() != pieceType){
                rowMatch = false;
                break;
            }
        }
        //Check col
        for (int i=0;i<gameBoard.getSize();i++){
            if(gameBoard.getPiece()[i][col] == null || gameBoard.getPiece()[i][col].getType() != pieceType){
                colMatch = false;
                break;
            }
        }
        //Check diagonal
        for(int i=0,j=0;i<gameBoard.getSize();i++,j++){
            if(gameBoard.getPiece()[i][j] == null || gameBoard.getPiece()[i][j].getType() != pieceType){
                diagonalMatch = false;
                break;
            }
        }
        for(int i=gameBoard.getSize()-1,j=0;i>=0;i--,j++){
            if(gameBoard.getPiece()[i][j] == null || gameBoard.getPiece()[i][j].getType() != pieceType){
                antiDiagonalMatch = false;
                break;
            }
        }
        return rowMatch || colMatch || diagonalMatch || antiDiagonalMatch;

    }
}
