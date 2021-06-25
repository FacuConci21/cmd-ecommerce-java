import application.models.AlcoholicBeverage;
import application.models.Dairy;
import application.models.Stiff;
import backend.Index;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Vector;

public class BackendTester {
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
            System.out.println("    --  connected   --");

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

        } else {
            System.out.println("    --  Not connected   --");
        }
    } // main()
} // BackendTester