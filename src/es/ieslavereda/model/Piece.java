package es.ieslavereda.model;

import com.diogonunes.jcolor.Attribute;

import java.io.Serializable;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * @author MarcRamos
 */
public abstract class Piece implements Serializable {
    private ChessType chessType;
    protected Cell cell;

    /**
     * Contructor de pieza
     * @param cell celda donde esta la pieza
     * @param chessType tipo de pieza
     */
    public Piece (Cell cell, ChessType chessType){
        this.cell = cell;
        this.chessType = chessType;
    }

    public void place(){
        cell.setPiece(this);
    }

    public void setCell(Cell c){
        this.cell = c;
    }

    public Cell getCell(){
        return cell;
    }

    public PieceColor getColor(){
        return chessType.getColor();
    }

    public ChessType getChessType(){
        return chessType;
    }

    /**
     * Te comprueba si te puede mover la pieza a una cordenada y si es asi te lo mueve
     * @param c cordenada a la que mover
     * @return boleano confirmando si se ha movido o no
     */
    public boolean moveTo(Cordenada c){
        if (cell==null || !cell.getBoard().containsCellAt(c)){
            return false;
        }

        if (getNextMovements().contains(c)){
            if (cell.getBoard().containsPieceAt(c)){
                Piece piece = cell.getBoard().getCellAt(c).getPiece();
                piece.cell = null;
                cell.getBoard().getStore().add(piece);
            }
            cell.setPiece(null);
            cell = cell.getBoard().getCellAt(c);
            place();
            pawnChange(c);
            return true;
        }
        return false;
    }

    /**
     * Metodo utilizado en el jaque para realizar el movimiento de vuelta a la hora de comprobar si sigue habiendo jaque al mover una ficha
     * @param c cordenada a la que mover
     * @return boleano confirmando si se ha realizado el movimiento correctamente o no
     */
    public boolean moveToForAdmin(Cordenada c){
        if (cell==null || !cell.getBoard().containsCellAt(c)){
            return false;
        }

        //if (getNextMovements().contains(c)){
            if (cell.getBoard().containsPieceAt(c)){
                Piece piece = cell.getBoard().getCellAt(c).getPiece();
                piece.cell = null;
                cell.getBoard().getStore().add(piece);
            }
            cell.setPiece(null);
            cell = cell.getBoard().getCellAt(c);
            place();
            pawnChange(c);
            return true;
        //}
    }

    /**
     * Cuando el peon llega al final de el tablero lo tranforma en reina
     * @param cord cordenada donde esta el peon
     * @return boleano confirmando si se ha relizado correctamente el cambio o no
     */
    public boolean pawnChange(Cordenada cord){
        if (this.getChessType()==ChessType.BLACK_PAWN){
            if (cord.getNumber()==8){
                cell.setPiece(new BlackQueen(cell));
                return true;
            }
        } else if (this.getChessType()==ChessType.WHITE_PAWN){
            if (cord.getNumber()==1){
                cell.setPiece(new WhiteQueen(cell));
                return true;
            }
        }
        return false;
    }

    public String toString(){
        Attribute background = cell.getColor().getAttribute();
        Attribute textColor = chessType.getColor().getAttribute();
        Attribute[] myFormat = new Attribute[]{background,textColor};

        return colorize(" " + chessType.getShape() + " ",myFormat);
    }

    public abstract List<Cordenada> getNextMovements();

    /*public void seeMovements(Cordenada[] cords){
        Board board = cell.getBoard();
        for (int i = 0;i<cords.length;i++){
            if (board.containsPieceAt(cords[i])){
                cell.kill();
            } else {
                cell.hightLight();
            }
        }
    }*/
}
