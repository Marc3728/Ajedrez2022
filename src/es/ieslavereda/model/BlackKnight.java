package es.ieslavereda.model;

import java.io.Serializable;

public class BlackKnight extends Knight implements Serializable {
    public BlackKnight(Cell cell){
        super(cell, ChessType.BLACK_KNIGHT);
        place();
    }
}
