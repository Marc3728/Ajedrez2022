package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author MarcRamos
 */
public class Player implements Serializable {
    private String nombre;
    private PieceColor turno;

    /**
     * Contructor de jugador
     * @param nombre nombre jugador
     * @param turno color piezas jugador
     */
    public Player(String nombre,PieceColor turno){
        this.nombre = nombre;
        this.turno = turno;
    }

    public String getNombre(){
        return nombre;
    }

    public PieceColor getTurno(){
        return turno;
    }

    public String toString(){
        return nombre;
    }
}
