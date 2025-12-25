/**
 * Ejercicío de Arbol de Navidad para ejecutar en consola de Windows
 * con el siguiente comando: java Animated.java (Debes estar en la ruta del archivo)
 * @author Alvaro Espinosa Montesinos
 */

import java.util.Scanner;
import static SETTINGS.Colours.*;

public class Animated {
    public static String[] colores = {RED, BLUE, ORANGE, BALL_RED, BALL_BLUE, BALL_GOLD, BALL_PINK, BALL_SILVER};
    private static final String[] TRIANGULO_BASE = {"♠","♠♠♠","♠♠♠♠♠","♠♠♠♠♠♠♠"};
    private static final String[] chimenea = {"▓▀▀▀▀▓","▓","▓"};
    private static final char CARACTER_TRONCO = '█';
    private static final char CARACTER_HOJA = '♠';
    private static final char CARACTER_ESTRELLA = '★';
    private static final char CARACTER_NIEVE = '❅';
    private static final char CARACTER_REGALO = '▓';
    private static final String FOC = "WWWW";
    private static char CARACTER_LAZO= 'ღ';
    private static final char CARACTER_SUELO = '▀';
    private static String[] triangulo;

    private static final Scanner scn = new Scanner(System.in);
    private static int ramas;
    private static boolean estrella = true;
    private static boolean tronco = true;
    private static final boolean DECORACION = true;
    private static boolean regalos = true;
    private static boolean nieve = true;
    private static boolean fuego = false;
    private static String nombre;

    public static void main(String[] args) throws InterruptedException {
        //ramas = Integer.parseInt(args[0]);
        menu();
                // Bucle infinito para la animación
        while (true) {
                    // Limpiamos consola (Efecto CLS)
            limpiarPantalla();
            resetearTriangulo();
            decoration(ramas, estrella);
            if (tronco && regalos) {
                troncoConRegalos();
            }
            else if (tronco){
                tronco();
            }
            suelo(nombre);
            // 4. Esperamos
            Thread.sleep(450);
        }
    }

