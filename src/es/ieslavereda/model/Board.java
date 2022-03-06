package es.ieslavereda.model;

import es.ieslavereda.Screen.Screen;
import es.ieslavereda.model.dynamicStructure.MyList;
import es.ieslavereda.model.dynamicStructure.MyStore;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author MarcRamos
 */
public class Board implements Serializable {
    //private Cell[][] cells;
    private Map<Cordenada,Cell> cells;
    IDeletePieceManager store;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;

    /**
     * Constructor de tablero
     */
    public Board(){
        cells = new HashMap<>();
        store = new MyStore();
        whitePieces = new LinkedList<>();
        blackPieces = new LinkedList<>();
        Cordenada coordinate;
        Cell cell;
        for (int row = 1;row<=8;row++){
            for (int col = 0;col<8;col++){
                coordinate = new Cordenada((char)('A'+col),row);
                cell = new Cell(this,coordinate);
                cells.put(coordinate,cell);
            }
        }
        placePieces();
    }

    /**
     * Se encarga de crear las fichas y ponerlas en sus correspondientes celdas del tablero
     */
    private void placePieces(){
        for (int i= 0;i<8;i++){
            blackPieces.add(new BlackPawn(getCellAt(new Cordenada((char)('A' + i),2))));
            whitePieces.add(new WhitePawn(getCellAt(new Cordenada((char)('A' + i),7))));
        }
        blackPieces.add(new BlackBishop(getCellAt(new Cordenada('C',1))));
        blackPieces.add(new BlackBishop(getCellAt(new Cordenada('F',1))));
        whitePieces.add(new WhiteBishop(getCellAt(new Cordenada('C',8))));
        whitePieces.add(new WhiteBishop(getCellAt(new Cordenada('F',8))));
        //Tower
        blackPieces.add(new BlackTower(getCellAt(new Cordenada('A',1))));
        blackPieces.add(new BlackTower(getCellAt(new Cordenada('H',1))));
        whitePieces.add(new WhiteTower(getCellAt(new Cordenada('A',8))));
        whitePieces.add(new WhiteTower(getCellAt(new Cordenada('H',8))));
        //Knight
        blackPieces.add(new BlackKnight(getCellAt(new Cordenada('B',1))));
        blackPieces.add(new BlackKnight(getCellAt(new Cordenada('G',1))));
        whitePieces.add(new WhiteKnight(getCellAt(new Cordenada('B',8))));
        whitePieces.add(new WhiteKnight(getCellAt(new Cordenada('G',8))));
        //King
        blackPieces.add(new BlackKing(getCellAt(new Cordenada('D',1))));
        whitePieces.add(new WhiteKing(getCellAt(new Cordenada('D',8))));
        //Queen
        blackPieces.add(new BlackQueen(getCellAt(new Cordenada('E',1))));
        whitePieces.add(new WhiteQueen(getCellAt(new Cordenada('E',8))));


        /*new BlackKnight(cells[0][1]);
        new BlackKnight(cells[0][6]);
        new BlackKing(cells[0][4]);
        new WhiteKnight(cells[7][1]);
        new WhiteKnight(cells[7][6]);
        new WhiteQueen(cells[7][3]);
        new BlackPawn(cells[7][2]);
        new BlackBishop(cells[5][1]);
        new WhiteTower(cells[3][1]);
        new WhitePawn(cells[2][4]);
        new WhitePawn(cells[3][3]);*/
    }

    public Map<Cordenada, Cell> getCells(){
        return cells;
    }

    public IDeletePieceManager getStore(){
        return store;
    }

    public boolean containsCellAt(Cordenada cordenada){
        return cells.containsKey(cordenada);
    }

    /**
     * Se encarga de mandar a pintar cada celda de cada cordenada de una lista de cordenadas
     * @param cordenadas lista de cordenadas
     */
    public void hightLight(List<Cordenada> cordenadas){
        for (int i=0;i<cordenadas.size();i++){
            getCellAt(cordenadas.get(i)).hightLight();
        }
    }


    /**
     * Cuenta cuantas piezas hay de el tipo de pieza pasado
     * @param chessType tipo de pieza
     * @return numero de piezas
     */
    public long countPieceChessType(ChessType chessType){
        return this.cells.values().stream()
                .filter(c -> c.getPiece()!=null)
                .filter(c -> c.getPiece().getChessType() == chessType)
                .count();
    }


