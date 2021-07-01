package backend;

import appinterfaces.ResultsProgram;
import application.models.AlcoholicBeverage;
import application.models.Dairy;
import application.models.Product;
import application.models.Stiff;
import backend.controllers.AlcoholicBeverageController;
import backend.controllers.DairyController;
import appinterfaces.backend.Service;
import appinterfaces.backend.RoutesAndPaths;
import backend.controllers.StiffController;
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
    private final DairyController dairyController;
    private final AlcoholicBeverageController alcoholController;
    private final StiffController stiffController;
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
        this.stiffController = new StiffController();
    }

    public Index(String collectionName) {
        this.collectionName = collectionName;
        this.dairyController = new DairyController();
        this.alcoholController = new AlcoholicBeverageController();
        this.stiffController = new StiffController();
    }

    // Public methods
    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public int connect() {
        File database = new File(SERVER_URI);
        if (database.exists() && database.isDirectory()) {
            return ResultsProgram.CONNECTION_SUCCESS;
        }
        return ResultsProgram.CONNECTION_FAILED;
    }

    @Override
    public JSONArray GET() {
        JSONArray result;
        switch (this.collectionName) {
            case "dairy": {
                this.dairyController.setDairyCollectionReader(this.connectToRead(DAIRY_URL));
                result = this.dairyController.GET();
                this.closeReader();
                return result;
            }
            case "stiff": {
                this.stiffController.setStiffCollectionReader(this.connectToRead(STIFF_URL));
                result = this.stiffController.GET();
                this.closeReader();
                return result;
            }
            case "alcoholic": {
                this.alcoholController.setAlcoholCollectionReader(this.connectToRead(ALCOHOLIC_BEVERAGE_URL));
                result = this.alcoholController.GET();
                this.closeReader();
                return result;
            }
            default: {
                /**
                 * Importante que retorne null, a modo de error en caso de que se ingrese mal
                 * un nombre de coleccion.
                 */
                return null;
            }
        }
    }

    @Override
    public JSONObject GET(String id) {
        switch (this.collectionName) {
            case "dairy": {
                this.dairyController.setDairyCollectionReader(this.connectToRead(DAIRY_URL));
                this.dairyController.GET();
                this.closeReader();

                JSONObject result = this.dairyController.GET(id);
                return result;
            }
            case "alcoholic": {
                this.alcoholController.setAlcoholCollectionReader(this.connectToRead(ALCOHOLIC_BEVERAGE_URL));
                this.alcoholController.GET();
                this.closeReader();

                JSONObject result = this.alcoholController.GET(id);
                return result;
            }
            case "stiff": {
                this.stiffController.setStiffCollectionReader(this.connectToRead(STIFF_URL));
                this.stiffController.GET();
                this.closeReader();

                JSONObject result = this.stiffController.GET(id);
                return result;
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int POST(Product newRecord) {
        int result = ResultsProgram.INVALID_VALUE;

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

        if (newRecord instanceof Stiff) {
            this.stiffController.setStiffCollectionReader(this.connectToRead(STIFF_URL));
            this.stiffController.GET();
            this.closeReader();

            this.stiffController.setStiffCollectionWriter(this.connectToWrite(STIFF_URL));
            result = this.stiffController.POST(newRecord);

            this.closeWriter();
        }

        return result;
    }

    @Override
    public JSONObject PUT(String id, JSONObject updatedObject) {
        JSONObject jsonResult = new JSONObject();

        if (!updatedObject.get("_id").toString().equals(id)) {
            /**
             * No se permite cambiar el valor de los Ids
             */
            return null;
        }

        switch (this.collectionName) {
            case "dairy": {
                this.dairyController.setDairyCollectionReader(this.connectToRead(DAIRY_URL));
                this.dairyController.GET();
                this.closeReader();

                this.dairyController.setDairyCollectionWriter(this.connectToWrite(DAIRY_URL));
                jsonResult = this.dairyController.PUT(id, updatedObject);
                this.closeWriter();

                return jsonResult;
            }

            case "alcoholic": {
                this.alcoholController.setAlcoholCollectionReader(this.connectToRead(ALCOHOLIC_BEVERAGE_URL));
                this.alcoholController.GET();
                this.closeReader();

                this.alcoholController.setAlcoholCollectionWriter(this.connectToWrite(ALCOHOLIC_BEVERAGE_URL));
                jsonResult = this.alcoholController.PUT(id, updatedObject);
                this.closeWriter();
                return jsonResult;
            }
            case "stiff": {
                this.stiffController.setStiffCollectionReader(this.connectToRead(STIFF_URL));
                this.stiffController.GET();
                this.closeReader();

                this.stiffController.setStiffCollectionWriter(this.connectToWrite(STIFF_URL));
                jsonResult = this.stiffController.PUT(id, updatedObject);
                this.closeWriter();

                return jsonResult;
            }
            default: {
                return null;
            }
        }
    }



    @Override
    public int DELETE(String id) {

        switch (this.collectionName){
            case "dairy":{
                this.dairyController.setDairyCollectionWriter(this.connectToWrite(DAIRY_URL));
                int result = this.dairyController.DELETE(id);
                this.closeWriter();
                return result;
            }
            case "alcoholic":{
                this.alcoholController.setAlcoholCollectionWriter(this.connectToWrite(ALCOHOLIC_BEVERAGE_URL));
                int result = this.alcoholController.DELETE(id);
                this.closeWriter();
                return result;
            }
            case "stiff": {
                this.stiffController.setStiffCollectionReader(this.connectToRead(STIFF_URL));
                this.stiffController.GET();
                this.closeReader();

                this.stiffController.setStiffCollectionWriter(this.connectToWrite(STIFF_URL));
                int result = this.stiffController.DELETE(id);
                this.closeWriter();

                return result;
            }
            default: {
                return ResultsProgram.INVALID_KEY;
            }
        }
    }

} // Index
