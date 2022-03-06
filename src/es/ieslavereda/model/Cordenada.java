package es.ieslavereda.model;

import java.io.Serializable;

/**
 * @author MarcRamos
 */
public class Cordenada implements Serializable {
    private char letter;
    private int number;

    /**
     * Contructor de la cordenada
     * @param letter letra de la cordenada
     * @param number numero de la cordenada
     */
    public Cordenada (char letter,int number){
        this.letter = String.valueOf(letter).toUpperCase().charAt(0);
        this.number = number;
    }

    public Cordenada up(){
        return new Cordenada(letter,number-1);
    }

    public Cordenada down(){
        return new Cordenada(letter,number+1);
    }

    public Cordenada right(){
        return new Cordenada((char)(letter+1),number);
    }

    public Cordenada left(){
        return new Cordenada((char)(letter-1),number);
    }

    public Cordenada upRight(){
        return up().right();
    }

    public Cordenada upLeft(){
        return up().left();
    }

    public Cordenada downLeft(){
        return down().left();
    }

    public Cordenada downRight(){
        return down().right();
    }

    public char getLetter(){
        return letter;
    }

    public int getNumber(){
        return number;
    }

    @Override
    public boolean equals(Object o){
        if (!(o instanceof Cordenada))	return false;
        return ((Cordenada) o).getLetter() == this.letter && ((Cordenada) o).getNumber() == this.number;

    }

    public int compareTo(Cordenada c) {
        if (c.getLetter()!=this.letter) return c.getLetter()-this.letter;
        return (c.getNumber()!=this.number)? c.getNumber() - this.number : 0;
    }

    public int hashCode(){
        return number+letter;
    }

    public String toString(){
        return "("+letter+","+number+")";
    }
}
