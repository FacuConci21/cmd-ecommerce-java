package productstests;

import application.models.Stiff;
import backend.Index;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StiffProductsTest {

    public static void PostTest(Index index, String color) {
        Stiff sProduct1 = new Stiff(
                1, 2, "Queso", "Queso La Paulina", 200.12f, 5,
                "24-6-2021", 30
        );
        Stiff sProduct2 = new Stiff(
                2, 2, "Salame", "Salame corte fino", 200.12f, 5,
                "24-6-2021", 30
        );
        Stiff sProduct3 = new Stiff(
                3, 2, "Mortadela", "3Kg - Mortadela Campofrio", 200.12f, 5,
                "24-6-2021", 30
        );

        /**
         * Basta con comentar estas lineas para evitar que se graben estos datos
         * y poder ver la respuesta del backend con una lista vacia.
         */

        /***/
        System.out.println(color + "Product posted: " + index.POST(sProduct1));
        System.out.println(color + "Product posted: " + index.POST(sProduct2));
        System.out.println(color + "Product posted: " + index.POST(sProduct3));
    }

    public static void GetTest(Index index, String color) {
        JSONArray stiffCollection = index.GET();
        /**
         * Aca inicio la operacion de obtencion de datos, para consultar los datos que se almacenaron
         * en el anterior paso.
         * Si esta vacio se muestra [], la coleccion vacia.
         * Y si se traen los datos correctamente se muestran los productos listados.
         */



        if (stiffCollection.size() == 0) {
            System.out.println(color + stiffCollection);
        } else {
            System.out.println(color + "[");
            for (int i = 0; i < stiffCollection.size(); i++) {
                JSONObject stiff = (JSONObject) stiffCollection.get(i);

                System.out.println(color + "\t{");
                for (Object key : stiff.keySet()) {
                    System.out.println(color + "\t\t" + key + ": " + stiff.get(key));
                }
                System.out.println(color + "\t}");
            }
            System.out.println(color + "]");
        }
    }

    public static void GetByIdTest(Index index, String id, String color, String errorColor) {
        JSONObject StiffObjectCollection = index.GET(id);
        if (StiffObjectCollection == null){
            System.out.println(errorColor + "No se encontro el producto.");
        } else {
            System.out.println(color + StiffObjectCollection);
        }
    }

    public static void PutTest(Index index, String id, String color, String errorColor) {
        JSONObject sProduct = index.GET(id);
        if (sProduct == null){
            System.out.println(errorColor + "No se encontro el producto que desea modificar.");
        } else {
            sProduct.replace("name", "otro fiambre coño");
            System.out.println(color + index.PUT(id, sProduct));
        }
    }

    public static void DeleteTest(Index index, String id, String color) {
        System.out.println(color + index.DELETE(id));
    }
} // StiffProductsTest