import backend.Index;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BackendTester {
    public static void main(String[] args) {
        Index index = new Index();

        if (index.connect() == 5) {
            System.out.println("connected");
            //System.out.println(index.GET());
            JSONArray dairyCollection = index.GET();

            JSONObject dairy = (JSONObject) dairyCollection.get(0);

            for (Object key: dairy.keySet()) {
                System.out.println(key + ": " + dairy.get(key));
            }
        }
    }
}