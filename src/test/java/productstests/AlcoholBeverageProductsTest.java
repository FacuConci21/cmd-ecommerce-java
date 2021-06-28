package productstests;

import application.models.Product;
import backend.Index;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AlcoholBeverageProductsTest {
    public static void PostAlcoholBeverageProducts(Index index, Product newRecord){
        {
            index.POST(newRecord);
        }
    }

    public static void GetAlcoholBeverageProducts(Index index, String color){

        JSONArray AlcoholCollection = index.GET();

        if (AlcoholCollection.size() == 0) {
            System.out.println( color + AlcoholCollection);
        } else {
            for (int i = 0; i < AlcoholCollection.size(); i++) {
                JSONObject dairy = (JSONObject) AlcoholCollection.get(i);

                System.out.println(color + "[");
                for (Object key : dairy.keySet()) {
                    System.out.println(color + "\t" + key + ": " + dairy.get(key));
                }
                System.out.println(color + "]");
            }
        }
    }

    public static void GetAlcoholBeverageProductsById(Index index, String id, String color){
        JSONObject AlcoholObjectCollection = index.GET(id);
        System.out.println(color + AlcoholObjectCollection);
    }

    public static void PutTest(Index index, String id, String color, String errorColor){
        JSONObject aProduct = index.GET(id);
        if (aProduct == null){
            System.out.println(errorColor + "No se encontro el producto que desea modificar.");
        } else {
            aProduct.replace("name", "Vino Toro");
            System.out.println(color + index.PUT(id, aProduct));
        }

    }
    public static void DeleteAlcoholBeverageProducts(Index index, String id, String errorColor, String color){
        JSONArray AlcoholCollection = index.GET();

        int alcoholDelete = index.DELETE(id);
        if(alcoholDelete == 0 ){
            System.out.println(color + "Eliminado Correctamente");
            /** Obteniendo colección de nuevo para ver si se eliminó */
            if (AlcoholCollection.size() == 0) {
                System.out.println(AlcoholCollection);
            } else {
                for (int i = 0; i < AlcoholCollection.size(); i++) {
                    JSONObject alcohol = (JSONObject) AlcoholCollection.get(i);

                    System.out.println(color + "[");
                    for (Object key : alcohol.keySet()) {
                        System.out.println(color + "\t" + key + ": " + alcohol.get(key));
                    }
                    System.out.println(color + "]");
                }
            }

        } else {
            System.out.println(errorColor + "No se pudo eliminar");
        }
    }
}
