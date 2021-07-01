import appinterfaces.Colors;
import appinterfaces.ResultsProgram;
import application.models.Dairy;
import backend.Index;
import org.json.simple.JSONObject;
import productstests.AlcoholBeverageProductsTest;
import productstests.DairyProductsTest;
import productstests.StiffProductsTest;

import java.util.Vector;

public class BackendTester implements Colors {
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

                /**
                 * Basta con comentar estas lineas para evitar que se graben estos datos
                 * y poder ver la respuesta del backend con una lista vacia.
                 */

                System.out.println(index.POST(dProduct));
                /**System.out.println(index.POST(dProduct2));
                 System.out.println(index.POST(dProduct3));*/
            }


            if (index.connect() == ResultsProgram.CONNECTION_SUCCESS) {
                System.out.println(ResultsProgram.outputMessages[ResultsProgram.CONNECTION_SUCCESS]);

                System.out.println(ANSI_GREEN + "POST opperation:");
                /**AlcoholBeverageProductsTest.PostAlcoholBeverageProducts(index, ANSI_DEFAULT);
                 productstests.DairyProductsTest.PostDairyProduct(index, ANSI_DEFAULT);*/
                StiffProductsTest.PostStiffProducts(index, ANSI_DEFAULT);


                System.out.println(ANSI_GREEN + "GET opperation: ");
                /**Para probar las metodos de los diferentes productos solo basta con cambiar el nombre de la coleccion
                 * en la línea siguiente
                 * NAMES:
                 * dairy (for dairys products)
                 * alcoholic (for alcoholic beverages products)
                 * stiff (for stiff products)
                 * */

                index.setCollectionName("stiff");


                /**
                 AlcoholBeverageProductsTest.GetAlcoholBeverageProducts(index, ANSI_DEFAULT);
                 productstests.DairyProductsTest.GetDairyProducts(index, ANSI_DEFAULT);
                 */
                StiffProductsTest.GetStiffProducts(index, ANSI_DEFAULT);

                /**Prueba de GET by INDEX*/

                System.out.println(ANSI_GREEN + "GET BY ID Operation:");

                /**productstests.DairyProductsTest.GetDairyProductsById(index, "2", ANSI_DEFAULT, ANSI_RED);*/
                /**AlcoholBeverageProductsTest.GetAlcoholBeverageProductsById(index, "2", ANSI_DEFAULT, ANSI_RED);*/
                //StiffProductsTest.GetByIdTest(index, "323423", ANSI_DEFAULT, ANSI_RED);


                //System.out.println(ANSI_GREEN + "PUT opperation: ");

                /**AlcoholBeverageProductsTest.PutTest(index, "8", ANSI_YELLOW, ANSI_RED);;*/
                //StiffProductsTest.PutTest(index, "5", ANSI_YELLOW, ANSI_RED);
                /**productstests.DairyProductsTest.PutDairyProducts(index,"3", ANSI_YELLOW, ANSI_RED);*/


                /**Prueba de DELETE*/

                //System.out.println(ANSI_GREEN + "DELETE Operation:");

                /**
                 StiffProductsTest.DeleteTest(index, "2", ANSI_YELLOW);
                 productstests.DairyProductsTest.DeleteDairyProducts(index, "1", ANSI_RED, ANSI_DEFAULT);
                 AlcoholBeverageProductsTest.DeleteAlcoholBeverageProducts(index, "1", ANSI_RED ,ANSI_DEFAULT);
                 */

            } else {
                System.out.println(ResultsProgram.outputMessages[ResultsProgram.CONNECTION_FAILED]);
            }
        } // main()
    }
}// BackendTester