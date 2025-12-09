import java.util.Scanner;

public class Animated {
    // Códigos de colores ANSI
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

    public static String[] colores = {RED, DARK_RED, BLUE, DARK_BLUE, ORANGE, DARK_ORANGE};

    // Plantilla base inmutable del triángulo
    private static final String[] TRIANGULO_BASE = {"*","***","*****","*******"};
    // Triángulo que usaremos para pintar
    private static String[] triangulo;

    private static Scanner scn = new Scanner(System.in);
    private static int ramas;
    private static boolean estrella = false;
    private static boolean tronco = false;
    private static boolean decoracion = false;

    public static void main(String[] args) {
        menu();

        if (decoracion) {
            try {
                // Bucle infinito para la animación
                while (true) {
                    // Limpiamos consola (Efecto CLS)
                    limpiarPantalla();
                    resetearTriangulo();
                    decoration(ramas, estrella);
                    if (tronco) tronco();

                    // 4. Esperamos medio segundo
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            resetearTriangulo();
            ramas(ramas, estrella);
            if (tronco) tronco();
        }
    }

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

    static void resetearTriangulo() {
        triangulo = new String[TRIANGULO_BASE.length];
        for (int i = 0; i < TRIANGULO_BASE.length; i++) {
            triangulo[i] = TRIANGULO_BASE[i];
        }
    }

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
        do {
            System.out.print("Quieres decorarción (S/N): ");
            answer = scn.nextLine().charAt(0);
        } while (!(answer == 'S' || answer == 's' || answer == 'N' || answer == 'n'));
        decoracion = answer == 83 || answer == 115;
    }


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

    // Tu método decoration optimizado
    static void decoration(int ramas, boolean star) {
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
}