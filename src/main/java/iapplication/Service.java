package iapplication;

import application.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface Service {
    JSONArray GET();
    JSONObject GET(Long _id);
    int POST(Product newRecord);
    JSONObject PUT(String _id);
    int DELETE(Long _id);
}