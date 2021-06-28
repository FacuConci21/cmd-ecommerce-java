import appinterfaces.Colors;
import application.models.Dairy;
import backend.Index;
import org.json.simple.JSONObject;
import productstests.AlcoholBeverageProductsTest;
import productstests.DairyProductsTest;
import productstests.StiffProductsTest;

import java.util.Vector;

public class BackendTester implements Colors{
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
            System.out.println(ANSI_BLUE + "    --  connected   --");

            System.out.println(ANSI_GREEN + "POST opperation:");
            /**AlcoholBeverageProductsTest.PostAlcoholBeverageProducts(index, ANSI_DEFAULT);
            DairyProductsTest.PostDairyProduct(index, ANSI_DEFAULT);*/
            StiffProductsTest.PostTest(index, ANSI_DEFAULT);



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
             DairyProductsTest.GetDairyProducts(index, ANSI_DEFAULT);
             */
            StiffProductsTest.GetTest(index, ANSI_DEFAULT);

            /**Prueba de GET by INDEX*/

            System.out.println(ANSI_GREEN + "GET BY ID Operation:");

            /**DairyProductsTest.GetDairyProductsById(index, "2", ANSI_DEFAULT, ANSI_RED);*/
            /**AlcoholBeverageProductsTest.GetAlcoholBeverageProductsById(index, "2", ANSI_DEFAULT, ANSI_RED);*/
            StiffProductsTest.GetByIdTest(index, "323423", ANSI_DEFAULT, ANSI_RED);


            System.out.println(ANSI_GREEN + "PUT opperation: ");

            /**AlcoholBeverageProductsTest.PutTest(index, "8", ANSI_YELLOW, ANSI_RED);;*/
            StiffProductsTest.PutTest(index, "5", ANSI_YELLOW, ANSI_RED);
            /**DairyProductsTest.PutDairyProducts(index,"3", ANSI_YELLOW, ANSI_RED);*/


            /**Prueba de DELETE*/

            System.out.println(ANSI_GREEN + "DELETE Operation:");

            /**
             StiffProductsTest.DeleteTest(index, "2", ANSI_YELLOW);
             DairyProductsTest.DeleteDairyProducts(index, "1", ANSI_RED, ANSI_DEFAULT);
             AlcoholBeverageProductsTest.DeleteAlcoholBeverageProducts(index, "1", ANSI_RED ,ANSI_DEFAULT);
             */

        } else {
            System.out.println(ANSI_RED + "    --  Not connected   --");
        }
    } // main()
} // BackendTester