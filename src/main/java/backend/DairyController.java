package backend;

import application.Dairy;
import application.Product;
import iapplication.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DairyController implements Service {

    // Private attributes
    private FileWriter dairyCollectionWriter;
    private FileReader dairyCollectionReader;
    public JSONObject collection = new JSONObject();

    // Constructors
    public DairyController() {
    }

    // Public methods
    public void setDairyCollectionWriter(FileWriter dairyCollectionWriter) {
        this.dairyCollectionWriter = dairyCollectionWriter;
    }

    public void setDairyCollectionReader(FileReader dairyCollectionReader) {
        this.dairyCollectionReader = dairyCollectionReader;
    }

    @Override
    public JSONArray GET() {
        JSONParser jsonParser = new JSONParser();


        try {
            this.collection = (JSONObject) jsonParser.parse(this.dairyCollectionReader);
            dairyCollectionReader.close();

            return (JSONArray) this.collection.get("collection");
        } catch (IOException | ParseException e) {
            // e.printStackTrace();
            return new JSONArray();
        }
    }

    @Override
    public JSONObject GET(String id) {

        int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();
        for (int i = 0; i < sizeOfCollection; i++) {

            JSONObject product = (JSONObject) ((JSONArray) this.collection.get("collection")).get(i);
            String idProduct = product.get("_id").toString();

            if (idProduct.equals(id)){
                return product;
            }
        }

        return new JSONObject();
    }

    @Override
    public int POST(Product newRecord) {
        JSONObject newDairyProduct = new JSONObject();

        newDairyProduct.put("_id", newRecord.getId());
        newDairyProduct.put("name", newRecord.getName());
        newDairyProduct.put("description", newRecord.getDescription());
        newDairyProduct.put("price", newRecord.getPrice());
        newDairyProduct.put("stock", newRecord.getStock());
        newDairyProduct.put("category", newRecord.getCategory());
        newDairyProduct.put("fatPercentage", ((Dairy) newRecord).getFat_percentage());
        newDairyProduct.put("dateExpiry", ((Dairy) newRecord).getDate_expiry());
        newDairyProduct.put("vitamins", ((Dairy) newRecord).getVitamins());

        ((JSONArray) this.collection.get("collection")).add(newDairyProduct);

        try {
            this.dairyCollectionWriter.write(this.collection.toJSONString());
            this.dairyCollectionWriter.flush();

            return 0;
        } catch (IOException e) {
            //e.printStackTrace();
            return 3;
        }
    }

    @Override
    public JSONObject PUT(String id) {


        return null;
    }

    @Override
    public int DELETE(String id) {
        int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();
        for (int i = 0; i < sizeOfCollection; i++) {

            JSONObject product = (JSONObject) ((JSONArray) this.collection.get("collection")).get(i);
            String idProduct = product.get("_id").toString();

            if (idProduct.equals(id)){
                ((JSONArray) this.collection.get("collection")).remove(i);
                return 0;
            }
        }
        return -1;
    }
}
