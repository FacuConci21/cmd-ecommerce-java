package application;

import application.models.Product;
import application.models.Stiff;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

    // Publics
    public static Vector<Product> loadStiff(JSONArray productsCollection) {
        Vector<Product> productsList = new Vector<>();
        productsCollection.forEach( jsonStiff -> {
            productsList.add(parseToStiffProduct((JSONObject) jsonStiff));
        });
        return productsList;
    }
} // Loader
