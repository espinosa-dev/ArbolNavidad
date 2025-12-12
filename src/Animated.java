/**
 * Ejercicío de Arbol de Navidad para ejecutar en consola de Windows
 * con el siguiente comando: java Animated.java (Debes estar en la ruta del archivo)
 * @author Alvaro Espinosa Montesinos
 */

import java.util.Scanner;

public class Animated {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[38;5;196m";
    public static final String DARK_RED = "\033[38;5;88m";
    public static final String BLUE = "\033[38;5;39m";
    public static final String DARK_BLUE = "\033[38;5;18m";
    public static final String ORANGE = "\033[38;5;208m";
    public static final String DARK_ORANGE = "\033[38;5;166m";
    public static final String GREEN = "\033[1;38;5;22m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BROWN = "\033[38;5;94m";
    public static final String DARK_GREEN = "\033[1;38;5;28m";
    public static String[] colores = {RED, BLUE, ORANGE};
    private static final String[] TRIANGULO_BASE = {"*","***","*****","*******"};
    private static String[] triangulo;

    private static Scanner scn = new Scanner(System.in);
    private static int ramas;
    private static boolean estrella = true;
    private static boolean tronco = true;
    private static boolean decoracion = true;
    private static boolean regalos = true;
    private static boolean nieve = true;
    private static String nombre;

    public static void main(String[] args) {
        //ramas = Integer.parseInt(args[0]);
        menu();
        if (decoracion) {
            try {
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
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace(); // Salida de error estándar la traza completa del error
                // mostrando dónde ocurrió y en qué contexto
            }
        } else {
            resetearTriangulo();
            ramas(ramas, estrella);
            if (tronco) tronco();
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
        if (decoracion){
            do {
                System.out.print("Quieres nieve (S/N): ");
                answer = scn.nextLine().charAt(0);
            } while (!(answer == 'S' || answer == 's' || answer == 'N' || answer == 'n'));
            nieve = answer == 83 || answer == 115;
        }
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
                        System.out.println(YELLOW+triangulo[0]+RESET);
                    } else {
                        System.out.println(GREEN+triangulo[j]+RESET);
                    }
                }
            } else if (i % 2 == 0) {
                for (int j = 0; j < triangulo.length; j++) {
                    int espacios = (triangulo.length - 1 - j) + (ramas - 1 - i);
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    triangulo[j] = triangulo[j]+"**";
                    System.out.println(GREEN+triangulo[j]+RESET);
                }
            } else {
                for (int j = 0; j < triangulo.length; j++) {
                    int espacios = (triangulo.length - 1 - j) + (ramas - 1 - i);
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    triangulo[j] = triangulo[j]+"**";
                    System.out.println(DARK_GREEN +triangulo[j]+RESET);
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
        espacioPantalla();
        for (int i = 0; i < ramas; i++) {
            String colorBase;
            if (i % 2 == 0) colorBase = GREEN;
            else colorBase = DARK_GREEN;

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
                    System.out.println(YELLOW + triangulo[0] + RESET);
                } else {
                    String lineaActual = triangulo[j];
                    for (int k = 0; k < lineaActual.length(); k++) {
                        char caracter = lineaActual.charAt(k);
                        if (Math.random() < 0.05) {
                            int colorRandom = (int) (Math.random() * colores.length);
                            System.out.print(colores[colorRandom] + caracter);
                        } else if (Math.random() < 0.05 && nieve) {
                            System.out.print(RESET+caracter);
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
     * Method para dibujar el tronco
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
            for (int i = 0; i < 5; i++) {
                int espacios = (triangulo.length - 4) + (ramas - 1);
                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 5; j++) {
                    System.out.print(BROWN+"*");
                }
                System.out.println(RESET);
            }
        } else if (ramas < 30) {
            for (int i = 0; i < 6; i++) {
                int espacios = (triangulo.length - 5) + (ramas - 1);
                for (int x = 0; x < espacios; x++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < 6; j++) {
                    System.out.print(BROWN+"*");
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
                    espacios = (triangulo.length - 2) + (ramas - 1);
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+"*");
                    }
                } else {
                    espacios = (triangulo.length - 2) + (ramas - 1) - largoRegalo;
                    System.out.print(RED+"*"+RESET+"*"+RED+"*");
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+"*");
                    }
                }
                System.out.println(RESET);
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
                } else if (i == 1) {
                    int espacios = (triangulo.length - 2) + (ramas - 1) - largoLazo - 2;

                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    System.out.print(RESET+"ღ"+"  ");
                    for (int j = 0; j < 3; j++) {
                        System.out.print(BROWN+"*");
                    }
                    System.out.print("  "+RESET+"ღ");
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
        System.out.println();
        int espacios = (suelo/2) - (name.length()/2);
        for (int i = 0; i < espacios; i++) {
            System.out.print(" ");
        }
        System.out.println(name);
        System.out.println("Ctrl + C ⬅ Finalizar el programa (Luego introducir \"S\")");
    }

    static void espacioPantalla(){
        for (int i = 0; i < 5; i++) {
            System.out.println();
        }
    }
}