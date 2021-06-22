package backend;

import application.Dairy;
import application.Product;
import iapplication.Service;
import iapplication.RoutesAndPaths;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class Index implements Service, RoutesAndPaths {

    // Private attributes
    private String collectionName;
    private DairyController dairyController;
    private FileReader collectionReader;
    private FileWriter collectionWriter;

    // Private methods
    private FileReader connectToRead(String URL) {
        try {
            this.collectionReader = new FileReader(URL);
            return this.collectionReader;
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

    private FileWriter connectToWrite(String URL) {
        try {
            this.collectionWriter = new FileWriter(URL);
            return this.collectionWriter;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void closeWriter() {
        try {
            this.collectionWriter.flush();
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
        dairyController.setDairyCollectionReader(this.connectToRead(DAIRY_URL));
        JSONArray result = dairyController.GET();
        this.closeReader();
        return result;
    }

    @Override
    public JSONObject GET(String _id) {
        return null;
    }

    @Override
    public int POST(Product newRecord) {
        int result = -1;

        if (newRecord instanceof Dairy) {
            dairyController.setDairyCollectionReader(this.connectToRead(DAIRY_URL));
            dairyController.GET();
            this.closeReader();

            dairyController.setDairyCollectionWriter(this.connectToWrite(DAIRY_URL));
            result = dairyController.POST(newRecord);
            this.closeWriter();
        }

        return result;
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
