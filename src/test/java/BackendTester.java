import application.models.AlcoholicBeverage;
import application.models.Dairy;
import appinterfaces.Colors;
import backend.Index;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import productstests.AlcoholBeverageProductsTest;
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
                AlcoholicBeverage aProduct1 = new AlcoholicBeverage(5, 2, "Dada", "Dada 7", 250.0f, 20,
                        0.5f, 25);
                int result = index.POST(aProduct1);
                if (result != 0){
                    System.out.println(ANSI_RED + "No se pudo agregar el producto");
                } else {
                    System.out.println(ANSI_GREEN + "Producto agregado correctamente");
                }

            }
           /* {
                *//**
                 * Aca genero objetos para guardar en la BD, en la coleccion de datos
                 * de productos Dairy, lo encerre dentro de {} para que cuando termine de hacer POST
                 * se destruyan los objetos que ya no se van a usar para que no ocupen memoria.
                 *//*
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


                *//**System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct););
                 System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct2););
                 System.out.println(DairyProductsTest.PostDairyProduct(index, dProduct3););*//*
            }*/

            System.out.println(ANSI_GREEN + "GET opperation :");
            /**Para probar las metodos de los diferentes productos solo basta con cambiar el nombre de la coleccion
             * en la línea siguiente
             * NAMES:
             * dairy (for dairys products)
             * alcoholic (for alcoholic beverages proucts)
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
    }
}