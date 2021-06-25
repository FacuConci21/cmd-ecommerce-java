package appinterfaces.backend;

import application.models.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileReader;

public interface Service {
    JSONArray GET();
    JSONObject GET(String id);
    int POST(Product newRecord);
    JSONObject PUT(String id);
    int DELETE(String id);
}