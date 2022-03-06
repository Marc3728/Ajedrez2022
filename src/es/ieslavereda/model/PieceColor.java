package es.ieslavereda.model;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;

public enum PieceColor implements Serializable {
    WHITE(Attribute.TEXT_COLOR(255,255,255)),
    BLACK(Attribute.TEXT_COLOR(0,0,0));

    private Attribute color;

    PieceColor (Attribute color){
        this.color = color;
    }

    public Attribute getAttribute(){
        return color;
    }

    public PieceColor next(){
        if (ordinal()==0){
            return BLACK;
        } else {
            return WHITE;
        }
    }
}
