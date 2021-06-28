package application.models;

import appinterfaces.Colors;

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

    // Public static methods
    public static boolean controlDate(String date){
        boolean isDate = false;

        if (date.contains("-")){
            String [] arrayDate = date.split("-");
            if(arrayDate.length == 3) {

                int day = LocalDate.now().getDayOfMonth();
                int maxDay = LocalDate.MAX.getDayOfMonth();
                int month = LocalDate.now().getMonthValue();
                int maxMonth = LocalDate.MAX.getMonthValue();
                int year = LocalDate.now().getYear();
                int maxYear = LocalDate.MAX.getYear();

                if (Integer.parseInt(arrayDate[0]) >= day && Integer.parseInt(arrayDate[0]) <= maxDay) {
                    isDate = true;
                } else{ return false; }
                if (Integer.parseInt(arrayDate[1]) >= month && Integer.parseInt(arrayDate[1]) <= maxMonth) {
                    isDate = true;
                } else{ return false;}
                if (Integer.parseInt(arrayDate[2]) >= year && Integer.parseInt(arrayDate[2]) <= maxYear) {
                    isDate = true;
                } else{ return false;}
            }

        }
        return isDate;
    }
}
