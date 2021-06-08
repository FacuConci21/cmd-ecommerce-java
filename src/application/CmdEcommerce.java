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

    private int createOption() {
        System.out.println("Alta de un producto");
        return 0;
    }
    private int updateOption() {
        System.out.println("Modificar un producto");
        return 0;
    }
    private int deleteOption() {
        System.out.println("Baja de un producto");
        return 0;
    }
    private int listingOption() {
        System.out.println("Lista de productos");
        return 0;
    }

    // Constructors
    public CmdEcommerce() {
        productsList = new Vector<Object>();
    }

    // Public methods
    public int main_loop_program() {

        /*      PROGRAM LOOP        */
        while (options_menu()) {
            switch (optionSelection) {
                case 1:
                {
                    this.createOption();
                }
                case 2:
                {
                    this.updateOption();
                }
                case 3:
                {
                    this.deleteOption();
                }
                case 4:
                {
                    this.listingOption();
                }
            }
        }

        return 0;
    }

}
