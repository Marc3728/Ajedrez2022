package es.ieslavereda.model;

import java.io.Serializable;

public class BlackQueen extends Queen implements Serializable {
    public BlackQueen(Cell cell){
        super(cell,ChessType.BLACK_QUEEN);
        place();
    }
}
