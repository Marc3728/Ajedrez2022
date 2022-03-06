package es.ieslavereda.model.dynamicStructure;

import es.ieslavereda.model.Piece;

import java.io.Serializable;

public class Node implements Serializable {
    private Piece info;
    private Node next;

    public Node(Piece piece){
        info = piece;
        next = null;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node node){
        this.next = node;
    }

    public Piece getInfo(){
        return info;
    }

    public String toString(){
        return info.toString();
    }
}
