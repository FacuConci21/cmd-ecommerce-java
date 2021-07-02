package productstests;

import application.models.Product;
import application.models.AlcoholicBeverage;
import backend.Index;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class AlcoholBeverageProductsTest {
    public static void PostAlcoholBeverageProducts(Index index, String color){
        {
            AlcoholicBeverage aProduct1 = new AlcoholicBeverage(1, 2, "Sky Maracuya", "Sky saborizado sabor maracuya", 650.0f, 10,
                    1.0f, 45);

            AlcoholicBeverage aProduct2 = new AlcoholicBeverage(2, 2, "Dada", "Dada 7", 250.0f, 20,
                    0.5f, 25);
            /**
             * Basta con comentar esta linea para evitar que se graben estos datos
             * y poder ver la respuesta del backend con una lista vacia.
             */
            System.out.println(color + "Product posted: " + index.POST(aProduct1));
            System.out.println(color + "Product posted: " + index.POST(aProduct2));


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

    public static void GetAlcoholBeverageProductsById(Index index, String id, String color, String errorColor){
        JSONObject AlcoholObjectCollection = index.GET(id);
        if (AlcoholObjectCollection == null){
            System.out.println(errorColor + "No se encontro el producto.");
        } else {
            System.out.println(color + AlcoholObjectCollection);
        }

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
