/**
 * Ejercicio de Arbol de Navidad
 * @author Alvaro Espinosa Montesinos
 */

import static settings.Colours.*;
import java.util.Scanner;

public class ArbolNavidad {
    /**
     * Array de los colores para la decoracion
     */
    public static String[] colores = {RED, BLUE, ORANGE};
    /**
     * Array del triangulo principal
     */
    private static String[] triangulo = {"*","***","*****","*******"};
    /**
     * Scanner para recibir datos del usuario
     */
    private static Scanner scn = new Scanner(System.in);
    private static int ramas;
    private static boolean estrella = false;
    private static boolean tronco = false;
    private static boolean decoracion = false;
    private static boolean regalos = false;
    private static String nombre;
    public static void main(String[] args) {
        menu();
        if (decoracion){
            decoration(ramas, estrella);
        } else
            ramas(ramas, estrella);
        if (tronco && regalos)
            troncoConRegalos();
        else
            tronco();
        suelo(nombre);
    }

    /**
     * Imprime el menú del programa
     */
    static void menu(){
        char answer;
        System.out.print("Cuantas ramas deseas ingresar: ");
        ramas = scn.nextInt();
        scn.nextLine();
        do {
            System.out.print("Quieres una estrella (S/N): ");
            answer = scn.nextLine().charAt(0);
        } while (!(answer == 'S' || answer == 's' || answer == 'N' || answer == 'n'));
        estrella = answer == 83 || answer == 115;
        do {
            System.out.print("Quieres un tronco (S/N): ");
            answer = scn.nextLine().charAt(0);
        } while (!(answer == 'S' || answer == 's' || answer == 'N' || answer == 'n'));
        tronco = answer == 83 || answer == 115;
        if (tronco){
            do {
                System.out.print("Quieres regalos (S/N): ");
                answer = scn.nextLine().charAt(0);
            } while (!(answer == 'S' || answer == 's' || answer == 'N' || answer == 'n'));
            regalos = answer == 83 || answer == 115;
        }
        do {
            System.out.print("Quieres decoración (S/N): ");
            answer = scn.nextLine().charAt(0);
        } while (!(answer == 'S' || answer == 's' || answer == 'N' || answer == 'n'));
        decoracion = answer == 83 || answer == 115;
        System.out.print("Nombre que quieres que aparezca: ");
        nombre = scn.nextLine();
    }

    /**
     * Imprime las ramas (triangulos)
     * @param ramas Numero de ramas
     * @param star Si tiene estrella o no
     */
    static void ramas(int ramas, boolean star){
        for (int i = 0; i < ramas; i++) {
            if (i == 0){
                for (int j = 0; j < triangulo.length; j++) {
                    int espacios = (triangulo.length - 1 - j) + (ramas - 1 - i);
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    if (star && j == 0){
                        System.out.println(YELLOW+triangulo[0]);
                    } else {
                        System.out.println(GREEN+triangulo[j]);
                    }
                }
            } else if (i % 2 == 0) {
                for (int j = 0; j < triangulo.length; j++) {
                    int espacios = (triangulo.length - 1 - j) + (ramas - 1 - i);
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    triangulo[j] = triangulo[j]+"**";
                    System.out.println(GREEN+triangulo[j]);
                }
            } else {
                for (int j = 0; j < triangulo.length; j++) {
                    int espacios = (triangulo.length - 1 - j) + (ramas - 1 - i);
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    triangulo[j] = triangulo[j]+"**";
                    System.out.println(DARK_GREEN +triangulo[j]);
                }
            }
        }
    }

