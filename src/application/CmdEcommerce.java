package application;

import java.util.Scanner;
import java.util.Vector;

public class CmdEcommerce {

    // Private attributes
    private Vector<Object> productsList;
    int optionSelection;

    // Private methods
    private void validateInput() {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print("Elija una opcion: ");
            optionSelection = scanner.nextInt();
        } while (optionSelection > 4 || optionSelection < 0);
    }

    private boolean options_menu() {
        System.out.println("1 - Alta");
        System.out.println("2 - Modificacion");
        System.out.println("3 - Baja");
        System.out.println("4 - Listar");
        System.out.println("0 - Salir");

        validateInput();

        if (optionSelection != 0) return true;

        return false;
    }

    // Constructors
    public CmdEcommerce() {
        productsList = new Vector<Object>();
    }

    // Public methods
    public int main_loop_program() {

        while (options_menu()) {
            System.out.println("---------------------------------------------");
        }

        System.out.println(productsList.isEmpty());
        return 0;
    }

}
