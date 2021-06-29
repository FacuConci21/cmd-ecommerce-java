package application;

import application.models.AlcoholicBeverage;
import application.models.Dairy;
import application.models.Product;
import application.models.Stiff;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.Vector;

public abstract class Loader {

    // Privates
    private static Stiff parseToStiffProduct(JSONObject jsonStiff) {
        Stiff newStiff;
        newStiff = new Stiff(
                Integer.parseInt(jsonStiff.get("_id").toString()),
                Integer.parseInt(jsonStiff.get("category").toString()),
                jsonStiff.get("name").toString(),
                jsonStiff.get("description").toString(),
                Float.parseFloat(jsonStiff.get("price").toString()),
                Integer.parseInt(jsonStiff.get("stock").toString()),
                jsonStiff.get("dateExpiry").toString(),
                Integer.parseInt(jsonStiff.get("fatPercentage").toString())
        );
        return newStiff;
    }

    private static Dairy parseToDairyProduct(JSONObject jsonDairy) {
        return new Dairy(
                Integer.parseInt(jsonDairy.get("_id").toString()),
                Integer.parseInt(jsonDairy.get("category").toString()),
                jsonDairy.get("name").toString(),
                jsonDairy.get("description").toString(),
                Float.parseFloat(jsonDairy.get("price").toString()),
                Integer.parseInt(jsonDairy.get("stock").toString()),
                Integer.parseInt(jsonDairy.get("fatPercentage").toString()),
                jsonDairy.get("dateExpiry").toString(),
                new Vector<String>()
                );
    }

    private static AlcoholicBeverage parseToAlcoholicBeverageProduct(JSONObject jsonAlcoholicBeverage) {
        return new AlcoholicBeverage(
                Integer.parseInt(jsonAlcoholicBeverage.get("_id").toString()),
                Integer.parseInt(jsonAlcoholicBeverage.get("category").toString()),
                jsonAlcoholicBeverage.get("name").toString(),
                jsonAlcoholicBeverage.get("description").toString(),
                Float.parseFloat(jsonAlcoholicBeverage.get("price").toString()),
                Integer.parseInt(jsonAlcoholicBeverage.get("stock").toString()),
                Float.parseFloat(jsonAlcoholicBeverage.get("liter").toString()),
                Integer.parseInt(jsonAlcoholicBeverage.get("alcohol_percentage").toString())
        );
    }

    // Publics
    public static Vector<Product> loadStiff(JSONArray productsCollection) {
        Vector<Product> productsList = new Vector<>();
        productsCollection.forEach( jsonStiff -> {
            productsList.add(parseToStiffProduct((JSONObject) jsonStiff));
        });
        return productsList;
    }

    public static Vector<Product> loadDairy(JSONArray dairyCollection) {
        Vector<Product> productsList = new Vector<>();
        dairyCollection.forEach( jsonStiff -> {
            productsList.add(parseToStiffProduct((JSONObject) jsonStiff));
        });
        return productsList;
    }

    public static Vector<Product> loadAlcoholicBeverage(JSONArray alcoholicCollection) {
        Vector<Product> productsList = new Vector<>();
        alcoholicCollection.forEach( jsonStiff -> {
            productsList.add(parseToStiffProduct((JSONObject) jsonStiff));
        });
        return productsList;
    }
} // Loader