    /**
     * Imprime la decoracion
     * @param ramas Numeros de ramas
     * @param star Si tiene estrella o no
     */
    static void decoration(int ramas, boolean star) {
        for (int i = 0; i < ramas; i++) {
            String colorBase;
            if (i % 2 == 0)
                colorBase = GREEN;
            else
                colorBase = DARK_GREEN;

            if (i > 0) {
                for (int k = 0; k < triangulo.length; k++) {
                    triangulo[k] = triangulo[k] + "**";
                }
            }

            for (int j = 0; j < triangulo.length; j++) {

                int espacios = (triangulo.length - 1 - j) + (ramas - 1 - i);

                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }

                if (star && i == 0 && j == 0) {
                    System.out.println(YELLOW + triangulo[0]);
                } else {
                    String lineaActual = triangulo[j];

                    for (int k = 0; k < lineaActual.length(); k++) {
                        char caracter = lineaActual.charAt(k);

                        if (Math.random() < 0.15) {
                            int colorRandom = (int) (Math.random() * colores.length);
                            System.out.print(colores[colorRandom] + caracter);
                        } else {
                            System.out.print(colorBase + caracter);
                        }
                    }
                    System.out.println(RESET);
                }
            }
        }
    }

    /**
     * Metodo para dibujar el tronco
     * depende del tamaño de la rama dibuja un tronco u otro
     */
    static void tronco(){
        if (ramas <= 1){
            for (int i = 0; i < 2; i++) {
                int espacios = (triangulo.length - 2) + (ramas - 1);
                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 3; j++) {
                    System.out.print(BROWN+"*");
                }
                System.out.println(RESET);
            }
        } else if (ramas < 10) {
            for (int i = 0; i < 3; i++) {
                int espacios = (triangulo.length - 2) + (ramas - 1);
                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 3; j++) {
                    System.out.print(BROWN+"*");
                }
                System.out.println(RESET);
            }
        } else if (ramas < 20) {
            for (int i = 0; i < 4; i++) {
                int espacios = (triangulo.length - 3) + (ramas - 1);
                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 4; j++) {
                    System.out.print(BROWN+"*");
                }
                System.out.println(RESET);
            }
        } else {
            for (int i = 0; i < (ramas/5); i++) {
                int espacios = (triangulo.length - (ramas/8)) + (ramas - 1);
                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < (ramas/5); j++) {
                    System.out.print(BROWN+"*");
                }
                System.out.println(RESET);
            }
        }
    }

    /**
     * Utiliza el metodo tronco y añade los regalos
     */
    static void troncoConRegalos(){
        int largoRegalo = 3;
        int anchoRegalo = 2;
        if (ramas <= 1){
            for (int i = 0; i < 2; i++) {
                if (i == 0){
                    int espacios = (triangulo.length - 2) + (ramas - 1);
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+"*");
                    }
                    System.out.println(RESET);
                } else {
                    int espacios = (triangulo.length - 2) + (ramas - 1) - largoRegalo;
                    System.out.print(RED+"*"+RESET+"*"+RED+"*");
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+"*");
                    }
                    System.out.println(RESET);
                }
            }
        } else if (ramas < 10) {
            for (int i = 0; i < 3; i++) {
                if (i == 0){
                    int espacios = (triangulo.length - 2) + (ramas - 1);
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+"*");
                    }
                    System.out.println(RESET);
                } else {
                    int espacios = (triangulo.length - 2) + (ramas - 1) - largoRegalo - 1;

                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    System.out.print(RED+"▓"+RESET+"▓"+RED+"▓ ");
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+"*");
                    }
                    System.out.print(" "+BLUE+"▓"+RESET+"▓"+BLUE+"▓");
                    System.out.println(RESET);
                }
            }
        } else if (ramas < 20) {
            for (int i = 0; i < 4; i++) {
                int espacios = (triangulo.length - 3) + (ramas - 1);
                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 4; j++) {
                    System.out.print(BROWN+"*");
                }
                System.out.println(RESET);
            }
        } else {
            for (int i = 0; i < (ramas/5); i++) {
                int espacios = (triangulo.length - (ramas/8)) + (ramas - 1);
                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < (ramas/5); j++) {
                    System.out.print(BROWN+"*");
                }
                System.out.println(RESET);
            }
        }
    }

    static void suelo(String name){
        int suelo = 7;
        for (int i = 0; i < ramas; i++) {
            suelo += 2;
        }
        for (int i = 0; i < suelo-2; i++) {
            System.out.print(DARK_ORANGE+"▀");
        }
        System.out.println(RESET);
        int espacios = (suelo/2) - (name.length()/2);
        for (int i = 0; i < espacios; i++) {
            System.out.print(" ");
        }
        System.out.println(name);
    }
}