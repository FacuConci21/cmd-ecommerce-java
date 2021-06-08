package application;

import java.util.Scanner;
import java.util.Vector;

public class CmdEcommerce {

    // Private attributes
    private Vector<Product> productsList;
    int optionSelection;

    // Private methods
    private void validateInput(int optionsQuantity, String message) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print(message);
            optionSelection = scanner.nextInt();
        } while (optionSelection > optionsQuantity || optionSelection < 0);
    }

    private boolean optionsMenu(String [] optionsList, String message) {

        for (int i = 0; i < optionsList.length; i++) {
            System.out.println( (i+1) + " - " + optionsList[i]);
        }

        validateInput(optionsList.length, message);

        if (optionSelection != 0) return true;

        return false;
    }

    private int createOption() {
        String[] optionsList = {
                "Bebida alcoholica",
                "Lacteo",
                "Fiambre"
        };
        String optionMessage = "Que tipo de producto desea registrar?: ";
        Scanner scanner = new Scanner(System.in);
        Product newProductInstance;
        String productName, productDescription;
        float productPrice;
        int productStock;

        optionsMenu(optionsList, optionMessage);

        // Asking for data
        System.out.print("Nombre: "); productName = scanner.nextLine();
        System.out.print("Description: "); productDescription = scanner.nextLine();
        System.out.print("Precio: "); productPrice = scanner.nextFloat();
        System.out.print("Cant. en Stock: "); productStock = scanner.nextInt();

        switch (optionSelection) {
            case 1:
            {
                float beverageLiter;
                int beveragePercentage;

                System.out.print("Litros: "); beverageLiter = scanner.nextFloat();
                System.out.print("Porcentaje de alcohol: "); beveragePercentage = scanner.nextInt();

                newProductInstance = new AlcoholicBeverage(
                        1, productName, productDescription, productPrice, productStock,
                        beverageLiter, beveragePercentage
                );

                productsList.add(newProductInstance);
                break;
            }
            case 2:
            {
                System.out.println("gfasrgwrgwrgwrgaw");
                break;
            }
            case 3:
            {
                System.out.println("yuyhsthaethset");
                break;
            }

        }

        System.out.println("Producto: \'" + productName + "\'; \'" + productDescription + "\'.\nfue dado de alta." );
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
        productsList = new Vector<Product>();
    }

    // Public methods
    public int main_loop_program() {

        String[] optionsList = {
                "Alta de producto",
                "Modificacion de producto",
                "Baja de producto",
                "Lista de productos"
        };
        String menuMessage = "Elija una opcion: ";

        /*      PROGRAM LOOP        */
        while (optionsMenu(optionsList, menuMessage)) {
            switch (optionSelection) {
                case 1:
                {
                    this.createOption();
                    break;
                }
                case 2:
                {
                    this.updateOption();
                    break;
                }
                case 3:
                {
                    this.deleteOption();
                    break;
                }
                case 4:
                {
                    this.listingOption();
                    break;
                }
            }
        }

        return 0;
    }

}