    /**
     * Method para limpiar la pantalla y depende del sistema operativo lo realiza de una forma u otra
     */
    static void limpiarPantalla() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Opción A: Invocar CMD para hacer un cls real
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Opción B: Códigos ANSI para Linux/Mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Si falla, imprimimos líneas vacías como plan B
            for(int i = 0; i < 50; i++) System.out.println();
        }
    }

    /**
     * Reseteo el triangulo para volver a dibujarlo en bucle
     */
    static void resetearTriangulo() {
        triangulo = new String[TRIANGULO_BASE.length];
        System.arraycopy(TRIANGULO_BASE, 0, triangulo, 0, TRIANGULO_BASE.length);
    }
    /**
     * Imprime el menú del programa
     */
    static void menu(){
        char answer;
        do {
            System.out.print("Cuantas ramas deseas ingresar: ");
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
        if (DECORACION){
            do {
                System.out.print("Quieres nieve (S/N): ");
                answer = scn.nextLine().charAt(0);
                answer = Character.toLowerCase(answer);
            } while (!(answer == 's' || answer == 'n'));
            nieve = answer == 's';
        }
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
     * Imprime las ramas con decoracion
     * @param ramas Numeros de ramas
     * @param star Si tiene estrella o no
     */
    static void decoration(int ramas, boolean star) {
        espacioPantalla();
        for (int i = 0; i < ramas; i++) {
            String colorBase;
            if (i % 2 == 0) colorBase = GREEN;
            else colorBase = DARK_GREEN;

            if (i > 0) {
                for (int k = 0; k < triangulo.length; k++) {
                    triangulo[k] = triangulo[k] + CARACTER_HOJA+CARACTER_HOJA;
                }
            }

            for (int j = 0; j < triangulo.length; j++) {
                int espacios = (triangulo.length - 1 - j) + (ramas - 1 - i) + 5;
                for (int k = 0; k < espacios; k++) {
                    if (Math.random() < 0.005 && nieve)
                        System.out.print(WHITE_BOLD_BRIGHT+CARACTER_NIEVE);
                    else
                        System.out.print(" ");
                }

                if (star && i == 0 && j == 0) {
                    System.out.println(YELLOW + CARACTER_ESTRELLA + RESET);
                } else {
                    String lineaActual = triangulo[j];
                    for (int k = 0; k < lineaActual.length(); k++) {
                        char caracter = lineaActual.charAt(k);
                        if (Math.random() < 0.10) {
                            int colorRandom = (int) (Math.random() * colores.length);
                            System.out.print(colores[colorRandom] + caracter);
                        } else {
                            System.out.print(colorBase + caracter);
                        }
                    }
                    espacios = (triangulo.length - 1 - j) + (ramas - 1 - i) + 5;
                    for (int k = 0; k < espacios; k++) {
                        if (Math.random() < 0.005 && nieve)
                            System.out.print(WHITE_BOLD_BRIGHT+CARACTER_NIEVE);
                        else
                            System.out.print(" ");
                    }
                    System.out.println(RESET);
                }
            }
        }
    }
    /**
     * Method para dibujar el tronco
     * depende del tamaño de la rama dibuja un tronco u otro
     */
    static void tronco(){
        if (ramas <= 1){
            for (int i = 0; i < 2; i++) {
                int espacios = (triangulo.length - 2) + (ramas - 1) + 5;
                for (int k = 0; k < espacios; k++) {
                    if (Math.random() < 0.05 && nieve)
                        System.out.print(WHITE_BOLD_BRIGHT+CARACTER_NIEVE);
                    else
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
        } else if (ramas < 10) {
            for (int i = 0; i < 3; i++) {
                int espacios = (triangulo.length - 2) + (ramas - 1) + 5;
                for (int k = 0; k < espacios; k++) {
                    if (Math.random() < 0.05 && nieve)
                        System.out.print(WHITE_BOLD_BRIGHT+CARACTER_NIEVE);
                    else
                        System.out.print(" ");
                }

                for (int j = 0; j < 3; j++) {
                    System.out.print(BROWN+CARACTER_TRONCO);
                }
                if (fuego) {
                    for (int k = 0; k < espacios; k++) {
                        if (Math.random() < 0.05 && nieve)
                            System.out.print(WHITE_BOLD_BRIGHT+"*");
                        else
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
                                int colorRandom = (int) (Math.random() * colores.length);
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
        } else if (ramas < 20) {
            for (int i = 0; i < 5; i++) {
                int espacios = (triangulo.length - 4) + (ramas - 1) + 5;
                for (int k = 0; k < espacios; k++) {
                    if (Math.random() < 0.05 && nieve)
                        System.out.print(WHITE_BOLD_BRIGHT+CARACTER_NIEVE);
                    else
                        System.out.print(" ");
                }
                for (int j = 0; j < 5; j++) {
                    System.out.print(BROWN+CARACTER_TRONCO);
                }
                if (fuego) {
                    for (int k = 0; k < espacios; k++) {
                        if (Math.random() < 0.05 && nieve)
                            System.out.print(WHITE_BOLD_BRIGHT+"*");
                        else
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
                                int colorRandom = (int) (Math.random() * colores.length);
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
        } else if (ramas < 30) {
            for (int i = 0; i < 6; i++) {
                int espacios = (triangulo.length - 5) + (ramas - 1) + 5;
                for (int k = 0; k < espacios; k++) {
                    if (Math.random() < 0.05 && nieve)
                        System.out.print(WHITE_BOLD_BRIGHT+CARACTER_NIEVE);
                    else
                        System.out.print(" ");
                }
                for (int j = 0; j < 6; j++) {
                    System.out.print(BROWN+CARACTER_TRONCO);
                }
                if (fuego) {
                    for (int k = 0; k < espacios; k++) {
                        if (Math.random() < 0.05 && nieve)
                            System.out.print(WHITE_BOLD_BRIGHT+"*");
                        else
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
                                int colorRandom = (int) (Math.random() * colores.length);
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

    static void troncoConRegalos(){
        int largoLazo = 1;
        int largoRegalo = 3;
        if (ramas <= 1){
            for (int i = 0; i < 2; i++) {
                int espacios;
                if (i == 0){
                    espacios = (triangulo.length - 2) + (ramas - 1) + 5;
                    System.out.print(" ".repeat(espacios));
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+CARACTER_TRONCO);
                    }
                } else {
                    espacios = (triangulo.length - 2) + (ramas - 1) - largoRegalo + 5;
                    System.out.print(RED+CARACTER_REGALO+RESET+CARACTER_REGALO+RED+CARACTER_REGALO);
                    System.out.print(" ".repeat(espacios));
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+CARACTER_TRONCO);
                    }
                }
                System.out.println(RESET);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                char lazo;
                if (ramas == 0)
                    CARACTER_LAZO = ' ';
                if (i == 0){
                    int espacios = (triangulo.length - 2) + (ramas - 1)  + 5;
                    System.out.print(" ".repeat(espacios));
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
                    int espacios = (triangulo.length - 2) + (ramas - 1) - largoLazo - 2  + 5;
                    System.out.print(" ".repeat(espacios));

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
                    System.out.print(" ".repeat(espacios));

                    System.out.print(RED+CARACTER_REGALO+RESET+CARACTER_REGALO+RED+CARACTER_REGALO+" ");
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+CARACTER_TRONCO);
                    }
                    System.out.print(" "+BLUE+CARACTER_REGALO+RESET+CARACTER_REGALO+BLUE+CARACTER_REGALO);
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
        System.out.print(" ".repeat(espacios));
        System.out.println(GOLD+name+RESET);
        System.out.print(RED_BACKGROUND_BRIGHT+WHITE_BOLD_BRIGHT+"Ctrl + C <- Finalizar el programa (Luego introducir \"S\")"+RESET);
    }

    static void espacioPantalla(){
        for (int i = 0; i < 2; i++) {
            System.out.println(RESET);
        }
    }
}