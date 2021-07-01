package appinterfaces.backend;

public interface RoutesAndPaths {
    /*
     * Si la ruta se trata de un directorio o paquete, finalizarla con '/'.
     * Si se trata de un archivo, finalizarlo con su extension sin '/'.
     * */

    // Paths
<<<<<<< HEAD:src/main/java/iapplication/RoutesAndPaths.java
    String PROJECT_DIR = "C:\\CAPACITACION_PIL\\ProyectoJava\\cmd-ecommerce-java\\src\\main\\";
=======
    String PROJECT_DIR = System.getProperty("user.dir") + "/src/main/";
>>>>>>> 2a41d48e83c76d7aaede97e0b3a59c1074f69ef8:src/main/java/appinterfaces/backend/RoutesAndPaths.java
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
