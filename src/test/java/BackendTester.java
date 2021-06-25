import appinterfaces.Colors;
import backend.Index;
import productstests.DairyProductsTest;

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
                /**System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct););
                 System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct2););
                 System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct3););*/
            }

            System.out.println(ANSI_GREEN + "GET opperation :");
            /**Para probar las metodos de los diferentes productos solo basta con cambiar el nombre de la coleccion
             * en la línea siguiente
             * NAMES:
             * dairy (for dairys products)
             * alcoholic (for alcoholic beverages products)
             * stiff (for stiff products)
             * */
            index.setCollectionName("dairy");

            DairyProductsTest.GetDairyProducts(index,ANSI_DEFAULT);
            /**AlcoholBeverageProductsTest.GetAlcoholBeverageProducts(index, ANSI_DEFAULT);*/

            /**Prueba de GET by INDEX*/
            System.out.println(ANSI_GREEN + "GET BY ID Operation:");
            DairyProductsTest.GetDairyProductsById(index, "2", ANSI_DEFAULT);
            /**AlcoholBeverageProductsTest.GetAlcoholBeverageProductsById(index, "1", ANSI_DEFAULT);*/

            /**Prueba de DELETE*/

            System.out.println(ANSI_GREEN + "DELETE Operation:");
            DairyProductsTest.DeleteDairyProducts(index, "3", ANSI_RED, ANSI_DEFAULT);
            /**AlcoholBeverageProductsTest.DeleteAlcoholBeverageProducts(index, "1", ANSI_RED ,ANSI_DEFAULT);*/

        } else {
            System.out.println(ANSI_RED + "    --  Not connected   --");
        }
    } // main()
} // BackendTester