package es.ieslavereda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MarcRamos
 */
public abstract class Bishop extends Piece implements Serializable {
    /**
     * Contructor de el alfil
     * @param cell celda en la que esta
     * @param chessType tipo de pieza
     */
    public Bishop(Cell cell,ChessType chessType){
        super(cell,chessType);
    }
    /**
     * Nos da los movimientos de el alfil
     * @return lista de cordenadas
     */
    public static List<Cordenada> getMovementsBishop(Piece piece){
        List<Cordenada> nextMovements = new ArrayList<>();
        Board board = piece.cell.getBoard();
        Cordenada position = piece.cell.getCordenada();
        boolean continua = true;
        Cordenada aux;
        aux = position;
        do {
            aux = aux.upRight();
            if (canMoveTo(aux,piece)){
                nextMovements.add(aux);
            } else {
                continua = false;
            }
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=piece.getColor()){
                continua = false;
            }
        } while (continua==true);
        //DownRight
        continua = true;
        aux = position;
        do {
            aux = aux.downRight();
            if (canMoveTo(aux,piece)){
                nextMovements.add(aux);
            } else {
                continua = false;
            }
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=piece.getColor()){
                continua = false;
            }
        } while (continua==true);
        //DownLeft
        continua = true;
        aux = position;
        do {
            aux = aux.downLeft();
            if (canMoveTo(aux,piece)){
                nextMovements.add(aux);
            } else {
                continua = false;
            }
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=piece.getColor()){
                continua = false;
            }
        } while (continua==true);
        //UpLeft
        continua = true;
        aux = position;
        do {
            aux = aux.upLeft();
            if (canMoveTo(aux,piece)){
                nextMovements.add(aux);
            } else {
                continua = false;
            }
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=piece.getColor()){
                continua = false;
            }
        } while (continua==true);
        return nextMovements;
    }
    /**
     * Nos da los siguientes movimientos de el alfil
     * @return lista de cordenada
     */
    @Override
    public List<Cordenada> getNextMovements(){
        return getMovementsBishop(this);

    }
    /**
     * Nos dice si el alfil se puede mover a la cordenada pasada
     * @param aux cordenada a la que se quiere mover
     * @return boleano confirmando si se puede mover o no a la cordenada pasada
     */
    public static boolean canMoveTo(Cordenada aux,Piece piece){
        Board board = piece.cell.getBoard();
        return board.containsCellAt(aux) && !board.containsPieceAt(aux) ||
                board.containsCellAt(aux) && board.containsPieceAt(aux) &&
                        board.getCellAt(aux).getPiece().getColor()!=piece.getColor();
    }
}
