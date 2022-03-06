package es.ieslavereda.model;

import java.io.Serializable;

public class WhiteTower extends Tower implements Serializable {
    public WhiteTower(Cell cell){
        super(cell,ChessType.WHITE_TOWER);
        place();
    }
}
