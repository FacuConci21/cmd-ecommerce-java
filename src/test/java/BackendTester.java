import application.models.AlcoholicBeverage;
import application.models.Dairy;
import application.models.Stiff;
import backend.Index;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import productstests.DairyProductsTest;

import java.util.Vector;

public class BackendTester {
    public static void main(String[] args) {
        Index index = new Index();

        /**
         * Antes de cualquier operación se comprueba la conexión con el servidor.
         * Para este caso en particular el servidor se simula con un directorio, si el path
         * del mismo es valido y existe entonces se toma por una conexión correcta.
         *
         * De no conectarse, dirigirse al archivo de clase 'RoutesAndPaths', en el paquete 'iapplication'
         * y cambiar la ruta absoluta de la constante PROJECT_DIR por la ruta donde tengas guardado el proyecto,
         * incluyendo "src/main/".
         */
        if (index.connect() == 5) {
            System.out.println("    --  connected   --");

            {
                /**System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct););
                 System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct2););
                 System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct3););*/
            }

            System.out.println("GET opperation:");
            index.setCollectionName("dairy");
            DairyProductsTest.GetDairyProducts(index);


            /**Prueba de GET by INDEX*/
            System.out.println("GET BY ID Operation:");
            DairyProductsTest.GetDairyProductsById(index, "2");

            /**Prueba de DELETE*/

            System.out.println("DELETE Operation:");
            DairyProductsTest.DeleteDairyProducts(index, "3");


           /* System.out.println("POST OPERATION ALCOHOL BEVERAGE:");
            {
                AlcoholicBeverage aProduct1 = new AlcoholicBeverage(1, 2, "Vodka Saborizado", "Vodka Saborizado de Maracuyá", 1500.0f, 2,
                        1.0f, 50);
                System.out.println(index.POST(aProduct1));
            }*/
        } else {
            System.out.println("    --  Not connected   --");
        }
    } // main()
} // BackendTester