package application.models;

import appinterfaces.Colors;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AlcoholicBeverage extends Product{

    // Private atrributes
    private float liter;
    private int percentage;

    // Constructors
    public AlcoholicBeverage() {}

    public AlcoholicBeverage(int id, int category, String name, String description, float price, int stock){
        super(id,category,name,description,price,stock);
    }

    public AlcoholicBeverage(int category, String name, String description, float price, int stock,
                             float liter, int percentage)
    {
        super(category, name, description, price, stock);
        this.liter = liter;
        this.percentage = percentage;
    }

    public AlcoholicBeverage(int id, int category, String name, String description, float price, int stock,
                             float liter, int percentage)
    {
        super(id,category,name,description,price,stock);
        this.liter = liter;
        this.percentage = percentage;
    }

    // Public static methods
    public static JSONObject toJson(AlcoholicBeverage product) {
        JSONParser jsonParser = new JSONParser();

        try {
            Object jsonAlcoholic = jsonParser.parse( "{"+
                    "\"_id\":\"" + product.getId() + "\"," +
                    "\"name\":\"" + product.getName() + "\"," +
                    "\"description\":\"" + product.getDescription() + "\"," +
                    "\"category\":\"" + product.getCategory() + "\"," +
                    "\"price\":\"" + product.getPrice() + "\"," +
                    "\"stock\":\"" + product.getStock() + "\"," +
                    "\"alcohol_percentage\":\"" + product.getPercentage() + "\"," +
                    "\"liters\":\"" + product.getLiter() + "\"" +
                    "}"
            );
            return (JSONObject) jsonAlcoholic;
        } catch (ParseException e) {
            //e.printStackTrace();
            return null;
        }
    }

    // Public methods
    public float getLiter() {
        return liter;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public void setLiter(float liter) {
        this.liter = liter;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return super.toString() +
                Colors.ANSI_GREEN +" Litros: " + Colors.ANSI_DEFAULT + liter +
                Colors.ANSI_GREEN +" Alcohol: " + Colors.ANSI_DEFAULT + percentage + "%";
    }
}