package com.cg.udemy.tictactoe.model;

import com.cg.udemy.tictactoe.enums.PieceType;

public class PlayingPiece {
    PieceType type;
    public PlayingPiece(PieceType type){
        this.type=type;
    }

    public PieceType getType() {
        return type;
    }
}
