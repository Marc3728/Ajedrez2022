package es.ieslavereda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MarcRamos
 */
public abstract class King extends Piece implements Serializable {
    /**
     * Contructor de el rey
     * @param cell celda en la que esta
     * @param chessType tipo de pieza
     */
    public King(Cell cell, ChessType chessType){
        super(cell,chessType);
    }

    /**
     * Nos da los siguientes movimientos de el rey
     * @return lista de cordenada
     */
    @Override
    public List<Cordenada> getNextMovements(){
        List<Cordenada> nextMovements = new ArrayList<>();
        Board board = cell.getBoard();
        Cordenada position = cell.getCordenada();
        Cordenada aux;
        aux = position.up();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.upRight();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.right();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.downRight();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.down();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.downLeft();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.left();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.upLeft();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        return nextMovements;
    }

    /**
     * Nos dice si el rey se puede mover a la cordenada pasada
     * @param aux cordenada a la que se quiere mover
     * @return boleano confirmando si se puede mover o no a la cordenada pasada
     */
    public boolean canMoveTo(Cordenada aux){
        Board board = cell.getBoard();
        return board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) &&
                        board.getCellAt(aux).getPiece().getColor()!=getColor();
    }
}
