package application;

import backend.Index;
import iapplication.Colors;
import iapplication.Options;
import java.util.Scanner;
import java.util.Vector;

public final class CmdEcommerce implements Options{

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
            System.out.println( Colors.ANSI_DEFAULT + (i + 1) + " - " + optionsList[i]);
        }

        this.validateInput(optionsList.length, message);

        return (optionSelection != 0 && optionSelection != optionsList.length);
    }

    // Constructors
    public CmdEcommerce() {
        this.productsList = new Vector<>();
    }

    // Public methods
    public int main_loop_program() {

        String[] outputMessages = {
                Colors.ANSI_BLUE + "La operacion de llevo a cabo con exito!",
                Colors.ANSI_YELLOW + "La operacion fue cancelada.",
                Colors.ANSI_YELLOW + "La lista de productos esta vacia.",
                Colors.ANSI_RED + "Se produjo un error, reinicie el programa.",
                Colors.ANSI_RED + "No se pudo conectar a la base de datos.",
                Colors.ANSI_GREEN + "Conectado a la base de datos!",
                Colors.ANSI_GREEN + "Agradecemos su visita, Adios!"
        };
        String[] optionsList = {
                "Alta de producto",
                "Modificación de producto",
                "Baja de producto",
                "Lista de productos",
                "Salir",

        };
        String menuMessage = "Elija una opción: ";
        int programResult = -1;
        Index index = new Index();

        {
            programResult = index.connect();
            System.out.println(outputMessages[programResult]);
            index.POST(new Dairy());
        }
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
            System.out.println(outputMessages[programResult]);
        }

        System.out.println(outputMessages[4]); // '4' is for a goodbye message code.
        return programResult;
    }

    @Override
    public int createOption() {
        /*      DECLARATIONS        */
        System.out.println(Colors.ANSI_BLUE + "_______________________");
        System.out.println(Colors.ANSI_BLUE + " Registro de productos ");
        System.out.println(Colors.ANSI_BLUE + "_______________________");

        String[] optionsList = {
                "Bebida alcohólica",
                "Lácteo",
                "Fiambre"
        };
        String optionMessage = "Que tipo de producto desea registrar? (0-salir): ";
        Scanner scanner = new Scanner(System.in);
        Product newProductInstance;
        String productName, productDescription;
        float productPrice;
        int productStock, productId;

        this.optionsMenu(optionsList, optionMessage);

        // Harcoded to solve cancelation issue. '1' is for cancelation code.
        if (this.optionSelection <= 0) { return 1; }

        productId = (productsList.isEmpty()) ? 1 : productsList.size() + 1;
        // Asking for data
        System.out.print(Colors.ANSI_DEFAULT + "Nombre: "); productName = scanner.next();
        System.out.print(Colors.ANSI_DEFAULT + "Precio: "); productPrice = scanner.nextFloat();
        System.out.print(Colors.ANSI_DEFAULT + "Cant. en Stock: "); productStock = scanner.nextInt();
        System.out.print(Colors.ANSI_DEFAULT + "Description: "); productDescription = scanner.next();

        switch (this.optionSelection) {
            case 1:
            {
                float beverageLiter;
                int beveragePercentage;

                System.out.print(Colors.ANSI_DEFAULT + "Litros: "); beverageLiter = scanner.nextFloat();
                System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de alcohol: "); beveragePercentage = scanner.nextInt();

                newProductInstance = new AlcoholicBeverage(
                        productId, this.optionSelection,productName, productDescription, productPrice, productStock,
                        beverageLiter, beveragePercentage
                );

                // Adding product on the list
                this.productsList.add(newProductInstance);
                break;
            }
            case 2:
            {
                int dairyFatPercentage;
                String dairyDateExpiry, dairySingleVitamin;
                Vector<String> dairyVitamins = new Vector<>();

                System.out.print(Colors.ANSI_DEFAULT+"Ingrese las vitaminas: ");
                for (int i = 0; i < 3; i++) {
                    dairySingleVitamin = scanner.next();System.out.print(" ");
                    dairyVitamins.add(dairySingleVitamin);
                }
                System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de grasa: "); dairyFatPercentage = scanner.nextInt();
                do{
                    System.out.print(Colors.ANSI_DEFAULT + "Fecha de caducidad: "); dairyDateExpiry = scanner.next();
                } while( !Dairy.controlDate(dairyDateExpiry) );

                newProductInstance = new Dairy(
                        productId, this.optionSelection,productName, productDescription, productPrice, productStock,
                        dairyFatPercentage, dairyDateExpiry, dairyVitamins
                );

                // Adding product on the list
                this.productsList.add(newProductInstance);
                break;
            }
            case 3:
            {
                String stiffDateExpiry;
                int stiffFatPercentage;

                System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de grasa: "); stiffFatPercentage = scanner.nextInt();

                do{
                    System.out.print(Colors.ANSI_DEFAULT + "Fecha de caducidad: "); stiffDateExpiry = scanner.next();
                } while( !Stiff.controlDate(stiffDateExpiry) );

                newProductInstance = new Stiff(
                        productId, this.optionSelection,productName, productDescription, productPrice, productStock,
                        stiffDateExpiry, stiffFatPercentage
                );

                // Adding product on the list
                this.productsList.add(newProductInstance);
                break;
            }
        }

        System.out.println(Colors.ANSI_BLUE + "\tProducto: '" + productName + "'; '" +
                productDescription + "',\n\tfue dado de alta exitosamente." );
        return 0;
    }

    @Override
    public int updateOption() {
        /*      DECLARATIONS        */
        String[] optionsList = new String[this.productsList.size()];
        String productName, productDescription;
        float productPrice;
        int productStock;
        Product newProductInstance;

        if (optionsList.length == 0){
            System.out.println(Colors.ANSI_RED + "No hay productos para modificar." );
            return 2; // Empty list code
        } else{
            String optionMessage = "Que producto desea modificar? (0-salir): ";
            Scanner scanner = new Scanner(System.in);

            for(int i = 0; i < this.productsList.size(); i++){
                optionsList[i] = this.productsList.elementAt(i).getName();
            }

            this.optionsMenu(optionsList, optionMessage);

            // Harcoded to solve index issue.
            if (this.optionSelection <= 0) { return 1; }

            /*Request of data to modify*/

            System.out.print(Colors.ANSI_DEFAULT + "Name: "); productName = scanner.next();
            System.out.print(Colors.ANSI_DEFAULT + "Precio: "); productPrice = scanner.nextFloat();
            System.out.print(Colors.ANSI_DEFAULT + "Cant. en Stock: "); productStock = scanner.nextInt();
            System.out.print(Colors.ANSI_DEFAULT + "Description: "); productDescription = scanner.next();


            switch(this.productsList.elementAt(optionSelection - 1).getCategory()){
                case 1: {

                    float beverageLiter;
                    int beveragePercentage;

                    System.out.print(Colors.ANSI_DEFAULT + "Litros: "); beverageLiter = scanner.nextFloat();
                    System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de alcohol: "); beveragePercentage = scanner.nextInt();

                    newProductInstance = new AlcoholicBeverage(this.productsList.elementAt(
                            optionSelection - 1).getId(), optionSelection, productName,
                            productDescription, productPrice, productStock, beverageLiter, beveragePercentage
                    );

                    // Updating product
                    this.productsList.setElementAt(newProductInstance, optionSelection - 1);
                    break;
                }
                case 2: {

                    int dairyFatPercentage;
                    String dairyDateExpiry, dairySingleVitamin;
                    Vector<String> dairyVitamins = new Vector<>();

                    System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de grasa: "); dairyFatPercentage = scanner.nextInt();
                    System.out.print(Colors.ANSI_DEFAULT + "Ingrese las vitaminas: ");

                    for (int i = 0; i < 3; i++) {
                        System.out.print(" "); dairySingleVitamin = scanner.next();
                        dairyVitamins.add(dairySingleVitamin);
                    }
                    do{
                        System.out.print(Colors.ANSI_DEFAULT + "Fecha de caducidad: "); dairyDateExpiry = scanner.next();
                    } while( !Dairy.controlDate(dairyDateExpiry) );

                    newProductInstance = new Dairy(
                            this.productsList.elementAt(optionSelection - 1).getId(),
                            optionSelection,productName, productDescription, productPrice, productStock,
                            dairyFatPercentage,dairyDateExpiry, dairyVitamins
                    );

                    // Updating product
                    this.productsList.setElementAt(newProductInstance, optionSelection - 1);
                    break;
                }
                case 3: {
                    int stiffFatPercentage;
                    String stiffDateExpiry;

                    System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de grasa: "); stiffFatPercentage = scanner.nextInt();
                    do{
                        System.out.print(Colors.ANSI_DEFAULT + "Fecha de caducidad: "); stiffDateExpiry = scanner.next();
                    } while( !Stiff.controlDate(stiffDateExpiry) );

                    newProductInstance = new Stiff(this.productsList.elementAt(optionSelection - 1).getId(),
                            optionSelection, productName, productDescription, productPrice, productStock, stiffDateExpiry,
                            stiffFatPercentage);

                    // Updating product
                    this.productsList.setElementAt(newProductInstance, optionSelection - 1);
                    break;
                }
            }

            System.out.println(Colors.ANSI_BLUE + "Producto: " + productName + " fue modificado correctamente." );
        }

        return 0;
    }

    @Override
    public int deleteOption() {
        String[] optionsList = new String[this.productsList.size()];

        if (optionsList.length == 0){
            System.out.println(Colors.ANSI_RED + "No hay productos para eliminar." );
            return 2; // Empty list code
        } else {
            String optionMessage = "Seleccione el producto a eliminar (0-salir): ";

            for (int i = 0; i < this.productsList.size(); i++) {
                optionsList[i] = this.productsList.elementAt(i).getName();
            }

            this.optionsMenu(optionsList, optionMessage);

            // Harcoded to solve index issue.
            if (this.optionSelection <= 0) { return 1; }

            // Deleting product
            this.productsList.removeElementAt(optionSelection -1 );

            System.out.println(Colors.ANSI_BLUE + "Producto con ID " + (optionSelection) +" eliminado correctamente");

        }
        return 0;
    }

    @Override
    public int listingOption() {
        if(this.productsList.size() > 0 ){
            System.out.println(Colors.ANSI_BLUE + "Lista de productos");
            for (int i = 0; i < this.productsList.size(); i++) {
                System.out.println(this.productsList.elementAt(i).toString());
            }
        } else {
            System.out.println(Colors.ANSI_RED + "No hay productos cargados! Por favor, dé de alta uno como mínimo");
            return 2; // Empty list code
        }

        return 0;
    }
}
