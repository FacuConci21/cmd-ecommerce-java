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
                    "\"vitamins\":\"" + product.getVitamins() + "\"" +
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

    @Override
    public String toString() {
        return super.toString() +
                Colors.ANSI_GREEN+ " Porcentaje de calorias: " + Colors.ANSI_DEFAULT +fatPercentage+
                Colors.ANSI_GREEN+ " Fecha de expiracion: " + Colors.ANSI_DEFAULT + dateExpiry+
                Colors.ANSI_GREEN+ " Vitaminas que contiene: " + Colors.ANSI_DEFAULT + vitamins;
    }

    // Public static methods
    public static boolean controlDate(String date){
        boolean isDate = false;

        if (date.contains("-")){
            String [] arrayDate = date.split("-");
            if(arrayDate.length == 3) {

                int day = LocalDate.now().getDayOfMonth(),
                    maxDay = LocalDate.MAX.getDayOfMonth(),
                    month = LocalDate.now().getMonthValue(),
                    maxMonth = LocalDate.MAX.getMonthValue(),
                    year = LocalDate.now().getYear(),
                    maxYear = LocalDate.MAX.getYear();

                if (Integer.parseInt(arrayDate[0]) >= day && Integer.parseInt(arrayDate[0]) <= maxDay) {
                    isDate = true;
                }else{ isDate = false;}
                if (Integer.parseInt(arrayDate[1]) >= month && Integer.parseInt(arrayDate[1]) <= maxMonth) {
                    isDate = true;
                }else{ isDate = false;}
                if (Integer.parseInt(arrayDate[2]) >= year && Integer.parseInt(arrayDate[2]) <= maxYear) {
                    isDate = true;
                }else{ isDate = false;}
            }
        }
        return isDate;
    }
}
