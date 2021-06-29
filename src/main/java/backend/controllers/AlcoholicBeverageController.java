package backend.controllers;

import appinterfaces.ResultsProgram;
import application.models.AlcoholicBeverage;
import application.models.Product;
import appinterfaces.backend.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AlcoholicBeverageController implements Service {
    private FileWriter alcoholCollectionWriter;
    private FileReader alcoholCollectionReader;
    public JSONObject collection = new JSONObject();

    public AlcoholicBeverageController(){}

    public void setAlcoholCollectionWriter(FileWriter alcoholCollectionWriter) {
        this.alcoholCollectionWriter = alcoholCollectionWriter;
    }

    public void setAlcoholCollectionReader(FileReader alcoholCollectionReader) {
        this.alcoholCollectionReader = alcoholCollectionReader;
    }

    @Override
    public JSONArray GET() {
        JSONParser jsonParser = new JSONParser();

        try {
            this.collection = (JSONObject) jsonParser.parse(this.alcoholCollectionReader);
            this.alcoholCollectionReader.close();

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

        return null;
    }

    @Override
    public int POST(Product newRecord) {
        int result = ResultsProgram.ERROR;
        try{

            int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();
            JSONObject newProduct = new JSONObject();

            newProduct.put("name", newRecord.getName());
            newProduct.put("description", newRecord.getDescription());
            newProduct.put("price", newRecord.getPrice());
            newProduct.put("stock", newRecord.getStock());
            newProduct.put("category", newRecord.getCategory());
            newProduct.put("liters", ((AlcoholicBeverage) newRecord).getLiter());
            newProduct.put("alcohol_percentage", ((AlcoholicBeverage) newRecord).getPercentage());

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
                this.alcoholCollectionWriter.write(this.collection.toJSONString());
                this.alcoholCollectionWriter.flush();
                result = ResultsProgram.SUCCESS;
            } else {
                ((JSONArray) this.collection.get("collection")).add(newProduct);
                this.alcoholCollectionWriter.write(this.collection.toJSONString());
                this.alcoholCollectionWriter.flush();
                result = ResultsProgram.SUCCESS;
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
                    this.alcoholCollectionWriter.write(this.collection.toJSONString());
                    this.alcoholCollectionWriter.flush();

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

        try {
            int sizeOfCollection = ((JSONArray) this.collection.get("collection")).size();
            for (int i = 0; i < sizeOfCollection; i++) {

                JSONObject product = (JSONObject) ((JSONArray) this.collection.get("collection")).get(i);
                String idProduct = product.get("_id").toString();

                if (idProduct.equals(id)) {
                    ((JSONArray) this.collection.get("collection")).remove(i);
                    this.alcoholCollectionWriter.write(this.collection.toJSONString());
                    this.alcoholCollectionWriter.flush();

                    return ResultsProgram.SUCCESS;
                }
            }

        } catch (IOException e) {
            return ResultsProgram.ERROR;
        }
        return ResultsProgram.ERROR;
    }
}
