package es.ieslavereda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MarcRamos
 */
public abstract class Knight extends Piece implements Serializable {
    /**
     * Contructor de el caballo
     * @param cell celda donde esta
     * @param chessType el tipo de pieza
     */
    public Knight(Cell cell,ChessType chessType){
        super(cell,chessType);
    }
    /**
     * Nos da los siguientes movimientos de el caballo
     * @return lista de cordenada
     */
    @Override
    public List<Cordenada> getNextMovements(){
        //Cordenada[] nextMovements = new Cordenada[0];
        List<Cordenada> nextMovements = new ArrayList<>();
        Board board = cell.getBoard();
        Cordenada position = cell.getCordenada();
        Cordenada aux;
        //Up
        aux = position.up().upLeft();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.up().upRight();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.upLeft().left();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.upRight().right();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        //Right
        aux = position.right().upRight();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.right().downRight();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.upRight().up();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.downRight().down();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        //Left
        aux = position.left().upLeft();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.left().downLeft();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.upLeft().up();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.downLeft().down();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        //Down
        aux = position.down().downLeft();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.down().downRight();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.downLeft().left();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        aux = position.downRight().right();
        if (canMoveTo(aux)){
            nextMovements.add(aux);
        }
        return nextMovements;
    }
    /**
     * Nos dice si el caballo se puede mover a la cordenada pasada
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
