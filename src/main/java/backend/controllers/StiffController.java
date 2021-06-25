package backend.controllers;

import application.models.Product;
import appinterfaces.backend.Service;
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
        return 0;
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
