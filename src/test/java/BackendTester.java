import appinterfaces.Colors;
import application.models.Dairy;
import backend.Index;
import org.json.simple.JSONObject;
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
            {
                /**
                 * Aca genero objetos para guardar en la BD, en la coleccion de datos
                 * de productos Dairy, lo encerre dentro de {} para que cuando termine de hacer POST
                 * se destruyan los objetos que ya no se van a usar para que no ocupen memoria.
                 */
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

                //StiffProductsTest.PostTest(index, ANSI_BLUE);

                /**System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct););
                 System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct2););
                 System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct3););*/
            }

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
            StiffProductsTest.GetTest(index, ANSI_YELLOW);


            DairyProductsTest.GetDairyProducts(index, ANSI_DEFAULT);
            AlcoholBeverageProductsTest.GetAlcoholBeverageProducts(index, ANSI_DEFAULT);*/

            /**Prueba de GET by INDEX*/

            System.out.println(ANSI_GREEN + "GET BY ID Operation:");

            StiffProductsTest.GetByIdTest(index, "3252354", ANSI_DEFAULT);
            /**

            DairyProductsTest.GetDairyProductsById(index, "3", ANSI_DEFAULT);
            AlcoholBeverageProductsTest.GetAlcoholBeverageProductsById(index, "1", ANSI_DEFAULT);*/


             /**    Prueba para PUT     */

            System.out.println(ANSI_GREEN + "PUT opperation: ");

            StiffProductsTest.PutTest(index, "3", ANSI_YELLOW);
            /**
            DairyProductsTest.PutDairyProducts(index,"3", ANSI_DEFAULT);*/

            /**Prueba de DELETE*/

            System.out.println(ANSI_GREEN + "DELETE Operation:");

            /**
            StiffProductsTest.DeleteTest(index, "2", ANSI_YELLOW);
            StiffProductsTest.GetByIdTest(index, "2", ANSI_YELLOW);

             DairyProductsTest.DeleteDairyProducts(index, "1", ANSI_RED, ANSI_DEFAULT);
             AlcoholBeverageProductsTest.DeleteAlcoholBeverageProducts(index, "1", ANSI_RED ,ANSI_DEFAULT);*/

        } else {
            System.out.println(ANSI_RED + "    --  Not connected   --");
        }
    } // main()
} // BackendTester