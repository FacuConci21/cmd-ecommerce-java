import application.models.AlcoholicBeverage;
import application.models.Dairy;
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

            System.out.println("POST opperation:");

            {
                /**
                 * Aca genero objetos para guardar en la BD, en la coleccion de datos
                 * de productos Dairy, lo encerre dentro de {} para que cuando termine de hacer POST
                 * se destruyan los objetos que ya no se van a usar para que no ocupen memoria.
                 */
                Vector<String> vitamins = new Vector<>();

                vitamins.add("a2");
                vitamins.add("h");
                vitamins.add("bc");

                Dairy dProduct = new Dairy(
                        1, 3, "Yogurt en Sachet", "Y nos da la leche!", 100.0f, 12,
                        30, "25-7-2023", vitamins
                );
                Dairy dProduct2 = new Dairy(
                        2, 3, "Queso", "Y el dulce de leche!", 100.0f, 12,
                        30, "25-7-2023", vitamins
                );
                Dairy dProduct3 = new Dairy(
                        3, 3, "Dulce de Leche", "Y la manteca que se come con el pan!", 100.0f, 12,
                        30, "25-7-2023", vitamins
                );


                /**System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct););
                 System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct2););
                 System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct3););*/
            }

            System.out.println("GET opperation:");
            index.setCollectionName("alcoholic");
            /**DairyProductsTest.GetDairyProducts(index);*/
            JSONArray alcholCollection = index.GET();
            if (alcholCollection.size() == 0) {
                System.out.println(alcholCollection);
            } else {
                for (int i = 0; i < alcholCollection.size(); i++) {
                    JSONObject alcohol = (JSONObject) alcholCollection.get(i);

                    System.out.println("[");
                    for (Object key : alcohol.keySet()) {
                        System.out.println("\t" + key + ": " + alcohol.get(key));
                    }
                    System.out.println("]");
                }
            }

            /**Prueba de GET by INDEX*/
            System.out.println("GET BY ID Operation:");


            /**DairyProductsTest.GetDairyProductsById(index, "2");*/

            /**Prueba de DELETE*/

            System.out.println("DELETE Operation:");
            /**DairyProductsTest.DeleteDairyProducts(index, "3");*/


           /* System.out.println("POST OPERATION ALCOHOL BEVERAGE:");
            {
                AlcoholicBeverage aProduct1 = new AlcoholicBeverage(1, 2, "Vodka Saborizado", "Vodka Saborizado de Maracuyá", 1500.0f, 2,
                        1.0f, 50);
                System.out.println(index.POST(aProduct1));
            }*/




        } else {
            System.out.println("    --  Not connected   --");
        }
    }
}