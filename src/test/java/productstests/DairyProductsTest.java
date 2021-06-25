package productstests;

import application.models.Dairy;
import backend.Index;
import application.models.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Vector;

public class DairyProductsTest {
    public static void PostDairyProduct(Index index, Product newRecord){
        {

            /**
             * Basta con comentar esta linea para evitar que se graben estos datos
             * y poder ver la respuesta del backend con una lista vacia.
             */

            index.POST(newRecord);

        }
    }

    public static void GetDairyProducts(Index index){

        /**
         * Metodo creado para consultar los datos que se almacenarán en Dairy.json.
         * Si esta vacio se muestra [], la coleccion vacia.
         * Y si se traen los datos correctamente se muestran los productos listados.
         */

        JSONArray dairyCollection = index.GET();
        if (dairyCollection.size() == 0) {
            System.out.println(dairyCollection);
        } else {
            for (int i = 0; i < dairyCollection.size(); i++) {
                JSONObject dairy = (JSONObject) dairyCollection.get(i);

                System.out.println("[");
                for (Object key : dairy.keySet()) {
                    System.out.println("\t" + key + ": " + dairy.get(key));
                }
                System.out.println("]");
            }
        }
    }

    public static void GetDairyProductsById(Index index, String id){
        JSONObject dairyObjectCollection = index.GET(id);
        System.out.println(dairyObjectCollection);
    }

    public static void DeleteDairyProducts(Index index, String id){
        JSONArray dairyCollection = index.GET();

        int dairyDelete = index.DELETE(id);
        if(dairyDelete == 0 ){
            System.out.println("Eliminado Correctamente");
            /** Obteniendo colección de nuevo para ver si se eliminó */
            if (dairyCollection.size() == 0) {
                System.out.println(dairyCollection);
            } else {
                for (int i = 0; i < dairyCollection.size(); i++) {
                    JSONObject dairy = (JSONObject) dairyCollection.get(i);

                    System.out.println("[");
                    for (Object key : dairy.keySet()) {
                        System.out.println("\t" + key + ": " + dairy.get(key));
                    }
                    System.out.println("]");
                }
            }

        } else {
            System.out.println("No se pudo eliminar");
        }
    }

}
