package productstests;

import application.models.Stiff;
import backend.Index;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StiffTest {
    public static void test(Index index) {
        System.out.println("POST opperation:");

        {
            /**
             * Aca genero objetos para guardar en la BD, en la coleccion de datos
             * de productos Dairy, lo encerre dentro de {} para que cuando termine de hacer POST
             * se destruyan los objetos que ya no se van a usar para que no ocupen memoria.
             */
            Stiff sProduct1 = new Stiff(
                    1, 2, "Queso", "Queso La Paulina", 200.12f, 5,
                    "24-6-2021", 30
            );
            Stiff sProduct2 = new Stiff(
                    1, 2, "Salame", "Salame corte fino", 200.12f, 5,
                    "24-6-2021", 30
            );
            Stiff sProduct3 = new Stiff(
                    1, 2, "Mortadela", "3Kg - Mortadela Campofrio", 200.12f, 5,
                    "24-6-2021", 30
            );

            /**
             * Basta con comentar estas lineas para evitar que se graben estos datos
             * y poder ver la respuesta del backend con una lista vacia.
             */

            /***/
            System.out.println(index.POST(sProduct1));
            System.out.println(index.POST(sProduct2));
            System.out.println(index.POST(sProduct3));
        }

        System.out.println("GET opperation:");
        index.setCollectionName("stiff");
        JSONArray stiffCollection = index.GET();

        /**
         * Aca inicio la operacion de obtencion de datos, para consultar los datos que se almacenaron
         * en el anterior paso.
         * Si esta vacio se muestra [], la coleccion vacia.
         * Y si se traen los datos correctamente se muestran los productos listados.
         */

        if (stiffCollection.size() == 0) {
            System.out.println(stiffCollection);
        } else {
            System.out.println("[");
            for (int i = 0; i < stiffCollection.size(); i++) {
                JSONObject stiff = (JSONObject) stiffCollection.get(i);

                System.out.println("\t{");
                for (Object key : stiff.keySet()) {
                    System.out.println("\t\t" + key + ": " + stiff.get(key));
                }
                System.out.println("\t}");
            }
            System.out.println("]");

        }

        /**Prueba de GET by INDEX*/

        /**Prueba de DELETE*/

    }
}
