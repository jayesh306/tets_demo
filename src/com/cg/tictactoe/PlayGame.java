package com.cg.udemy.tictactoe;

import com.cg.udemy.tictactoe.enums.GameStatus;

public class PlayGame {
    public static void main(String[] args) {
        TictactoeGame game = new TictactoeGame();
        game.initializeGame();
        GameStatus status = game.startGame();
        System.out.println("Game Over!!");
        switch(status){
            case WIN -> System.out.println(game.winner.getName()+" won the game");
            case DRAW -> System.out.println("Its a draw");
            default -> System.out.println("Game ends");
        }
    }
}
