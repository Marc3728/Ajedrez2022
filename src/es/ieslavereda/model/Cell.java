package es.ieslavereda.model;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * @author MarcRamos
 */
public class Cell implements Serializable {
    private Cordenada cordenada;
    private CellColor color;
    private CellColor originalColor;
    private Piece piece;
    private Board board;

    /**
     * Contructor de celda
     * @param board tablero donde estara la celda
     * @param cordenada cordenada donde estara la celda
     */
    public Cell(Board board,Cordenada cordenada){
        this.board = board;
        this.cordenada = cordenada;
        if (((cordenada.getLetter()-'A') + cordenada.getNumber())%2==0){
            originalColor=CellColor.BLACK_CELL;
        } else {
            originalColor = CellColor.WHITE_CELL;
        }

        color = originalColor;
    }

    public String toString(){
        Attribute[] myFormat = new Attribute[]{color.getAttribute()};
        if (piece!=null){
            return piece.toString();
        } else {
            return colorize("   ",myFormat);
        }
    }

    public CellColor getColor(){
        return color;
    }

    public Board getBoard(){
        return board;
    }

    public Cordenada getCordenada(){
        return cordenada;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
    }

    /**
     * Se encarga de pintar la celda de los moviemientos posibles de una pieza
     */
    public void hightLight(){
        if (board.containsPieceAt(this.getCordenada())) {
            if (originalColor == CellColor.WHITE_CELL) {
                color = CellColor.HIGHTLIGHT_KILL_BLACK;
            } else {
                color = CellColor.HIGHTLIGHT_KILL_WHITE;
            }
        } else {

            color = CellColor.YELLOW;

        }
    }

    public void resetColor(){
        color = originalColor;
    }
    public Piece getPiece(){
        return piece;
    }

    /**
     * Enumerador con los diferentes colores que puede tomar la celda
     */
    public enum CellColor{
        WHITE_CELL(Attribute.BACK_COLOR(180,180,180)),
        BLACK_CELL(Attribute.BACK_COLOR(100,100,100)),
        YELLOW(Attribute.BACK_COLOR(255,255,0)),
        HIGHTLIGHT_KILL_WHITE(Attribute.BACK_COLOR(180,0,0)),
        HIGHTLIGHT_KILL_BLACK(Attribute.BACK_COLOR(130,0,0));

        private Attribute color;
        CellColor(Attribute color){
            this.color = color;
        }

        public Attribute getAttribute(){
            return color;
        }
    }
}
