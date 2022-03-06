package es.ieslavereda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MarcRamos
 */
public abstract class Tower extends Piece implements Serializable {
    /**
     * Contructor de la torre
     * @param cell celda donde esta
     * @param chessType el tipo de pieza
     */
    public Tower(Cell cell,ChessType chessType){
        super(cell,chessType);
    }
    /**
     * Nos da los movimientos de la torre
     * @return lista de cordenadas
     */
    public static List<Cordenada> getMovementsTower(Piece piece){
        //Cordenada[] nextMovements = new Cordenada[0];
        List<Cordenada> nextMovements = new ArrayList<>();
        Board board = piece.cell.getBoard();
        Cordenada position = piece.cell.getCordenada();
        boolean continua = true;
        Cordenada aux;
        aux = position;
        do {
            aux = aux.up();
            if (canMoveTo(aux,piece)){
                nextMovements.add(aux);
            } else {
                continua = false;
            }
            if (board.containsCellAt(aux) && board.containsPieceAt(aux) && board.getCellAt(aux).getPiece().getColor()!=piece.getColor()){
                continua = false;
            }
        } while (continua==true);
        //Right
        continua = true;
        aux = position;
        do {
            aux = aux.right();
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
            aux = aux.left();
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
            aux = aux.down();
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
     * Nos da los siguientes movimientos de la torre
     * @return lista de cordenadas
     */
    @Override
    public List<Cordenada> getNextMovements(){
        return getMovementsTower(this);

    }
    /**
     * Nos dice si la torre se puede mover a la cordenada pasada
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
