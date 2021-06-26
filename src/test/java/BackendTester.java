import appinterfaces.Colors;
import application.models.Dairy;
import backend.Index;
import org.json.simple.JSONObject;
import productstests.DairyProductsTest;

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
            {
                /**
                Vector<String> vitamins = new Vector<>();

                vitamins.add("a");
                Dairy dProduct1 = new Dairy(
                        1, 3, "a","bb", 100f, 1,
                        1, "25-6-2021", vitamins
                );
                Dairy dProduct2 = new Dairy(
                        2, 3, "b","cc", 100f, 1,
                        1, "25-6-2021", vitamins
                );
                Dairy dProduct3 = new Dairy(
                        3, 3, "c","dd", 100f, 1,
                        1, "25-6-2021", vitamins
                );

                DairyProductsTest.PostDairyProduct(index, dProduct1);
                DairyProductsTest.PostDairyProduct(index, dProduct2);
                DairyProductsTest.PostDairyProduct(index, dProduct3);*/
            }

            System.out.println(ANSI_GREEN + "GET opperation: ");
            /**Para probar las metodos de los diferentes productos solo basta con cambiar el nombre de la coleccion
             * en la línea siguiente
             * NAMES:
             * dairy (for dairys products)
             * alcoholic (for alcoholic beverages products)
             * stiff (for stiff products)
             * */
            index.setCollectionName("dairy");

            DairyProductsTest.GetDairyProducts(index, ANSI_DEFAULT);
            /**AlcoholBeverageProductsTest.GetAlcoholBeverageProducts(index, ANSI_DEFAULT);*/

            /**Prueba de GET by INDEX*/

             System.out.println(ANSI_GREEN + "GET BY ID Operation:");
            DairyProductsTest.GetDairyProductsById(index, "3", ANSI_DEFAULT);
             /**AlcoholBeverageProductsTest.GetAlcoholBeverageProductsById(index, "1", ANSI_DEFAULT);*/


             /**    Prueba para PUT     */

             DairyProductsTest.PutDairyProducts(index,"3", ANSI_DEFAULT);

            /**Prueba de DELETE*/
/**
             System.out.println(ANSI_GREEN + "DELETE Operation:");
             DairyProductsTest.DeleteDairyProducts(index, "1", ANSI_RED, ANSI_DEFAULT);
             AlcoholBeverageProductsTest.DeleteAlcoholBeverageProducts(index, "1", ANSI_RED ,ANSI_DEFAULT);*/

        } else {
            System.out.println(ANSI_RED + "    --  Not connected   --");
        }
    } // main()
} // BackendTester