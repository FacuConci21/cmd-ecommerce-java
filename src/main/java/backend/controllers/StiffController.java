package backend.controllers;

import application.models.Product;
import appinterfaces.backend.Service;
import application.models.Stiff;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class StiffController implements Service {

    // Private attributes
    private FileWriter stiffCollectionWriter;
    private FileReader stiffCollectionReader;
    public JSONObject collection = new JSONObject();

    // Constructors
    public StiffController() {
    }

    // Public methods
    public void setStiffCollectionWriter(FileWriter stiffCollectionWriter) {
        this.stiffCollectionWriter = stiffCollectionWriter;
    }

    public void setStiffCollectionReader(FileReader stiffCollectionReader) {
        this.stiffCollectionReader = stiffCollectionReader;
    }

    @Override
    public JSONArray GET() {
        JSONParser jsonParser = new JSONParser();

        try {
            this.collection = (JSONObject) jsonParser.parse(this.stiffCollectionReader);
            this.stiffCollectionReader.close();

            return  (JSONArray) this.collection.get("collection");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    @Override
    public JSONObject GET(String id) {
        return null;
    }

    @Override
    public int POST(Product newRecord) {
        JSONObject newStiffProduct = new JSONObject();

        newStiffProduct.put("_id", newRecord.getId());
        newStiffProduct.put("name", newRecord.getName());
        newStiffProduct.put("description", newRecord.getDescription());
        newStiffProduct.put("price", newRecord.getPrice());
        newStiffProduct.put("stock", newRecord.getStock());
        newStiffProduct.put("category", newRecord.getCategory());
        newStiffProduct.put("dateExpiry", ((Stiff) newRecord).getDateExpiry());
        newStiffProduct.put("fatPercentage", ((Stiff) newRecord).getFatPercentage());

        ((JSONArray) this.collection.get("collection")).add(newStiffProduct);

        try {
            this.stiffCollectionWriter.write(this.collection.toJSONString());
            this.stiffCollectionWriter.flush();

            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 3;
        }
    }

    @Override
    public JSONObject PUT(String id, JSONObject updatedObject) {
        return null;
    }

    @Override
    public int DELETE(String id) {
        return 0;
    }

}
