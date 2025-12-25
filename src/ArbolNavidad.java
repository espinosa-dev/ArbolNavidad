/**
 * Ejercicio de Arbol de Navidad
 * @author Alvaro Espinosa Montesinos
 */

import static SETTINGS.Colours.*;
import java.util.Scanner;

public class ArbolNavidad {
    /**
     * Array de los colores para la decoracion
     */
    public static String[] colores = {RED, BLUE, ORANGE};
    /**
     * Array del triángulo principal
     */
    private static String[] triangulo = {"♠","♠♠♠","♠♠♠♠♠","♠♠♠♠♠♠♠"};
    private static String[] chimenea = {"▓▀▀▀▀▓","▓","▓"};
    private static final char CARACTER_TRONCO = '█';
    private static final char CARACTER_HOJA = '♠';
    private static final char CARACTER_ESTRELLA = '★';
    private static final char CARACTER_REGALO = '▓';
    private static final String FOC = "WWWW";
    private static final char CARACTER_LAZO= 'ღ';
    private static final char CARACTER_SUELO = '▀';
    /**
     * Scanner para recibir datos del usuario
     */
    private static Scanner scn = new Scanner(System.in);
    private static int ramas;
    private static boolean estrella = false;
    private static boolean tronco = false;
    private static boolean decoracion = false;
    private static boolean regalos = false;
    private static boolean fuego = false;
    private static String nombre;
    public static void main(String[] args) {
        menu();
        if (ramas > 0) {
            if (decoracion) {
                decoration(ramas, estrella);
            } else
                ramas(ramas, estrella);
        }
        if (tronco && regalos)
            troncoConRegalos();
        else if (tronco)
            tronco();
        suelo(nombre);
    }

    /**
     * Imprime el menú del programa
     */
    static void menu(){
        char answer;
        do {
            System.out.print("Cuantas ramas deseas ingresar (Máximo 10): ");
            ramas = scn.nextInt();
        } while (ramas > 10 || ramas < 0);
        scn.nextLine();
        do {
            System.out.print("Quieres una estrella (S/N): ");
            answer = scn.nextLine().charAt(0);
            answer = Character.toLowerCase(answer);
        } while (!(answer == 's' || answer == 'n'));
        estrella = answer == 's';
        do {
            System.out.print("Quieres un tronco (S/N): ");
            answer = scn.nextLine().charAt(0);
            answer = Character.toLowerCase(answer);
        } while (!(answer == 's' || answer == 'n'));
        tronco = answer == 's';
        if (tronco){
            do {
                System.out.print("Quieres regalos (S/N): ");
                answer = scn.nextLine().charAt(0);
                answer = Character.toLowerCase(answer);
            } while (!(answer == 's' || answer == 'n'));
            regalos = answer == 's';
        }
        do {
            System.out.print("Quieres decoración (S/N): ");
            answer = scn.nextLine().charAt(0);
            answer = Character.toLowerCase(answer);
        } while (!(answer == 's' || answer == 'n'));
        decoracion = answer == 's';
        do {
            System.out.print("Quieres chimenea (S/N): ");
            answer = scn.nextLine().charAt(0);
            answer = Character.toLowerCase(answer);
        } while (!(answer == 's' || answer == 'n'));
        fuego = answer == 's';
        System.out.print("Nombre que quieres que aparezca: ");
        nombre = scn.nextLine();
    }

