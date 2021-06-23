package iapplication;

import application.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface Service {
    JSONArray GET();
    JSONObject GET(String id);
    int POST(Product newRecord);
    JSONObject PUT(String id);
    int DELETE(String id);
}