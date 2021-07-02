package application;

import appinterfaces.Colors;
import appinterfaces.Options;
import appinterfaces.ResultsProgram;
import application.models.AlcoholicBeverage;
import application.models.Dairy;
import application.models.Product;
import application.models.Stiff;
import backend.Index;
import org.json.simple.JSONArray;

import java.util.Scanner;
import java.util.Vector;

public final class CmdEcommerce implements Options {

    // Private attributes
    private final Vector<Product> productsList;
    private final Index index;
    int optionSelection;

    // Private methods
    private void validateInput(int optionsQuantity, String message) {
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.print(message);
            this.optionSelection = scanner.nextInt();
        } while (this.optionSelection > optionsQuantity || this.optionSelection < 0);
    }

    private boolean optionsMenu(String[] optionsList, String message) {

        for (int i = 0; i < optionsList.length; i++) {
            System.out.println(Colors.ANSI_DEFAULT + (i + 1) + " - " + optionsList[i]);
        }

        this.validateInput(optionsList.length, message);

        return (optionSelection != 0 && optionSelection != optionsList.length);
    }

    // Constructors
    public CmdEcommerce() {
        index = new Index();
        this.productsList = new Vector<>();
    }

    // Public methods
    public int mainLoopProgram() {
        String[] optionsList = {
                "Alta de producto",
                "Modificación de producto",
                "Baja de producto",
                "Lista de productos",
                "Salir",

        };
        String menuMessage = "Elija una opción: ";
        int programResult = -1;

        //  Connecting to database.
        {
            int connectionResult = this.index.connect();

            System.out.println(ResultsProgram.outputMessages[connectionResult]);
        }

        /*      PROGRAM LOOP        */
        while (this.optionsMenu(optionsList, menuMessage)) {
            switch (this.optionSelection) {
                case 1: {
                    programResult = this.createOption();
                    break;
                }
                case 2: {
                    programResult = this.updateOption();
                    break;
                }
                case 3: {
                    programResult = this.deleteOption();
                    break;
                }
                case 4: {
                    programResult = this.listingOption();
                    break;
                }
            }
            System.out.println(ResultsProgram.outputMessages[programResult]);
        }

        System.out.println(ResultsProgram.outputMessages[ResultsProgram.PROGRAM_FINISHED]);
        return programResult;
    }

    @Override
    public int createOption() {
        /*      DECLARATIONS        */
        System.out.println(Colors.ANSI_BLUE + "_______________________");
        System.out.println(Colors.ANSI_BLUE + " Registro de productos ");
        System.out.println(Colors.ANSI_BLUE + "_______________________");

        String[] optionsList = {
                "Bebidas alcohólicas",
                "Lácteos",
                "Embutidos/Fiambres"
        };

        String optionMessage = "Que tipo de producto desea registrar? (0-salir): ";
        Scanner scanner = new Scanner(System.in);
        Product newProductInstance = new Product();
        String productName, productDescription;
        float productPrice;
        int productStock;

        this.optionsMenu(optionsList, optionMessage);

        if (this.optionSelection <= 0) {
            return 1;
        }

        // Asking for data
        System.out.print(Colors.ANSI_DEFAULT + "Nombre: ");
        productName = scanner.next();
        System.out.print(Colors.ANSI_DEFAULT + "Precio: ");
        productPrice = scanner.nextFloat();
        System.out.print(Colors.ANSI_DEFAULT + "Cant. en Stock: ");
        productStock = scanner.nextInt();
        System.out.print(Colors.ANSI_DEFAULT + "Description: ");
        productDescription = scanner.next();

        switch (this.optionSelection) {
            case 1: {
                float beverageLiter;
                int beveragePercentage;

                System.out.print(Colors.ANSI_DEFAULT + "Litros: ");
                beverageLiter = scanner.nextFloat();
                System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de alcohol: ");
                beveragePercentage = scanner.nextInt();

                newProductInstance = new AlcoholicBeverage(
                        this.optionSelection, productName, productDescription, productPrice, productStock,
                        beverageLiter, beveragePercentage
                );
                break;
            }
            case 2: {
                int dairyFatPercentage;
                String dairyDateExpiry, dairySingleVitamin;
                Vector<String> dairyVitamins = new Vector<>();

                System.out.print(Colors.ANSI_DEFAULT + "Ingrese las vitaminas: ");
                for (int i = 0; i < 3; i++) {
                    dairySingleVitamin = scanner.next();
                    System.out.print(" ");
                    dairyVitamins.add(dairySingleVitamin);
                }
                System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de grasa: ");
                dairyFatPercentage = scanner.nextInt();
                do {
                    System.out.print(Colors.ANSI_DEFAULT + "Fecha de caducidad: ");
                    dairyDateExpiry = scanner.next();
                } while (!Dairy.controlDate(dairyDateExpiry));

                newProductInstance = new Dairy(
                        this.optionSelection, productName, productDescription, productPrice, productStock,
                        dairyFatPercentage, dairyDateExpiry, dairyVitamins
                );
                break;
            }
            case 3: {
                String stiffDateExpiry;
                int stiffFatPercentage;

                System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de grasa: ");
                stiffFatPercentage = scanner.nextInt();

                do {
                    System.out.print(Colors.ANSI_DEFAULT + "Fecha de caducidad: ");
                    stiffDateExpiry = scanner.next();
                } while (!Stiff.controlDate(stiffDateExpiry));

                newProductInstance = new Stiff(
                        this.optionSelection, productName, productDescription, productPrice, productStock,
                        stiffDateExpiry, stiffFatPercentage
                );
                break;
            }
        }
        // Adding product on the list
        this.index.POST(newProductInstance);
        return ResultsProgram.SUCCESS;
    }

    @Override
    public int updateOption() {
        this.chargingVector();
        String[] optionsList = new String[this.productsList.size()];
        String productName, productDescription;
        float productPrice;
        int productStock;
        Product newProductInstance;

        if (optionsList.length == 0) {
            return ResultsProgram.EMPTY_LIST;
        } else {
            String optionMessage = "Que producto desea modificar? (0-salir): ";
            Scanner scanner = new Scanner(System.in);

            for (int i = 0; i < this.productsList.size(); i++) {
                optionsList[i] = this.productsList.elementAt(i).getName();
            }

            this.optionsMenu(optionsList, optionMessage);

            // Harcoded to solve index issue.
            if (this.optionSelection <= 0) {
                return ResultsProgram.CANCELED;
            }
            /*Request of data to modify*/
            System.out.print(Colors.ANSI_DEFAULT + "Name: ");
            productName = scanner.next();
            System.out.print(Colors.ANSI_DEFAULT + "Precio: ");
            productPrice = scanner.nextFloat();
            System.out.print(Colors.ANSI_DEFAULT + "Cant. en Stock: ");
            productStock = scanner.nextInt();
            System.out.print(Colors.ANSI_DEFAULT + "Description: ");
            productDescription = scanner.next();

            switch (this.productsList.elementAt(optionSelection - 1).getCategory()) {
                case 1: {

                    float beverageLiter;
                    int beveragePercentage;

                    System.out.print(Colors.ANSI_DEFAULT + "Litros: ");
                    beverageLiter = scanner.nextFloat();
                    System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de alcohol: ");
                    beveragePercentage = scanner.nextInt();

                    newProductInstance = new AlcoholicBeverage(
                            optionSelection, productName, productDescription, productPrice, productStock,
                            beverageLiter, beveragePercentage
                    );

                    // Updating product
                    String idProd = "" + this.productsList.elementAt(optionSelection - 1).getId();
                    index.PUT(idProd, AlcoholicBeverage.toJson((AlcoholicBeverage) newProductInstance));
                    break;
                }
                case 2: {

                    int dairyFatPercentage;
                    String dairyDateExpiry, dairySingleVitamin;
                    Vector<String> dairyVitamins = new Vector<>();

                    System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de grasa: ");
                    dairyFatPercentage = scanner.nextInt();
                    System.out.print(Colors.ANSI_DEFAULT + "Ingrese las vitaminas: ");

                    for (int i = 0; i < 3; i++) {
                        System.out.print(" ");
                        dairySingleVitamin = scanner.next();
                        dairyVitamins.add(dairySingleVitamin);
                    }
                    do {
                        System.out.print(Colors.ANSI_DEFAULT + "Fecha de caducidad: ");
                        dairyDateExpiry = scanner.next();
                    } while (!Dairy.controlDate(dairyDateExpiry));

                    newProductInstance = new Dairy(
                            optionSelection, productName, productDescription, productPrice, productStock,
                            dairyFatPercentage, dairyDateExpiry, dairyVitamins
                    );

                    // Updating product
                    String idProd = "" + this.productsList.elementAt(optionSelection - 1).getId();
                    index.PUT(idProd,Dairy.toJson((Dairy) newProductInstance));
                    break;
                }
                case 3: {
                    int stiffFatPercentage;
                    String stiffDateExpiry;


                    System.out.print(Colors.ANSI_DEFAULT + "Porcentaje de grasa: ");
                    stiffFatPercentage = scanner.nextInt();
                    do {
                        System.out.print(Colors.ANSI_DEFAULT + "Fecha de caducidad: ");
                        stiffDateExpiry = scanner.next();
                    } while (!Stiff.controlDate(stiffDateExpiry));

                    newProductInstance = new Stiff(
                            optionSelection, productName, productDescription, productPrice, productStock,
                            stiffDateExpiry, stiffFatPercentage
                    );

                    // Updating product
                    String idProd = "" + this.productsList.elementAt(optionSelection - 1).getId();
                    index.PUT(idProd,Stiff.toJson((Stiff) newProductInstance));
                    break;
                }
            }
        }
        return ResultsProgram.SUCCESS;
    }

    @Override
    public int deleteOption() {
        this.chargingVector();

        String[] optionsList = new String[this.productsList.size()];

        if (optionsList.length == 0) {
            return ResultsProgram.EMPTY_LIST;
        } else {
            String optionMessage = "Que producto desea eliminar? (0-salir): ";

            for (int i = 0; i < this.productsList.size(); i++) {
                optionsList[i] = this.productsList.elementAt(i).getName();
            }

            this.optionsMenu(optionsList, optionMessage);

            // Harcoded to solve index issue.
            if (this.optionSelection <= 0) {
                return ResultsProgram.CANCELED;
            }
        }
        // Harcoded to solve index issue.
        if (this.optionSelection <= 0) {
            return ResultsProgram.CANCELED;
        }
        // Deleting product
        String idProd = "" + this.productsList.elementAt(optionSelection - 1).getId();
        index.DELETE(idProd);

        return ResultsProgram.SUCCESS;
    }

    @Override
    public int listingOption() {
        int programResult;
        this.chargingVector();

        if (this.productsList.isEmpty()) {
            programResult = ResultsProgram.EMPTY_LIST;
        } else {
            programResult = ResultsProgram.SUCCESS;
        }

        System.out.println(Colors.ANSI_BLUE + "Lista de productos");
        for (int i = 0; i < this.productsList.size(); i++) {
            System.out.println(this.productsList.elementAt(i).toString());
        }

        return programResult;
    }

    public int chargingVector() {
        String[] productsCategories = {
                "Bebidas Alcoholicas",
                "Lacteos",
                "Embutidos/Fiambres",
                "Salir"
        };

        this.optionsMenu(productsCategories, "Elija la categoria del producto: ");
        this.productsList.clear();
        switch (this.optionSelection) {
            case 1: {
                this.index.setCollectionName("alcoholic");
                JSONArray productList = this.index.GET();
                this.productsList.addAll(Loader.loadAlcoholicBeverage(productList));
                productList.clear();
                break;
            }
            case 2: {
                this.index.setCollectionName("dairy");
                JSONArray productList = this.index.GET();
                this.productsList.addAll(Loader.loadDairy(productList));
                productList.clear();
                break;
            }
            case 3: {
                this.index.setCollectionName("stiff");
                JSONArray productList = this.index.GET();
                this.productsList.addAll(Loader.loadStiff(productList));
                productList.clear();
                break;
            }
            case 4: {
                return ResultsProgram.CANCELED;
            }
        }

        return ResultsProgram.SUCCESS;
    }
}
