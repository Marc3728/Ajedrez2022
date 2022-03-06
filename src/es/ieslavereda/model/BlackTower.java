package es.ieslavereda.model;

import java.io.Serializable;

public class BlackTower extends Tower implements Serializable {
    public BlackTower(Cell cell){
        super(cell,ChessType.BLACK_TOWER);
        place();
    }
}
