package backend;

import application.Product;
import iapplication.Service;
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

    // Constructors
    public DairyController() {
    }

    public void setDairyCollectionWriter(FileWriter dairyCollectionWriter) {
        this.dairyCollectionWriter = dairyCollectionWriter;
    }

    public void setDairyCollectionReader(FileReader dairyCollectionReader) {
        this.dairyCollectionReader = dairyCollectionReader;
    }

    @Override
    public JSONArray GET() {
        JSONParser jsonParser = new JSONParser();
        JSONObject collection = new JSONObject();

        try {
            collection = (JSONObject) jsonParser.parse(this.dairyCollectionReader);
            return (JSONArray) collection.get("collection");
        } catch (IOException | ParseException e) {
            // e.printStackTrace();
            return null;
        }
    }

    @Override
    public JSONObject GET(String _id) {
        return null;
    }

    @Override
    public int POST(Product newRecord) {
        return 0;
    }

    @Override
    public JSONObject PUT(String _id) {
        return null;
    }

    @Override
    public int DELETE(String _id) {
        return 0;
    }
}
