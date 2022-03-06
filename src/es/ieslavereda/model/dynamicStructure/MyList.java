package es.ieslavereda.model.dynamicStructure;

import es.ieslavereda.model.ChessType;
import es.ieslavereda.model.IDeletePieceManager;
import es.ieslavereda.model.Piece;

import java.io.Serializable;

/**
 * @author MarcRamos
 */
public class MyList implements IDeletePieceManager, Serializable {
    private Node head;
    private int size;

    /**
     * Se encara de meter una pieza a la lista
     * @param piece pieza a meter
     */
    @Override
    public void add(Piece piece) {
        Node aux = new Node(piece);
        if(head==null)
            head = aux;
        else{
            aux.setNext(head);
            head = aux;
        }
        size++;

    }

    /**
     * Se encarga de devolver la primera pieza de la lista
     * @return la primera pieza de la lista
     */
    @Override
    public Piece getFirst() {
        if(head == null)
            return null;
        else{
            Piece p = head.getInfo();
            head = head.getNext();
            return p;
        }
    }

    /**
     * Se encarga de quitar de la lista la pieza pasada
     * @param piece pieza a eliminar
     */
    @Override
    public void remove(Piece piece) {
        if(head==null)
            return;
        if(head.getInfo().equals(piece)) {
            head = head.getNext();
            size--;
        }
        else{
            Node aux2=head,aux1=head.getNext();
            while (aux1!=null && aux1.getInfo()!=piece){
                aux2=aux1;
                aux1=aux1.getNext();
            }

            // To do
        }

    }

    /**
     * Se encarga de contar las piezas que hay de un tipo de pieza
     * @param chessType tipo de pieza
     * @return numero de piezas
     */
    @Override
    public int count(ChessType chessType) {
        int count = 0;
        Node aux = head;

        while (aux!=null){
            if(aux.getInfo().getChessType().equals(chessType))
                count++;

            aux = aux.getNext();
        }


        return count;
    }
}
