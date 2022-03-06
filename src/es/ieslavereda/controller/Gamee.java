package es.ieslavereda.controller;

import es.ieslavereda.Screen.Input;
import es.ieslavereda.Screen.Screen;
import es.ieslavereda.model.*;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author MarcRamos
 */
public class Gamee implements Serializable {
    private Player playerone;
    private Player playertwo;
    private Board board;
    private PieceColor turn;

    /**
     * Contructor de el juego
     * @param playerone jugador uno
     * @param playertwo jugador dos
     */
    public Gamee(Player playerone,Player playertwo){
        this.playerone = playerone;
        this.playertwo = playertwo;
        this.board = new Board();
        this.turn = PieceColor.WHITE;
    }

    /**
     * Se encarga de inicializar el juego y es el que lleva el juego a cabo
     */
    public void start(){
        boolean finalizar = false;
        int num = 0;
        Scanner sc = new Scanner(System.in);
        String resp;
        boolean guardar = false;
        boolean haqmat = false;
        List<Cordenada> posmov;
        do {
            System.out.println("Pulsa cualquier letra para continuar o pulsa x para guardar");
            resp = sc.next();
            if (resp.charAt(0)!='x' && resp.charAt(0)!='X') {
                Screen.show(board, turn);
                Screen.tellTurn(playerone, playertwo, turn);
                Screen.tellJaque(turn, board);
                Piece pieza;
                Cordenada c;
                if (board.comprubaJaque(turn) == false) {
                    do {
                        do {
                            c = Input.pedirCordenada("Dime la cordenada de la ficha a mover");
                            System.out.println(c);
                        } while (!board.containsCellAt(c) || !board.containsPieceAt(c) || board.getCellAt(c).getPiece().getColor() != turn);
                        pieza = board.getCellAt(c).getPiece();
                        posmov = pieza.getNextMovements();
                    } while (posmov.isEmpty());
                    board.hightLight(posmov);
                    Screen.show(board, turn);
                    boolean comprobacion = false;
                    Cordenada m;
                    do {
                        m = Input.pedirCordenada("Dime la cordenada a la que quieres mover");
                        comprobacion = board.getCellAt(c).getPiece().moveTo(m);
                    } while (comprobacion == false);
                } else {
                    if (board.hayHaqueeMate(turn)) {
                        haqmat = true;
                        System.out.println("HAQUE MATE");
                    } else {
                        do {
                            c = Input.pedirCordenada("Dime la cordenada de la ficha a mover");
                            System.out.println(c);
                        } while (!board.containsCellAt(c) || !board.containsPieceAt(c) || board.getCellAt(c).getPiece().getColor() != turn || board.pieceSaveKing(c).size() == 0);
                        List<Cordenada> cordnn = board.pieceSaveKing(c);
                        board.hightLight(cordnn);
                        Screen.show(board, turn);
                        boolean comprobacion = false;
                        Cordenada m;
                        do {
                            m = Input.pedirCordenada("Dime la cordenada a mover");
                            if (cordnn.contains(m)) {
                                comprobacion = board.getCellAt(c).getPiece().moveTo(m);
                            }
                        } while (comprobacion == false);
                    }
                }
                if (haqmat != true) {
                    board.resetColors();
                    //Screen.show(board,turn);
                    if (turn == PieceColor.WHITE) {
                        turn = PieceColor.BLACK;
                    } else {
                        turn = PieceColor.WHITE;
                    }
                }
            } else {
                guardar = true;
                this.saveGame();
            }

        } while (guardar==false && haqmat==false);
        if (guardar==false) {
            if (board.hayHaqueeMate(PieceColor.WHITE)) {
                if (playerone.getTurno() == PieceColor.BLACK) {
                    System.out.println("Gana " + playerone);
                } else {
                    System.out.println("Gana" + playertwo);
                }
            } else {
                if (playerone.getTurno() == PieceColor.WHITE) {
                    System.out.println("Gana " + playerone);
                } else {
                    System.out.println("Gana" + playertwo);
                }
            }
        }
    }

    /**
     * Guarda la partida
     */
    public void saveGame(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("partida"))) {
            oos.writeObject(this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Carga la partida guardada
     */
    public void loadGame(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("partida"))) {
            Gamee g = (Gamee) ois.readObject();
            this.board = g.board;
            this.playerone = g.playerone;
            this.playertwo = g.playertwo;
            this.turn = g.turn;
            start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
