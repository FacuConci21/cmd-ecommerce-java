package application;

import java.util.Scanner;
import java.util.Vector;

public final class CmdEcommerce {

    // Private attributes
    private final Vector<Product> productsList;
    int optionSelection;
    //Colores
    public String azul = "\033[34m";
    public String rojo = "\033[31m";
    public String default_color = "\u001B[0m";

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
            System.out.println( default_color+ (i + 1) + " - " + optionsList[i]);
        }

        this.validateInput(optionsList.length, message);

        return (optionSelection != 0 && optionSelection != 5);
    }

    private int createOption() {
        /*      DECLARATIONS        */
        System.out.println(azul+ "_______________________");
        System.out.println(azul+ " Registro de productos ");
        System.out.println(azul+ "_______________________");

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

        productId = (productsList.isEmpty()) ? 1 : productsList.size() + 1;
        // Asking for data
        System.out.print(default_color+"Nombre: "); productName = scanner.next();
        System.out.print(default_color+"Precio: "); productPrice = scanner.nextFloat();
        System.out.print(default_color+"Cant. en Stock: "); productStock = scanner.nextInt();
        System.out.print(default_color+"Description: "); productDescription = scanner.nextLine();

        switch (this.optionSelection) {
            case 1:
            {
                float beverageLiter;
                int beveragePercentage;

                System.out.print(default_color+"Litros: "); beverageLiter = scanner.nextFloat();
                System.out.print(default_color+"Porcentaje de alcohol: "); beveragePercentage = scanner.nextInt();

                newProductInstance = new AlcoholicBeverage(
                        productId, this.optionSelection,productName, productDescription, productPrice, productStock, beverageLiter, beveragePercentage
                );

                this.productsList.add(newProductInstance);
                break;
            }
            case 2:
            {
                int dairyFatPercentage;
                String dairyDateExpiry, dairySingleVitamin;
                Vector<String> dairyVitamins = new Vector<>();

                System.out.print(default_color+"Ingrese las vitaminas: ");
                for (int i = 0; i < 3; i++) {
                    dairySingleVitamin = scanner.next();System.out.print(" ");
                    dairyVitamins.add(dairySingleVitamin);
                }
                System.out.print(default_color+"Porcentaje de grasa: "); dairyFatPercentage = scanner.nextInt();
                System.out.print(default_color+"Fecha de caducidad: "); dairyDateExpiry = scanner.next();

                newProductInstance = new Dairy(
                        dairyFatPercentage, dairyDateExpiry, dairyVitamins, productId, this.optionSelection,productName, productDescription, productPrice, productStock
                );

                this.productsList.add(newProductInstance);
                break;
            }
            case 3:
            {
                String stiffDateExpiry;
                int stiffFatPercentage;

                System.out.print(default_color+"Fecha de caducidad: "); stiffDateExpiry = scanner.next();
                System.out.print(default_color+"Porcentaje de grasa: "); stiffFatPercentage = scanner.nextInt();

                newProductInstance = new Stiff(
                        productId, this.optionSelection,productName, productDescription, productPrice, productStock, stiffDateExpiry, stiffFatPercentage
                );

                this.productsList.add(newProductInstance);
                break;
            }

        }

        System.out.println(azul + "\tProducto: '" + productName + "'; '" + productDescription + "',\n\tfue dado de alta exitosamente." );
        return 0;
    }

    private int updateOption(){

        String[] optionsList = new String[this.productsList.size()];
        String productName, productDescription;
        float productPrice;
        int productStock;
        Product newProductInstance;
        if (optionsList.length == 0){
            System.out.println(rojo + "No hay productos para modificar." );
        } else{
            String optionMessage = "Que producto desea modificar?: ";
            Scanner scanner = new Scanner(System.in);

            for(int i = 0; i < this.productsList.size(); i++){
                    optionsList[i] = this.productsList.elementAt(i).getName();
            }

            this.optionsMenu(optionsList, optionMessage);

            /*Request of data to modify*/

            System.out.print(default_color+"Name: "); productName = scanner.nextLine();
            System.out.print(default_color+"Precio: "); productPrice = scanner.nextFloat();
            System.out.print(default_color+"Cant. en Stock: "); productStock = scanner.nextInt();
            System.out.print(default_color+"Description: "); productDescription = scanner.nextLine();


            switch(this.productsList.elementAt(optionSelection - 1).getCategory()){
                case 1: {

                    float beverageLiter;
                    int beveragePercentage;

                    System.out.print(default_color+"Litros: "); beverageLiter = scanner.nextFloat();
                    System.out.print(default_color+"Porcentaje de alcohol: "); beveragePercentage = scanner.nextInt();

                    newProductInstance = new AlcoholicBeverage(this.productsList.elementAt(optionSelection - 1).getId(), optionSelection, productName,
                            productDescription, productPrice, productStock, beverageLiter, beveragePercentage);

                    this.productsList.setElementAt(newProductInstance, optionSelection - 1);
                    break;
                }
                case 2: {

                    int dairyFatPercentage;
                    String dairyDateExpiry, dairySingleVitamin;
                    Vector<String> dairyVitamins = new Vector<>();

                    System.out.print(default_color+"Porcentaje de grasa: "); dairyFatPercentage = scanner.nextInt();
                    System.out.print(default_color+"Fecha de caducidad: "); dairyDateExpiry = scanner.nextLine();
                    System.out.print(default_color+"Ingrese las vitaminas: ");
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

                    System.out.print(default_color+"Porcentaje de grasa: "); stiffFatPercentage = scanner.nextInt();
                    System.out.print(default_color+"Fecha de caducidad: "); stiffDateExpiry = scanner.nextLine();

                    newProductInstance = new Stiff(this.productsList.elementAt(optionSelection - 1).getId(),
                            optionSelection, productName, productDescription, productPrice, productStock, stiffDateExpiry,
                            stiffFatPercentage);

                    this.productsList.setElementAt(newProductInstance, optionSelection - 1);
                }
            }

            System.out.println(azul + "Producto: " + productName + " fue modificado correctamente." );
        }

        return 0;
    }

    private int deleteOption() {
        String[] optionsList = new String[this.productsList.size()];

        if (optionsList.length == 0){
            System.out.println(rojo + "No hay productos para eliminar." );
        } else {
            String optionMessage = "Seleccione el producto a eliminar: ";
            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < this.productsList.size(); i++) {
                optionsList[i] = this.productsList.elementAt(i).getName();
            }

            this.optionsMenu(optionsList, optionMessage);

            this.productsList.removeElementAt(optionSelection -1 );

            System.out.println(azul + "Producto con ID " + (optionSelection) +" eliminado correctamente");

        }
        return 0;
    }

    private int listingOption() {

        if(this.productsList.size() > 0 ){
            System.out.println(azul + "Lista de productos");
            for (int i = 0; i < this.productsList.size(); i++) {
                System.out.println(this.productsList.elementAt(i).toString());
            }
        } else {
            System.out.println(rojo + "No hay productos cargados! Por favor, dé de alta uno como mínimo");
        }



        return 0;
    }

    // Constructors
    public CmdEcommerce() {
        this.productsList = new Vector<>();
    }

    // Public methods
    public int main_loop_program() {

        String[] optionsList = {
                "Alta de producto",
                "Modificación de producto",
                "Baja de producto",
                "Lista de productos",
                "Salir",

        };
        String menuMessage = "Elija una opción: ";
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
