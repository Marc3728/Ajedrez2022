package es.ieslavereda.model.dynamicStructure;

import es.ieslavereda.model.ChessType;
import es.ieslavereda.model.IDeletePieceManager;
import es.ieslavereda.model.Piece;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * @author MarcRamos
 */
public class MyStore implements IDeletePieceManager, Serializable {
    private List<Piece> pieces;

    /**
     * Contructor de MyStore
     */
    public MyStore(){
        this.pieces = new LinkedList<>();
    }

    /**
     * Se encarga de meter una nueva pieza al almacen
     * @param piece pieza a meter
     */
    @Override
    public void add(Piece piece) {
        pieces.add(piece);
    }

    /**
     * Se encarga de cojer la primera pieza de el almacen
     * @return la primera pieza
     */
    @Override
    public Piece getFirst() {
        return pieces.get(0);
    }

    /**
     * Se encarga de eliminar de el almacen una pieza pasada
     * @param piece pieza a quitar
     */
    @Override
    public void remove(Piece piece) {
        pieces.remove(piece);
    }

    /**
     * Se encarga de contar las piezas que hay en el almacen de el tipo de pieza pasado
     * @param chessType tipo de pieza
     * @return numero de piezas de el tipo pasado
     */
    @Override
    public int count(ChessType chessType) {
        //return (int) pieces.stream().filter(p->p.getChessType()==chessType).count();
        int suma = 0;
        for (Piece piece : pieces){
            if (piece.getChessType()==chessType){
                suma++;
            }
        }
        return suma;
    }
}
