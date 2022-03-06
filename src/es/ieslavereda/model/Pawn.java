package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author MarcRamos
 */
public abstract class Pawn extends Piece implements Serializable {
    /**
     * Contructor de el peon
     * @param cell celda en la que esta
     * @param chessType el tipo de pieza
     */
    public Pawn(Cell cell,ChessType chessType){
        super(cell,chessType);
    }
}
