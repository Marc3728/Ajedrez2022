package es.ieslavereda.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MarcRamos
 */
public abstract class Queen extends Piece implements Serializable {
    /**
     * Contructor de la reina
     * @param cell celda donde esta
     * @param chessType el tipo de pieza
     */
    public Queen(Cell cell,ChessType chessType){
        super(cell,chessType);
    }
    /**
     * Nos da los siguientes movimientos de la reina
     * @return lista de cordenadas
     */
    public List<Cordenada> getNextMovements(){
        List<Cordenada> nextMovementsb = Bishop.getMovementsBishop(this);
        List<Cordenada> nextMovementst = Tower.getMovementsTower(this);
        List<Cordenada> nextMovements = new ArrayList<>();
        for (int i = 0;i<nextMovementsb.size();i++){
            nextMovements.add(nextMovementsb.get(i));
        }
        for (int b = 0;b<nextMovementst.size();b++){
            nextMovements.add(nextMovementst.get(b));
        }

        return nextMovements;
    }
}
