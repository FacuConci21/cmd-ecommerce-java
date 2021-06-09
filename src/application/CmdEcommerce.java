package application;

import java.util.Scanner;
import java.util.Vector;

public final class CmdEcommerce {

    // Private attributes
    private Vector<Product> productsList;
    int optionSelection;

    // Private methods
    private void validateInput(int optionsQuantity, String message) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print(message);
            this.optionSelection = scanner.nextInt();
        } while (this.optionSelection > optionsQuantity || this.optionSelection < 0);
    }

    private boolean optionsMenu(String [] optionsList, String message) {

        for (int i = 0; i < optionsList.length; i++) {
            System.out.println( (i+1) + " - " + optionsList[i]);
        }

        this.validateInput(optionsList.length, message);

        if (optionSelection != 0) return true;

        return false;
    }

    private int createOption() {
        /*      DECLARATIONS        */
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
        int productStock, productId;

        this.optionsMenu(optionsList, optionMessage);

        productId = (productsList.isEmpty()) ? 1 : productsList.size();
        // Asking for data
        System.out.print("Nombre: "); productName = scanner.next();
        System.out.print("Description: "); productDescription = scanner.next();
        System.out.print("Precio: "); productPrice = (float) scanner.nextFloat();
        System.out.print("Cant. en Stock: "); productStock = scanner.nextInt();

        switch (this.optionSelection) {
            case 1:
            {
                float beverageLiter;
                int beveragePercentage;

                System.out.print("Litros: "); beverageLiter = (float) scanner.nextFloat();
                System.out.print("Porcentaje de alcohol: "); beveragePercentage = scanner.nextInt();

                newProductInstance = new AlcoholicBeverage(
                        productId, productName, productDescription, productPrice, productStock, beverageLiter, beveragePercentage
                );

                this.productsList.add(newProductInstance);
                break;
            }
            case 2:
            {
                int dairyFatPercentage;
                String dairyDateExpiry, dairySingleVitamin;
                Vector<String> dairyVitamins = new Vector<String>();

                System.out.print("Ingrese las vitaminas: ");
                for (int i = 0; i < 3; i++) {
                    dairySingleVitamin = scanner.next();System.out.print(" ");
                    dairyVitamins.add(dairySingleVitamin);
                }
                System.out.print("Porcentaje de grasa: "); dairyFatPercentage = scanner.nextInt();
                System.out.print("Fecha de caducidad: "); dairyDateExpiry = scanner.next();

                /**/
                newProductInstance = new Dairy(
                        dairyFatPercentage, dairyDateExpiry, dairyVitamins, productId, productName, productDescription, productPrice, productStock
                );

                this.productsList.add(newProductInstance);
                break;
            }
            case 3:
            {
                String stiffDateExpiry;
                int stiffFatPercentage;

                System.out.print("Fecha de caducidad: "); stiffDateExpiry = scanner.next();
                System.out.print("Porcentaje de grasa: "); stiffFatPercentage = scanner.nextInt();

                newProductInstance = new Stiff(
                        productId, productName, productDescription, productPrice, productStock, stiffDateExpiry, stiffFatPercentage
                );

                this.productsList.add(newProductInstance);
                break;
            }

        }

        System.out.println("\tProducto: \'" + productName + "\'; \'" + productDescription + "\',\n\tfue dado de alta exitosamente." );
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
        while (this.optionsMenu(optionsList, menuMessage)) {
            switch (this.optionSelection) {
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
