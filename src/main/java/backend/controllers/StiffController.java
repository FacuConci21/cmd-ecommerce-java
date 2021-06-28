package backend.controllers;

import application.models.Dairy;
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

            return (JSONArray) this.collection.get("collection");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
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
        JSONObject newStiffProduct = new JSONObject();
        int result = 3;

        try{

            int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();
            JSONObject newProduct = new JSONObject();

            newProduct.put("_id", newRecord.getId());
            newProduct.put("name", newRecord.getName());
            newProduct.put("description", newRecord.getDescription());
            newProduct.put("price", newRecord.getPrice());
            newProduct.put("stock", newRecord.getStock());
            newProduct.put("category", newRecord.getCategory());
            newProduct.put("dateExpiry", ((Stiff) newRecord).getDateExpiry());
            newProduct.put("fatPercentage", ((Stiff) newRecord).getFatPercentage());

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
                this.stiffCollectionWriter.write(this.collection.toJSONString());
                this.stiffCollectionWriter.flush();
                result = 0;
            } else {
                ((JSONArray) this.collection.get("collection")).add(newProduct);
                this.stiffCollectionWriter.write(this.collection.toJSONString());
                this.stiffCollectionWriter.flush();
                result = 0;
            }
        } catch (IOException e) {
            return result;
        }
        return result;
    }

    @Override
    public JSONObject PUT(String id, JSONObject updatedObject) {

        JSONObject product = new JSONObject();
        String idProduct;

        int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();

        for (int i = 0; i < sizeOfCollection; i++) {
            product = (JSONObject) ((JSONArray) this.collection.get("collection")).get(i);
            idProduct = product.get("_id").toString();

            if (idProduct.equals(id)) {
                ((JSONArray) this.collection.get("collection")).set(i, updatedObject);

                try {
                    this.stiffCollectionWriter.write(this.collection.toJSONString());
                    this.stiffCollectionWriter.flush();

                    return (JSONObject)((JSONArray) this.collection.get("collection")).get(i);
                } catch (IOException e) {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public int DELETE(String id) {
        JSONObject product = new JSONObject();
        String idProduct;
        int result = 1;

        int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();

        for (int i = 0; i < sizeOfCollection; i++) {
            product = (JSONObject) ((JSONArray) this.collection.get("collection")).get(i);
            idProduct = product.get("_id").toString();

            if (idProduct.equals(id)) {
                ((JSONArray) this.collection.get("collection")).remove(i);
                result = 0;
                break;
            }
        }
        try {
            this.stiffCollectionWriter.write(this.collection.toJSONString());
            this.stiffCollectionWriter.flush();

            return result;
        } catch (IOException e) {
            return -1;
        }
    }

} // StiffController
