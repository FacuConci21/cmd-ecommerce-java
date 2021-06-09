package application;

import java.util.Scanner;
import java.util.Vector;

public final class CmdEcommerce {

    // Private attributes
    private final Vector<Product> productsList;
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
            System.out.println( (i) + " - " + optionsList[i]);
        }

        this.validateInput(optionsList.length, message);

        return (optionSelection != 0);
    }

    private int createOption() {
        /*      DECLARATIONS        */
        String[] optionsList = {
                "Bebida alcohólica",
                "Lácteo",
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
        System.out.print("Precio: "); productPrice = scanner.nextFloat();
        System.out.print("Cant. en Stock: "); productStock = scanner.nextInt();

        switch (this.optionSelection) {
            case 1:
            {
                float beverageLiter;
                int beveragePercentage;

                System.out.print("Litros: "); beverageLiter = scanner.nextFloat();
                System.out.print("Porcentaje de alcohol: "); beveragePercentage = scanner.nextInt();

                newProductInstance = new AlcoholicBeverage(
<<<<<<< HEAD
                        1, optionSelection,productName, productDescription, productPrice, productStock, beverageLiter, beveragePercentage
=======
                        productId, productName, productDescription, productPrice, productStock, beverageLiter, beveragePercentage
>>>>>>> 22930b185536ec6892cacd8bedcc82a3fa8771ba
                );

                this.productsList.add(newProductInstance);
                break;
            }
            case 2:
            {
                int dairyFatPercentage;
                String dairyDateExpiry, dairySingleVitamin;
                Vector<String> dairyVitamins = new Vector<>();

                System.out.print("Ingrese las vitaminas: ");
                for (int i = 0; i < 3; i++) {
                    dairySingleVitamin = scanner.next();System.out.print(" ");
                    dairyVitamins.add(dairySingleVitamin);
                }
                System.out.print("Porcentaje de grasa: "); dairyFatPercentage = scanner.nextInt();
                System.out.print("Fecha de caducidad: "); dairyDateExpiry = scanner.next();

                /**/
                newProductInstance = new Dairy(
<<<<<<< HEAD
                        dairyFatPercentage,dairyDateExpiry, dairyVitamins, 1, optionSelection,productName, productDescription, productPrice, productStock
=======
                        dairyFatPercentage, dairyDateExpiry, dairyVitamins, productId, productName, productDescription, productPrice, productStock
>>>>>>> 22930b185536ec6892cacd8bedcc82a3fa8771ba
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

        System.out.println("\tProducto: '" + productName + "'; '" + productDescription + "',\n\tfue dado de alta exitosamente." );
        return 0;
    }

    private int updateOption(){

        String[] optionsList = new String[this.productsList.size()];
        String productName, productDescription;
        float productPrice;
        int productStock;
        Product newProductInstance;
        if (optionsList.length == 0){
            System.out.println("No hay productos para modificar." );
        } else{
            String optionMessage = "Que producto desea modificar?: ";
            Scanner scanner = new Scanner(System.in);

            for(int i = 0; i < this.productsList.size(); i++){
                    optionsList[i] = this.productsList.elementAt(i).getName();
            };

            this.optionsMenu(optionsList, optionMessage);

            /*Request of data to modify*/

            System.out.print("Name: "); productName = scanner.nextLine();
            System.out.print("Description: "); productDescription = scanner.nextLine();
            System.out.print("Precio: "); productPrice = scanner.nextFloat();
            System.out.print("Cant. en Stock: "); productStock = scanner.nextInt();


            switch(this.productsList.elementAt(optionSelection - 1).getCategory()){
                case 1: {

                    float beverageLiter;
                    int beveragePercentage;

                    System.out.print("Litros: "); beverageLiter = scanner.nextFloat();
                    System.out.print("Porcentaje de alcohol: "); beveragePercentage = scanner.nextInt();

                    newProductInstance = new AlcoholicBeverage(this.productsList.elementAt(optionSelection - 1).getId(), optionSelection, productName,
                            productDescription, productPrice, productStock, beverageLiter, beveragePercentage);

                    this.productsList.setElementAt(newProductInstance, optionSelection - 1);
                    break;
                }
                case 2: {

                    int dairyFatPercentage;
                    String dairyDateExpiry, dairySingleVitamin;
                    Vector<String> dairyVitamins = new Vector<String>();

                    System.out.print("Porcentaje de grasa: "); dairyFatPercentage = scanner.nextInt();
                    System.out.print("Fecha de caducidad: "); dairyDateExpiry = scanner.nextLine();
                    System.out.print("Ingrese las vitaminas: ");
                    for (int i = 0; i < 3; i++) {
                        System.out.print(" "); dairySingleVitamin = scanner.next();
                        dairyVitamins.add(dairySingleVitamin);
                    }

                    newProductInstance = new Dairy(
                            dairyFatPercentage,dairyDateExpiry, dairyVitamins, this.productsList.elementAt(optionSelection - 1).getId(), optionSelection,productName, productDescription, productPrice, productStock
                    );

                    this.productsList.setElementAt(newProductInstance, optionSelection - 1);
                    break;
                }
                case 3: {
                    int stiffFatPercentage;
                    String stiffDateExpiry;

                    System.out.print("Porcentaje de grasa: "); stiffFatPercentage = scanner.nextInt();
                    System.out.print("Fecha de caducidad: "); stiffDateExpiry = scanner.nextLine();

                    newProductInstance = new Stiff(this.productsList.elementAt(optionSelection - 1).getId(),
                            optionSelection, productName, productDescription, productPrice, productStock, stiffDateExpiry,
                            stiffFatPercentage);

                    this.productsList.setElementAt(newProductInstance, optionSelection - 1);
                }
            }

            System.out.println("Producto: " + productName + " fue modificado correctamente." );
        }

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
        this.productsList = new Vector<>();
    }

    // Public methods
    public int main_loop_program() {

        String[] optionsList = {
                "Salir",
                "Alta de producto",
                "Modificacion de producto",
                "Baja de producto",
                "Lista de productos"

        };
        String menuMessage = "Elija una opcion: ";
        int programResult = 0;

        /*      PROGRAM LOOP        */
        while (this.optionsMenu(optionsList, menuMessage)) {
            switch (this.optionSelection) {
                case 1:
                {
                    programResult = this.createOption();
                    break;
                }
                case 2:
                {
                    programResult = this.updateOption();
                    break;
                }
                case 3:
                {
                    programResult = this.deleteOption();
                    break;
                }
                case 4:
                {
                    programResult = this.listingOption();
                    break;
                }
            }
        }

        return programResult;
    }

}
