package es.ieslavereda.model;

import java.io.Serializable;

public class BlackBishop extends Bishop implements Serializable {
    public BlackBishop(Cell cell){
        super(cell,ChessType.BLACK_BISHOP);
        place();
    }
}
