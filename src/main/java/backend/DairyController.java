package backend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import application.Product;
import iapplication.Router;
import iapplication.RoutesAndPaths;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DairyController implements Router {

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
