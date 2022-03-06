package es.ieslavereda.model;

import java.io.Serializable;

public enum ChessType implements Serializable {
    WHITE_KING("♕", PieceColor.WHITE),
    BLACK_KING("♕", PieceColor.BLACK),
    WHITE_QUEEN("♔", PieceColor.WHITE),
    BLACK_QUEEN("♔", PieceColor.BLACK),
    WHITE_TOWER("♖", PieceColor.WHITE),
    BLACK_TOWER("♖", PieceColor.BLACK),
    WHITE_BISHOP("♗", PieceColor.WHITE),
    BLACK_BISHOP("♗", PieceColor.BLACK),
    WHITE_PAWN("♙", PieceColor.WHITE),
    BLACK_PAWN("♙", PieceColor.BLACK),
    WHITE_KNIGHT("♘", PieceColor.WHITE),
    BLACK_KNIGHT("♘", PieceColor.BLACK);

    private String shape;
    private PieceColor color;

    ChessType(String shape,PieceColor color){
        this.shape = shape;
        this.color = color;
    }

    public String getShape(){
        return shape;
    }

    public PieceColor getColor(){
        return color;
    }
}
