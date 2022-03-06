package es.ieslavereda.Screen;

import com.diogonunes.jcolor.Attribute;
import es.ieslavereda.model.*;

import java.util.LinkedList;
import java.util.List;

import static com.diogonunes.jcolor.Ansi.colorize;

/**
 * @author MarcRamos
 */
public class Screen {
    /**
     * Se encarga de comprobar de que color es el color de piezas y depende de el color manda a printear el tablero de un lado o de otro
     * @param board tablero a printear
     * @param color color de pieza
     */
    public static void show(Board board, PieceColor color) {
        if(color==PieceColor.WHITE)
            showWhiteView(board);
        else
            showBlackView(board);


        piecesLive(board);
        piecesDead(board);
        /*String piezas = "";
        String cuenta = "";
        for (ChessType chessType : ChessType.values()){
            piezas += chessType.getShape();
            cuenta += board.countPieceChessType(chessType);
        }*/
        /*System.out.println("          PIECES ALIVE");
        System.out.println("          "+piezas);
        System.out.println("          "+cuenta);*/
    }

    /**
     * Se encarga de printear el tablero de el punto de vista para las piezas blancas
     * @param board tablero a printear
     */
    private static void showWhiteView(Board board) {

        StringBuilder salida = new StringBuilder();

        salida.append(getLettersWhiteView());

        for (int fila = 1; fila <= 8; fila++) {
            salida.append(getMiddlePartWhiteView(board, fila));

        }
        salida.append(getLettersWhiteView());

        System.out.print(salida);
    }

    /**
     * Se encarga de printear el tablero de el punto de vista para las piezas negras
     * @param board tablero a printear
     */
    private static void showBlackView(Board board) {

        StringBuilder salida = new StringBuilder();

        salida.append(getLettersBlackView());

        for (int fila = 8; fila >= 1; fila--) {
            salida.append(getMiddlePartBlackView(board, fila));

        }
        salida.append(getLettersBlackView());

        System.out.print(salida);
    }

    /**
     * Se encarga de devolver las letras para el punto de vista de las piezas blancas
     * @return cadena con las letras
     */
    private static String getLettersWhiteView() {
        return "               A  B  C  D  E  F  G  H\n";
    }

    /**
     * Se encarga de devolver las letras para el punto de vista de las piezas negras
     * @return cadena con las letras
     */
    private static String getLettersBlackView() {
        return "               H  G  F  E  D  C  B  A\n";
    }

    /**
     * Devuelve la parte de el tablero central para el punto de vista para las piezas blancas
     * @param board tablero a printear
     * @param fila fila del tablero
     * @return cadena lista para printear de la fila pasada de el tablero pasado
     */
    private static String getMiddlePartWhiteView(Board board, int fila) {

        StringBuilder salida = new StringBuilder("            " + fila + " ");

        for (int col = 0; col < 8; col++) {
            salida.append(board.getCellAt(new Cordenada((char) ('A' + col), fila)));
        }

        return salida.append(" ").append(fila).append("\n").toString();
    }

    /**
     * Devuelve la parte de el tablero central para el punto de vista para las piezas negras
     * @param board tablero a printear
     * @param fila fila del tablero
     * @return cadena lista para printear de la fila pasada de el tablero pasado
     */
    private static String getMiddlePartBlackView(Board board, int fila) {

        StringBuilder salida = new StringBuilder("            " + fila + " ");

        for (int col = 7; col >= 0; col--) {
            salida.append(board.getCellAt(new Cordenada((char) ('A' + col), fila)));
        }

        return salida.append(" ").append(fila).append("\n").toString();
    }

    /**
     * Printea las diferentes fichas del tablero y el numero de unidades que hay vivas de cada tipo
     * @param board tablero donde buscar
     */
    private static void piecesLive(Board board){
        System.out.println("                     PIECES LIVE");
        String output="        ";

        for (ChessType chessType : ChessType.values()){
            output +=colorize(" "+chessType.getShape()+" ",Cell.CellColor.BLACK_CELL.getAttribute(),chessType.getColor().getAttribute());
        }
        output+="\n        ";
        for (ChessType chessType : ChessType.values()){
            output +=colorize(" "+board.countPieceChessType(chessType)+" ",Cell.CellColor.WHITE_CELL.getAttribute(), Attribute.TEXT_COLOR(0,0,0));
        }
        System.out.println(output);
    }

    /**
     * Printea las diferentes fichas del tablero y el numero de unidades que hay muertas de cada tipo
     * @param board tablero donde buscar
     */
    private static void piecesDead(Board board){
        System.out.println("                     PIECES DEAD");
        String output="        ";

        for (ChessType chessType : ChessType.values()){
            output +=colorize(" "+chessType.getShape()+" ",Cell.CellColor.BLACK_CELL.getAttribute(),chessType.getColor().getAttribute());
        }
        output+="\n        ";
        for (ChessType chessType : ChessType.values()){
            output +=colorize(" "+board.getStore().count(chessType)+" ",Cell.CellColor.WHITE_CELL.getAttribute(), Attribute.TEXT_COLOR(0,0,0));
        }
        System.out.println(output);
    }

    /**
     * Printea el turno de el jugador en la partida
     * @param jugador primer jugador
     * @param jugadordos segundo jugador
     * @param turno el color de pieza de el turno
     */
    public static void tellTurn(Player jugador,Player jugadordos,PieceColor turno){
        if (jugador.getTurno()==turno){
            System.out.println("Turno de "+jugador);
        } else {
            System.out.println("Turno de "+jugadordos);
        }
    }

    /**
     * Se encarga de decir que hay jaque si hay jaque para el jugador con el color de pieza pasado en el tablero pasado
     * @param p color pieza
     * @param b tablero
     */
    public static void tellJaque(PieceColor p,Board b){
        if (b.comprubaJaque(p)==true){
            System.out.println("JAQUE");
        }
    }
}
