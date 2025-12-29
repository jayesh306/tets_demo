package com.cg.udemy.tictactoe.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int size;
    private PlayingPiece[][] piece;

    public int getSize() {
        return size;
    }

    public PlayingPiece[][] getPiece() {
        return piece;
    }


    public Board(int size){
        this.size=size;
        piece = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int col, PlayingPiece playingPiece){
        if(piece[row][col] != null){
            return false;
        }else{
            piece[row][col] = playingPiece;
            return true;
        }
    }
    public List<CellPosition> getFreeCells(){
        List<CellPosition> freeCells = new ArrayList<>();
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(piece[i][j] == null){
                    freeCells.add(new CellPosition(i,j));
                }
            }
        }
        return freeCells;
    }
    public void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(piece[i][j] != null){
                    System.out.print(piece[i][j].getType().name()+ "   ");
                }else{
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }
}
