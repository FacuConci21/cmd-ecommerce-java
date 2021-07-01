package productstests;

import application.models.Dairy;
import backend.Index;
import application.models.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Vector;

public class DairyProductsTest {
    public static void PostDairyProduct(Index index, String color){
        {


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

            /**
             * Basta con comentar esta linea para evitar que se graben estos datos
             * y poder ver la respuesta del backend con una lista vacia.
             */
            System.out.println(color + "Product posted: " + index.POST(dProduct1));
            System.out.println(color + "Product posted: " + index.POST(dProduct2));
            System.out.println(color + "Product posted: " + index.POST(dProduct3));




        }
    }

    public static void GetDairyProducts(Index index, String color){

        /**
         * Metodo creado para consultar los datos que se almacenarán en Dairy.json.
         * Si esta vacio se muestra [], la coleccion vacia.
         * Y si se traen los datos correctamente se muestran los productos listados.
         */

        JSONArray dairyCollection = index.GET();
        if (dairyCollection.size() == 0) {
            System.out.println(color + dairyCollection);
        } else {
            for (int i = 0; i < dairyCollection.size(); i++) {
                JSONObject dairy = (JSONObject) dairyCollection.get(i);

                System.out.println(color + "[");
                for (Object key : dairy.keySet()) {
                    System.out.println(color + "\t" + key + ": " + dairy.get(key));
                }
                System.out.println(color + "]");
            }
        }
    }

    public static void GetDairyProductsById(Index index, String id, String color, String errorColor){
        JSONObject dairyObjectCollection = index.GET(id);
        if (dairyObjectCollection == null){
            System.out.println(errorColor + "No se encontro el producto.");
        } else {
            System.out.println(color + dairyObjectCollection);
        }
        System.out.println(color + dairyObjectCollection);
    }

    public static void PutDairyProducts(Index index, String id, String color, String errorColor) {
        JSONObject dProduct = index.GET(id);

        if (dProduct == null){
            System.out.println(errorColor + "No se encontro el producto que desea modificar.");
        } else {
            dProduct.replace("_id", "4");
            System.out.println(color + index.PUT(id, dProduct));
        }
    }

    public static void DeleteDairyProducts(Index index, String id, String errorColor ,String color){
        JSONArray dairyCollection = index.GET();

        int dairyDelete = index.DELETE(id);
        if(dairyDelete == 0 ){
            System.out.println(color + "Eliminado Correctamente");
            /** Obteniendo colección de nuevo para ver si se eliminó */
            if (dairyCollection.size() == 0) {
                System.out.println(dairyCollection);
            } else {
                for (int i = 0; i < dairyCollection.size(); i++) {
                    JSONObject dairy = (JSONObject) dairyCollection.get(i);

                    System.out.println(color + "[");
                    for (Object key : dairy.keySet()) {
                        System.out.println(color + "\t" + key + ": " + dairy.get(key));
                    }
                    System.out.println(color + "]");
                }
            }

        } else {
            System.out.println(errorColor + "No se pudo eliminar");
        }
    }

}
