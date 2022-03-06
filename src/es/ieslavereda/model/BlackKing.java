package es.ieslavereda.model;

import java.io.Serializable;

public class BlackKing extends King implements Serializable {
    public BlackKing(Cell cell){
        super(cell,ChessType.BLACK_KING);
        place();
    }
}
