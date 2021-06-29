package application.models;

import appinterfaces.Colors;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.time.LocalDate;

public class Stiff extends Product {

    // Private atrributes
    private String dateExpiry;
    private int fatPercentage;

    // Constructors
    public Stiff() {}

    public Stiff(int id, int category, String name, String description, float price, int stock,
                 String dateExpiry, int fatPercentage)
    {
        super(id, category,name, description, price, stock);
        this.dateExpiry = dateExpiry;
        this.fatPercentage = fatPercentage;
    }

    // Public methods
    public String getDateExpiry() {
        return dateExpiry;
    }

    public int getFatPercentage() {
        return fatPercentage;
    }

    @Override
    public String toString() {
        return super.toString() +
                Colors.ANSI_GREEN + "Fecha de expiracion: " + Colors.ANSI_DEFAULT + dateExpiry +
                Colors.ANSI_GREEN + "Porcentaje en calorias: " + Colors.ANSI_DEFAULT + fatPercentage;
    }
    public static JSONObject toJson(Stiff product) {
        JSONParser jsonParser = new JSONParser();
        try {
            Object jsonStiff = jsonParser.parse(
                    "{" +
                    "\"_id\":" + product.getId() +
                            "\"name\":" + "\"" + product.getName() + "\"," +
                            "\"description\":" + "\""  + product.getDescription() + "\"," +
                            "\"category\":" + "\"" + product.getCategory() + "\"," +
                            "\"price\":" + "\"" + product.getPrice() + "\"," +
                            "\"stock\":" + "\"" + product.getStock() + "\"," +
                            "\"fatPercentage\":" + "\"" + product.getFatPercentage() + "\"," +
                            "\"dateExpiry\":" + "\"" + product.getDateExpiry() + "\""+ "}"
            );

            return (JSONObject) jsonStiff;
        } catch (ParseException e) {
            //e.printStackTrace();
            return null;
        }
    }


    // Public static methods
    public static boolean controlDate(String date){
        boolean isDate = false;

        if (date.contains("-")){
            String [] arrayDate = date.split("-");
            if(arrayDate.length == 3) {

                int maxDay = LocalDate.MAX.getDayOfMonth();
                int maxMonth = LocalDate.MAX.getMonthValue();
                int year = LocalDate.now().getYear();
                int maxYear = LocalDate.MAX.getYear();

                if (Integer.parseInt(arrayDate[0]) >= 1 && Integer.parseInt(arrayDate[0]) <= maxDay) {
                    isDate = true;
                } else{ return false; }
                if (Integer.parseInt(arrayDate[1]) >= 1 && Integer.parseInt(arrayDate[1]) <= maxMonth) {
                    isDate = true;
                } else{ return false;}
                if (Integer.parseInt(arrayDate[2]) >= 1 && Integer.parseInt(arrayDate[2]) <= maxYear) {
                    isDate = true;
                } else{ return false;}
            }

        }
        return isDate;
    }
}
