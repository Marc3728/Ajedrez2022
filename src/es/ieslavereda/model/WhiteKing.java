package es.ieslavereda.model;

import java.io.Serializable;

public class WhiteKing extends King implements Serializable {
    public WhiteKing(Cell cell){
        super(cell,ChessType.WHITE_KING);
        place();
    }
}