    public Cell getCellAt(Cordenada cordenada){
        return cells.get(cordenada);
    }

    /**
     * Nos dice si hay una pieza en una cordenada pasada
     * @param cordenada cordenada a buscar
     * @return boleano
     */
    public boolean containsPieceAt(Cordenada cordenada){
        Cell cell = getCellAt(cordenada);
        if (cell == null) return false;
        return (cell.getPiece()!=null);
    }

    /**
     * Resetea los colores de las celdas a los colores por defecto
     */
    public void resetColors(){
        for (Cell cell : cells.values()){
            cell.resetColor();
        }
    }

    /**
     * Comprueba si hay Jaque al rey de el jugador con el color de piezas pasado
     * @param p color de pieza
     * @return boleano confirmando si hay Jaque o no
     */
    public boolean comprubaJaque(PieceColor p){
        Cordenada cord = null;
        ChessType ct;
        boolean jaque = false;
        Set<Cordenada> cordenadas = cells.keySet();
        if (p==PieceColor.WHITE){
            ct = ChessType.WHITE_KING;
        } else {
            ct = ChessType.BLACK_KING;
        }
        List<Cordenada> correylist = cordenadas.stream().filter(cr -> this.containsPieceAt(cr)).filter(cr -> this.getCellAt(cr).getPiece().getChessType()==ct).collect(Collectors.toList());
        cord = correylist.get(0);
        List<Cordenada> posibles = null;
        for (Cordenada c : cells.keySet()){
            if (this.containsPieceAt(c)){
                if (this.getCellAt(c).getPiece().getColor()!=p){
                    List<Cordenada> lista = this.getCellAt(c).getPiece().getNextMovements();
                    Cordenada celdarey = cord;
                    posibles = lista.stream().filter(ps -> ps.equals(celdarey)).collect(Collectors.toList());
                    if (posibles.size()>0){
                        return true;
                    }
                }
            }
        }
        return jaque;
    }

    /**
     * Nos devuelve una lista con los movimientos de la pieza pasada que salvarian al rey de el Jaque
     * @param c pieza
     * @return lista de cordenadas
     */
    public List<Cordenada> pieceSaveKing(Cordenada c){
        Piece pieza = this.getCellAt(c).getPiece();
        PieceColor colorp = pieza.getColor();
        List<Cordenada> posiblesasegurados = new LinkedList<>();
        Board b = this;
        Piece pce = null;
        boolean haypieza = false;
        List<Cordenada> posmov = pieza.getNextMovements();
        for (Cordenada cr : posmov){
            if (b.getCellAt(cr).getPiece()!=null){
                pce = b.getCellAt(cr).getPiece();
                haypieza=true;
            }
            if (b.getCellAt(c).getPiece().moveTo(cr) && !b.comprubaJaque(colorp)){
                posiblesasegurados.add(cr);
            }
            b.getCellAt(cr).getPiece().moveToForAdmin(c);
            //this.cells.get(cr).setPiece(null);
            //this.cells.get(c).setPiece(pieza);
            if (haypieza==true){
                b.getCellAt(cr).setPiece(pce);
                pce.setCell(b.getCellAt(cr));
                store.remove(pce);
            }
            haypieza = false;
        }
        return posiblesasegurados;
    }

    /**
     * Nos dice si hay JaqueMate para el jugador con el color de piezas pasado
     * @param pcc color pieza
     * @return boleano confirmando si hay JaqueMate o no
     */
    public boolean hayHaqueeMate(PieceColor pcc){
        List<Cordenada> listfinal = new LinkedList<>();
        List<Cordenada> cordsss;
        List<Cordenada> cords = cells.keySet().stream().filter(c1 -> this.getCellAt(c1).getPiece()!=null).filter(c1 -> this.getCellAt(c1).getPiece().getColor() == pcc).collect(Collectors.toList());

        for (Cordenada crd : cords) {
            cordsss = pieceSaveKing(crd);
            for (Cordenada c : cordsss) {
                listfinal.add(c);
            }
        }

        if (listfinal.size()>0){
            return false;
        }
        return true;
    }

    public String toString(){
        String salida = "";
        salida += "   A  B  C  D  E  F  G  H \n";
        for (int row = 0;row<8;row++){
            salida+= (row+1)+" ";
            for (int col = 0;col<8;col++){
//                salida+=cells[row][col].toString();
            }
            salida+="\n";
        }
        return salida;
    }
}
