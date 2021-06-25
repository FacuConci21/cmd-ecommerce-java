package backend;

import application.models.AlcoholicBeverage;
import application.models.Dairy;
import application.models.Product;
import backend.controllers.AlcoholicBeverageController;
import backend.controllers.DairyController;
import appinterfaces.backend.Service;
import appinterfaces.backend.RoutesAndPaths;
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
    private AlcoholicBeverageController alcoholController;
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
        this.alcoholController = new AlcoholicBeverageController();
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
        switch (this.collectionName) {
            case "dairy":
            {
                this.dairyController.setDairyCollectionReader(this.connectToRead(DAIRY_URL));
                JSONArray result = this.dairyController.GET();
                this.closeReader();
                return result;
            }
            case "alcoholic":{
                this.alcoholController.setAlcoholCollectionReader(this.connectToRead(ALCOHOLIC_BEVERAGE_URL));
                JSONArray result = this.alcoholController.GET();
                this.closeReader();
                return result;
            }
            default:
            {
                return null;
            }
        }
    }

    @Override
    public JSONObject GET(String id) {
        switch(this.collectionName){
            case "dairy":{
                this.dairyController.setDairyCollectionReader(this.connectToRead(DAIRY_URL));
                JSONObject result = this.dairyController.GET(id);
                this.closeReader();
                return result;
            }
            case "alcoholic":{
                this.alcoholController.setAlcoholCollectionReader(this.connectToRead(ALCOHOLIC_BEVERAGE_URL));
                JSONObject result = this.alcoholController.GET(id);
                this.closeReader();
                return result;
            }
            default:{
                return null;
            }
        }


    }

    @Override
    public int POST(Product newRecord) {
        int result = -1;

        if (newRecord instanceof Dairy) {
            this.dairyController.setDairyCollectionReader(this.connectToRead(DAIRY_URL));
            this.dairyController.GET();
            this.closeReader();

            this.dairyController.setDairyCollectionWriter(this.connectToWrite(DAIRY_URL));
            result = this.dairyController.POST(newRecord);
            this.closeWriter();
        }

        if (newRecord instanceof AlcoholicBeverage) {
            this.alcoholController.setAlcoholCollectionReader(this.connectToRead(ALCOHOLIC_BEVERAGE_URL));
            this.alcoholController.GET();
            this.closeReader();

            this.alcoholController.setAlcoholCollectionWriter(this.connectToWrite(ALCOHOLIC_BEVERAGE_URL));
            result = this.alcoholController.POST(newRecord);
            this.closeWriter();
        }
        return result;
    }

    @Override
    public JSONObject PUT(String id) {
        return null;
    }


    @Override
    public int DELETE(String id) {

        switch (this.collectionName){
            case "dairy":{
                this.dairyController.setDairyCollectionReader(this.connectToRead(DAIRY_URL));
                int result = this.dairyController.DELETE(id);
                this.closeReader();
                return result;
            }
            case "alcoholic":{
                this.alcoholController.setAlcoholCollectionReader(this.connectToRead(ALCOHOLIC_BEVERAGE_URL));
                int result = this.alcoholController.DELETE(id);
                this.closeReader();
                return result;
            }
            default:{
                return -1;
            }
        }

    }


}