    /**
     * Imprime las ramas (triangulos)
     * @param ramas Numero de ramas
     * @param star Si tiene estrella o no
     */
    static void ramas(int ramas, boolean star){
        int espacios;
        for (int i = 0; i < ramas; i++) {
            String Colorbase;
            if (i % 2== 0)
                Colorbase = GREEN;
            else
                Colorbase = DARK_GREEN;

            if (i == 0){

                for (int j = 0; j < triangulo.length; j++) {
                    espacios = (triangulo.length - 1 - j) + (ramas - 1 - i) + 5;
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    if (star && j == 0){
                        System.out.println(YELLOW+CARACTER_ESTRELLA);
                    } else {
                        System.out.println(GREEN+triangulo[j]);
                    }
                }
            } else {
                for (int j = 0; j < triangulo.length; j++) {
                    espacios = (triangulo.length - 1 - j) + (ramas - 1 - i) + 5;
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    triangulo[j] = triangulo[j]+CARACTER_HOJA+CARACTER_HOJA;
                    System.out.println(Colorbase+triangulo[j]);
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
                    triangulo[k] = triangulo[k] + CARACTER_HOJA+CARACTER_HOJA;
                }
            }

            for (int j = 0; j < triangulo.length; j++) {

                int espacios = (triangulo.length - 1 - j) + (ramas - 1 - i) + 5;

                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }

                if (star && i == 0 && j == 0) {
                    System.out.println(YELLOW + CARACTER_ESTRELLA);
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
        int espacios;
        if (ramas <= 1){
            for (int i = 0; i < 2; i++) {
                espacios = (triangulo.length - 2) + (ramas - 1) + 5;
                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 3; j++) {
                    System.out.print(BROWN+CARACTER_TRONCO);
                }
                if (fuego) {
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    System.out.print(BRICK_RED + chimenea[i]);
                }
                System.out.println(RESET);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                espacios = (triangulo.length - 2) + (ramas - 1) + 5;
                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 3; j++) {
                    System.out.print(BROWN+CARACTER_TRONCO);
                }
                if (fuego) {
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    if (i == 0){
                        System.out.print(BRICK_RED + chimenea[i]);
                    } else {
                        System.out.print(BRICK_RED + chimenea[i]);
                        String[] colorFuego = {RED, ORANGE, DARK_ORANGE};
                        for (int k = 0; k < FOC.length(); k++) {
                            char caracter = FOC.charAt(k);
                            if (Math.random() < 0.5) {
                                int colorRandom = (int) (Math.random() * colorFuego.length);
                                System.out.print(colorFuego[colorRandom] + caracter);
                            } else {
                                System.out.print(RED + caracter);
                            }
                        }
                        System.out.print(BRICK_RED + chimenea[i]);
                    }
                }
                System.out.println(RESET);
            }
        }
    }
    /**
     * Utiliza el metodo tronco y añade los regalos
     */
    static void troncoConRegalos(){
        int largoLazo = 1;
        int largoRegalo = 3;
        if (ramas <= 1){
            for (int i = 0; i < 2; i++) {
                int espacios;
                if (i == 0){
                    espacios = (triangulo.length - 2) + (ramas - 1) + 5;
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+CARACTER_TRONCO);
                    }
                    if (fuego) {
                        for (int x = 0; x < espacios; x++) {
                            System.out.print(" ");
                        }
                        System.out.print(BRICK_RED + chimenea[i]);
                    }
                    System.out.println(RESET);

                } else {
                    espacios = (triangulo.length - 2) + (ramas - 1) - largoRegalo + 5;
                    System.out.print(RED+CARACTER_REGALO+RESET+CARACTER_REGALO+RED+CARACTER_REGALO);
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+CARACTER_TRONCO);
                    }
                    if (fuego) {
                        for (int x = 0; x < espacios; x++) {
                            System.out.print(" ");
                        }
                        System.out.print(BRICK_RED + chimenea[i]);
                        String[] colorFuego = {RED, ORANGE, DARK_ORANGE};
                        for (int k = 0; k < FOC.length(); k++) {
                            char caracter = FOC.charAt(k);
                            if (Math.random() < 0.5) {
                                int colorRandom = (int) (Math.random() * colorFuego.length);
                                System.out.print(colorFuego[colorRandom] + caracter);
                            } else {
                                System.out.print(RED + caracter);
                            }
                        }
                        System.out.print(BRICK_RED + chimenea[i]);

                    }
                }
                System.out.println(RESET);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                char lazo;
                if (ramas == 0)
                    lazo = ' ';
                if (i == 0){
                    int espacios = (triangulo.length - 2) + (ramas - 1) + 5;
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+CARACTER_TRONCO);
                    }
                    if (fuego) {
                        for (int x = 0; x < espacios; x++) {
                            System.out.print(" ");
                        }
                        System.out.print(BRICK_RED + chimenea[i]);
                    }
                    System.out.println(RESET);
                } else if (i == 1) {
                    int espacios = (triangulo.length - 2) + (ramas - 1) - largoLazo - 2 + 5;

                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    System.out.print(RESET+CARACTER_LAZO+"  ");
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+CARACTER_TRONCO);
                    }
                    System.out.print("  "+RESET+CARACTER_LAZO);
                    if (fuego) {
                        for (int x = 0; x < espacios; x++) {
                            System.out.print(" ");
                        }
                        System.out.print(BRICK_RED + chimenea[i]);
                        String[] colorFuego = {RED, ORANGE, DARK_ORANGE};
                        for (int k = 0; k < FOC.length(); k++) {
                            char caracter = FOC.charAt(k);
                            if (Math.random() < 0.5) {
                                int colorRandom = (int) (Math.random() * colorFuego.length);
                                System.out.print(colorFuego[colorRandom] + caracter);
                            } else {
                                System.out.print(RED + caracter);
                            }
                        }
                        System.out.print(BRICK_RED + chimenea[i]);
                    }
                    System.out.println(RESET);
                } else {
                    int espacios = (triangulo.length - 2) + (ramas - 1) - largoRegalo - 1 + 5;

                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    System.out.print(DARK_RED+CARACTER_REGALO+RESET+CARACTER_REGALO+DARK_RED+CARACTER_REGALO+" ");
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+CARACTER_TRONCO);
                    }
                    System.out.print(" "+DARK_BLUE+CARACTER_REGALO+RESET+CARACTER_REGALO+DARK_BLUE+CARACTER_REGALO);
                    if (fuego) {
                        for (int x = 0; x < espacios; x++) {
                            System.out.print(" ");
                        }
                        System.out.print(BRICK_RED + chimenea[i]);
                        String[] colorFuego = {RED, ORANGE, DARK_ORANGE};
                        for (int k = 0; k < FOC.length(); k++) {
                            char caracter = FOC.charAt(k);
                            if (Math.random() < 0.5) {
                                int colorRandom = (int) (Math.random() * colorFuego.length);
                                System.out.print(colorFuego[colorRandom] + caracter);
                            } else {
                                System.out.print(RED + caracter);
                            }
                        }
                        System.out.print(BRICK_RED + chimenea[i]);
                    }
                    System.out.println(RESET);
                }
            }
        }
    }

    /**
     * Imprime el suelo y el nombre.
     * @param name Nombre que nos da el usuario
     */
    static void suelo(String name){
        int suelo;
        if (fuego)
            suelo = 7+10+chimenea[0].length();
        else
            suelo = 7+10;
        for (int i = 0; i < ramas; i++) {
            suelo += 2;
        }
        for (int i = 0; i < suelo-2; i++) {
            System.out.print(DARK_ORANGE+CARACTER_SUELO);
        }
        System.out.println(RESET);
        int espacios = (suelo/2) - (name.length()/2);
        for (int i = 0; i < espacios; i++) {
            System.out.print(" ");
        }
        System.out.println(name);
    }
}