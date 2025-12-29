package com.cg.udemy.tictactoe.model;

public class Player {
    String name;
    PlayingPiece piece;
    public Player(String name, PlayingPiece piece){
        this.name=name;
        this.piece=piece;
    }
//    public void setName(String name){
//        this.name=name;
//    }
    public String getName(){
        return name;
    }
//    public void setPlayingPiece(PlayingPiece piece){
//        this.piece=piece;
//    }
    public PlayingPiece getPlayingPiece(){
        return piece;
    }
}
