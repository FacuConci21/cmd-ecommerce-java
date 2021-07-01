package productstests;

import application.models.Stiff;
import backend.Index;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StiffProductsTest {

    public static void PostStiffProducts(Index index, String color) {
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




} // StiffProductsTest
