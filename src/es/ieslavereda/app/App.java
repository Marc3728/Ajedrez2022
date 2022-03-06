package es.ieslavereda.app;

import es.ieslavereda.controller.Gamee;
import es.ieslavereda.model.PieceColor;
import es.ieslavereda.model.Player;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean volverjugar;
        int respvj;
        do {
            volverjugar = false;
            System.out.println("1-Partida nueva 2-Cargar Partida");
            int respues = sc.nextInt();
            Gamee juego;
            if (respues == 2) {
                juego = new Gamee(new Player("jugadoruno", PieceColor.WHITE), new Player("jugadordos", PieceColor.BLACK));
                juego.loadGame();
            } else {
                System.out.println("Dime el nombre de el jugador de fichas blancas");
                String juno = sc.next();
                System.out.println("Dime el nombre de el jugador de fichas negras");
                String jdos = sc.next();
                juego = new Gamee(new Player(juno, PieceColor.WHITE), new Player(jdos, PieceColor.BLACK));
                juego.start();
            }
            System.out.println("Quieres volver a jugar 1-SI 2-NO");
            respvj = sc.nextInt();
            if (respvj==1){
                volverjugar = true;
            }
        } while(volverjugar==true);
    }
}
