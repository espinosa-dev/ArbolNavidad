import java.util.Scanner;

public class Main {
    public static final String RESET = "\033[0m";
    public static final String RED = "\033[0;31m";
    public static final String GREEN = "\033[0;32m";
    public static final String BLUE = "\033[0;34m";
    public static final String YELLOW = "\033[0;33m";
    public static final String BROWN = "\033[38;5;94m";
    public static String[] colores = {"\033[1;90m","\033[1;91m","\033[1;92m","\033[1;93m","\033[1;94m","\033[1;95m","\033[1;96m","\033[1;97m"};
    private static String[] triangulo = {"*","***","*****","*******"};
    private static Scanner scn = new Scanner(System.in);
    private static int ramas;
    private static boolean estrella = false;
    private static boolean tronco = false;
    public static void main(String[] args) {
        menu(ramas, estrella, tronco);
        ramas(ramas, estrella);
        if (tronco)
            tronco();
    }
    static void menu(int rama, boolean star, boolean tronc){
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
                        System.out.println(YELLOW+triangulo[0]);
                    } else {
                        System.out.println(GREEN+triangulo[j]);
                    }
                }
            } else {
                for (int j = 0; j < triangulo.length; j++) {
                    int espacios = (triangulo.length - 1 - j) + (ramas - 1 - i);
                    for (int x = 0; x < espacios; x++) {
                        System.out.print(" ");
                    }
                    triangulo[j] = triangulo[j]+"**";
                    System.out.println(GREEN+triangulo[j]);
                }
            }
        }
    }
    static void tronco(){
        for (int i = 0; i < 3; i++) {
            int espacios = (triangulo.length - 2) + (ramas - 1);
            for (int x = 0; x < espacios; x++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 3; j++) {
                System.out.print(BROWN+"*");
            }
            System.out.println();
        }
    }
}