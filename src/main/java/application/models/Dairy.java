package application.models;
import appinterfaces.Colors;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Vector;
import java.time.LocalDate;

public class Dairy extends Product{

    // Private atrributes
    private int fatPercentage;
    private String dateExpiry;
    private Vector <String> vitamins;

    // Constructors
    public Dairy() {}

    public Dairy(int category, String name, String description, float price, int stock,
                 int fatPercentage, String dateExpiry, Vector<String> vitamins)
    {
        super(category, name, description, price, stock);
        this.fatPercentage = fatPercentage;
        this.dateExpiry = dateExpiry;
        this.vitamins = vitamins;
    }

    public Dairy(int id, int category, String name, String description, float price, int stock,
                 int fatPercentage, String dateExpiry, Vector<String> vitamins)
    {
        super(id, category, name, description, price, stock);
        this.fatPercentage = fatPercentage;
        this.dateExpiry = dateExpiry;
        this.vitamins = vitamins;
    }

    // Public static methods
    public static JSONObject toJson(Dairy product) {
        JSONParser jsonParser = new JSONParser();
        try {
             Object jsonDairy = jsonParser.parse( "{" +
                    "\"_id\":\"" + product.getId() + "\","+
                    "\"name\":\"" + product.getName() + "\","+
                    "\"description\":\"" + product.getDescription() + "\","+
                    "\"category\":\"" + product.getCategory() + "\","+
                    "\"price\":\"" + product.getPrice() + "\","+
                    "\"stock\":\"" + product.getStock() + "\","+
                    "\"fatPercentage\":\"" + product.getFatPercentage() + "\","+
                    "\"dateExpiry\":\"" + product.getDateExpiry() + "\","+
                    "\"vitamins\":" + product.getVitaminsAsStringArray() +
                     "}"
             );
             return (JSONObject) jsonDairy;
        } catch (ParseException e) {
            //e.printStackTrace();
            return null;
        }
    }

    // Public methods
    public int getFatPercentage() {
        return fatPercentage;
    }

    public String getDateExpiry() {
        return dateExpiry;
    }

    public Vector<String> getVitamins() {
        return vitamins;
    }

    public String getVitaminsAsStringArray() {
        return "[ \"" + this.vitamins.elementAt(0) +
                "\", \"" + this.vitamins.elementAt(1) +
                "\", \"" +this.vitamins.elementAt(2) + "\" ]";
    }

    @Override
    public String toString() {
        return super.toString() +
                Colors.ANSI_GREEN+ " Calorias: " + Colors.ANSI_DEFAULT +fatPercentage + "%" +
                Colors.ANSI_GREEN+ " Vto: " + Colors.ANSI_DEFAULT + dateExpiry+
                Colors.ANSI_GREEN+ " Vitaminas: " + Colors.ANSI_DEFAULT + vitamins;
    }

    // Public static methods
    public static boolean controlDate(String date){
        boolean isDate = false;

        if (date.contains("-")){
            String [] arrayDate = date.split("-");
            if(arrayDate.length == 3) {

                int maxDay = LocalDate.MAX.getDayOfMonth(),
                    maxMonth = LocalDate.MAX.getMonthValue(),
                    year = LocalDate.now().getYear(),
                    maxYear = LocalDate.MAX.getYear();

                isDate = (Integer.parseInt(arrayDate[0]) >= 1 && Integer.parseInt(arrayDate[0]) <= maxDay) &&
                        (Integer.parseInt(arrayDate[1]) >= 1 && Integer.parseInt(arrayDate[1]) <= maxMonth) &&
                        (Integer.parseInt(arrayDate[2]) >= year && Integer.parseInt(arrayDate[2]) <= maxYear);
            }
        }
        return isDate;
    }
}
