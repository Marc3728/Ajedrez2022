package es.ieslavereda.model;

import java.io.Serializable;

public class WhiteQueen extends Queen implements Serializable {
    public WhiteQueen(Cell cell){
        super(cell,ChessType.WHITE_QUEEN);
        place();
    }
}
