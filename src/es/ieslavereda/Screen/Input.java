package es.ieslavereda.Screen;

import es.ieslavereda.model.Cordenada;
import java.util.Scanner;

/**
 * @author MarcRamos
 */
public class Input {
    /**
     * Se encarga de pedir una cordenada valida a el usuario
     * @param mensaje mensaje a mostrar
     * @return cordenada valida introducida por el usuario
     */
    public static Cordenada pedirCordenada(String mensaje){
        Scanner sc = new Scanner(System.in);
        boolean correcta = false;
        char letra;
        int num;
        do {
            System.out.println(mensaje);
            String cordenada = sc.next();
            if ((int) cordenada.charAt(0)>64 && (int) cordenada.charAt(0)<73 || (int) cordenada.charAt(0)>96 && (int) cordenada.charAt(0)<105){
                if (Character.getNumericValue(cordenada.charAt(1))>0 && Character.getNumericValue(cordenada.charAt(1))<9){
                    if ((int) cordenada.charAt(0)>96 && (int) cordenada.charAt(0)<105){
                        int valnum = ((int) cordenada.charAt(0)) -32;
                        letra = (char) valnum;
                    } else {
                        letra = cordenada.charAt(0);
                    }
                    num = Character.getNumericValue(cordenada.charAt(1));
                    return new Cordenada(letra,num);
                }
            }

        } while (correcta==false);
        return null;
    }
}
