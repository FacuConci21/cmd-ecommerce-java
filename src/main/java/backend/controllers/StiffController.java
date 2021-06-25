package backend.controllers;

import application.models.Product;
import appinterfaces.backend.Service;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StiffController implements Service {

    // Private attributes

    // Constructors

    // Public methods
    @Override
    public JSONArray GET() {
        return null;
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
