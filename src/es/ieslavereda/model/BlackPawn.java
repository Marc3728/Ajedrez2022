package es.ieslavereda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MarcRamos
 */
public class BlackPawn extends Pawn implements Serializable {
    /**
     * Contructor de el peon negro
     * @param cell celda en la que esta
     */
    public BlackPawn(Cell cell){
        super(cell, ChessType.BLACK_PAWN);
        place();
    }
    /**
     * Nos da los siguientes movimientos de el peon negro
     * @return lista de cordenada
     */
    @Override
    public List<Cordenada> getNextMovements() {
        List<Cordenada> nextMovements = new ArrayList<>();
        Board board = cell.getBoard();
        Cordenada position = cell.getCordenada();
        Cordenada aux;
        aux = position;
        if (board.containsCellAt(aux.down()) && !board.containsPieceAt(aux.down())){
            nextMovements.add(aux.down());
            if (position.getNumber()==2 && !board.containsPieceAt(aux.down().down())){
                nextMovements.add(aux.down().down());
            }
        }
        //Kill
        aux = position.downLeft();
        if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=getColor()){
            nextMovements.add(aux);
        }
        aux = position.downRight();
        if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=getColor()){
            nextMovements.add(aux);
        }
        return nextMovements;
    }
}
