package backend.controllers;

import application.models.AlcoholicBeverage;
import application.models.Dairy;
import application.models.Product;
import appinterfaces.backend.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
        int result = 3;
        try{

            int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();
            JSONObject newProduct = new JSONObject();

            newProduct.put("name", newRecord.getName());
            newProduct.put("description", newRecord.getDescription());
            newProduct.put("price", newRecord.getPrice());
            newProduct.put("stock", newRecord.getStock());
            newProduct.put("category", newRecord.getCategory());
            newProduct.put("fatPercentage", ((Dairy) newRecord).getFat_percentage());
            newProduct.put("dateExpiry", ((Dairy) newRecord).getDate_expiry());
            newProduct.put("vitamins", ((Dairy) newRecord).getVitamins());

            if (sizeOfCollection > 0){
                for (int i = 0; i < sizeOfCollection; i++) {
                    JSONObject product = (JSONObject) ((JSONArray) this.collection.get("collection")).get(i);

                    int idProduct = Integer.parseInt(product.get("_id").toString());
                    int newProductId = newRecord.getId();

                    if (idProduct == newProductId) {
                        newProduct.put("_id", sizeOfCollection + 1 );
                    } else {
                        newProduct.put("_id",newRecord.getId());
                    }
                }

                ((JSONArray) this.collection.get("collection")).add(newProduct);
                this.dairyCollectionWriter.write(this.collection.toJSONString());
                this.dairyCollectionWriter.flush();
                result = 0;
            }
        } catch (IOException e) {
            return result;
        }
        return result;
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
