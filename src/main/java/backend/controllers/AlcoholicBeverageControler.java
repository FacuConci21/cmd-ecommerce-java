package backend.controllers;

import application.models.AlcoholicBeverage;
import application.models.Product;
import iapplication.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AlcoholicBeverageControler implements Service {
    private FileWriter AlcoholCollectionWriter;
    private FileReader AlcoholCollectionReader;
    public JSONObject collection = new JSONObject();

    public AlcoholicBeverageControler(){}

    public void setAlcoholCollectionWriter(FileWriter alcoholCollectionWriter) {
        AlcoholCollectionWriter = alcoholCollectionWriter;
    }

    public void setAlcoholCollectionReader(FileReader alcoholCollectionReader) {
        AlcoholCollectionReader = alcoholCollectionReader;
    }

    @Override
    public JSONArray GET() {
        JSONParser jsonParser = new JSONParser();


        try {
            this.collection = (JSONObject) jsonParser.parse(this.AlcoholCollectionReader);
            AlcoholCollectionReader.close();

            return (JSONArray) this.collection.get("collection");
        } catch (IOException | ParseException e) {
            // e.printStackTrace();
            return new JSONArray();
        }

    }

    @Override
    public JSONObject GET(String id) {
        return null;
    }

    @Override
    public int POST(Product newRecord) {
        JSONObject newProduct = new JSONObject();

        newProduct.put("_id", newRecord.getId());
        newProduct.put("name", newRecord.getName());
        newProduct.put("description", newRecord.getDescription());
        newProduct.put("price", newRecord.getPrice());
        newProduct.put("stock", newRecord.getStock());
        newProduct.put("category", newRecord.getCategory());
        newProduct.put("liters", ((AlcoholicBeverage) newRecord).getLiter());
        newProduct.put("alcohol_percentage", ((AlcoholicBeverage) newRecord).getPercentage());


        ((JSONArray) this.collection.get("collection")).add(newProduct);

        try {

            this.AlcoholCollectionWriter.write(this.collection.toJSONString());
            this.AlcoholCollectionWriter.flush();

            return 0;
        } catch (IOException e) {
            return 3;
        }
    }

    @Override
    public JSONObject PUT(String id) {
        return null;
    }

    @Override
    public int DELETE(String id) {
        return 0;
    }
}
