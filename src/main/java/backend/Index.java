package backend;

import application.Dairy;
import application.Product;
import iapplication.Router;
import iapplication.RoutesAndPaths;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class Index implements Router, RoutesAndPaths {

    // Private attributes
    private String collectionName;
    private DairyController dairyController;
    private FileReader collectionReader;

    // Private methods
    private FileReader connectTo() {
        try {
            return this.collectionReader = new FileReader(DAIRY_URL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void closeReader() {
        try {
            this.collectionReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Contructors
    public Index() {
        this.dairyController = new DairyController();
    }

    public Index(String collectionName) {
        this.collectionName = collectionName;
        dairyController = new DairyController();
    }

    // Public methods
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public int connect() {
        File database = new File(SERVER_URI);
        if (database.exists() && database.isDirectory()) {
            return 5;
        }
        return 4;
    }

    @Override
    public JSONArray GET() {
        return null;
    }

    @Override
    public JSONObject GET(String _id) {
        return null;
    }

    @Override
    public int POST(Product newRecord) {

        if (newRecord instanceof Dairy) {
            System.out.println(this.connectTo());
            this.closeReader();
            dairyController.POST(newRecord);
        }

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
