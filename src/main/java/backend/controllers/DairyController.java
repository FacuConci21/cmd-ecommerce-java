package backend.controllers;

import appinterfaces.ResultsProgram;
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

    // Private methods
    private JSONObject findById(String id) {
        int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();
        for (int i = 0; i < sizeOfCollection; i++) {

            JSONObject product = (JSONObject) ((JSONArray) this.collection.get("collection")).get(i);
            String idProduct = product.get("_id").toString();

            if (idProduct.equals(id)) {
                return product;
            }
        }

        return new JSONObject();
    }

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

            if (idProduct.equals(id)) {
                return product;
            }
        }

        return null;
    }

    @Override
    public int POST(Product newRecord) {
        int result = ResultsProgram.ERROR;
        try {

            int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();
            JSONObject newProduct = new JSONObject();

            newProduct.put("name", newRecord.getName());
            newProduct.put("description", newRecord.getDescription());
            newProduct.put("price", newRecord.getPrice());
            newProduct.put("stock", newRecord.getStock());
            newProduct.put("category", newRecord.getCategory());
            newProduct.put("fatPercentage", ((Dairy) newRecord).getFatPercentage());
            newProduct.put("dateExpiry", ((Dairy) newRecord).getDateExpiry());
            newProduct.put("vitamins", ((Dairy) newRecord).getVitamins());
            newProduct.put("_id", sizeOfCollection + 1);

            ((JSONArray) this.collection.get("collection")).add(newProduct);
            this.dairyCollectionWriter.write(this.collection.toJSONString());
            this.dairyCollectionWriter.flush();
            result = ResultsProgram.SUCCESS;

        } catch (IOException e) {
            return result;
        }
        return result;
    }

    @Override
    public JSONObject PUT(String id, JSONObject updatedObject) {


        int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();
        for (int i = 0; i < sizeOfCollection; i++) {

            JSONObject product = (JSONObject) ((JSONArray) this.collection.get("collection")).get(i);
            String idProduct = product.get("_id").toString();

            if (idProduct.equals(id)) {
                ((JSONArray) this.collection.get("collection")).set(i, updatedObject);

                try {
                    this.dairyCollectionWriter.write(this.collection.toJSONString());
                    this.dairyCollectionWriter.flush();

                    return (JSONObject) ((JSONArray) this.collection.get("collection")).get(i);
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return new JSONObject();
    }

    @Override
    public int DELETE(String id) {
        int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();
        try {
            for (int i = 0; i < sizeOfCollection; i++) {

                JSONObject product = (JSONObject) ((JSONArray) this.collection.get("collection")).get(i);
                String idProduct = product.get("_id").toString();


                if (idProduct.equals(id)) {
                    ((JSONArray) this.collection.get("collection")).remove(i);
                    this.dairyCollectionWriter.write(this.collection.toJSONString());
                    this.dairyCollectionWriter.flush();

                    return ResultsProgram.SUCCESS;
                }

            }
        } catch (IOException e) {
            return ResultsProgram.ERROR;
        }
        return ResultsProgram.ERROR;
    }
}
