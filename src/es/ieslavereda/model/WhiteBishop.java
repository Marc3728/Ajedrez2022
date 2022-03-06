package es.ieslavereda.model;

import java.io.Serializable;

public class WhiteBishop extends Bishop implements Serializable {
    public WhiteBishop(Cell cell) {
        super(cell, ChessType.WHITE_BISHOP);
        place();
    }
}
