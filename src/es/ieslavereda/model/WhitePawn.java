package es.ieslavereda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MarcRamos
 */
public class WhitePawn extends Pawn implements Serializable {
    /**
     * Contructor de el peon blanco
     * @param cell celda en la que esta
     */
    public WhitePawn (Cell cell) {
        super(cell,ChessType.WHITE_PAWN);
        place();
    }
    /**
     * Nos da los siguientes movimientos de el peon blanco
     * @return lista de cordenada
     */
    @Override
    public List<Cordenada> getNextMovements() {
        List<Cordenada> nextMovements = new ArrayList<>();
        Board board = cell.getBoard();
        Cordenada position = cell.getCordenada();
        Cordenada aux;
        aux = position;
        if (board.containsCellAt(aux.up()) && !board.containsPieceAt(aux.up())){
            nextMovements.add(aux.up());
            if (position.getNumber()==7 && !board.containsPieceAt(aux.up().up())){
                nextMovements.add(aux.up().up());
            }
        }
        //Kill
        aux = position.upLeft();
        if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=getColor()){
            nextMovements.add(aux);
        }
        aux = position.upRight();
        if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=getColor()){
            nextMovements.add(aux);
        }
        return nextMovements;
    }
}
