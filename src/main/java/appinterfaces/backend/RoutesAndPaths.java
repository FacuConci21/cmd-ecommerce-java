package appinterfaces.backend;

public interface RoutesAndPaths {
    /*
     * Si la ruta se trata de un directorio o paquete, finalizarla con '/'.
     * Si se trata de un archivo, finalizarlo con su extension sin '/'.
     * */

    // Paths
    String PROJECT_DIR = "/home/matisantillan11/Documents/Proyects/cmd-ecommerce-java/src/main/";
    String SERVER_URI = PROJECT_DIR + "resources/server/";
    String PRODUCTS_COLLECTION_DIR = "products-cluster/";
    String USERS_COLLECTION_DIR = "users-cluster/";

    // Routes
    String ALCOHOLIC_BEVERAGE_URL = SERVER_URI + PRODUCTS_COLLECTION_DIR + "AlcoholicBeverage.json";
    String DAIRY_URL = SERVER_URI + PRODUCTS_COLLECTION_DIR + "Dairy.json";
    String STIFF_URL = SERVER_URI + PRODUCTS_COLLECTION_DIR + "Stiff.json";
    String CLIENTS_URL = SERVER_URI + USERS_COLLECTION_DIR + "Clients.json";
    String EMPLOYES_URL = SERVER_URI + USERS_COLLECTION_DIR + "Employes.json";
}
