package application;

import iapplication.Colors;

import java.time.LocalDate;

public class Stiff extends Product {

    // Private atrributes
    private String date_expiry;
    private int fat_percentage;

    // Constructors
    public Stiff() {}

    public Stiff(int id, int category, String name, String description, float price, int stock,
                 String date_expiry, int fat_percentage)
    {
        super(id, category,name, description, price, stock);
        this.date_expiry = date_expiry;
        this.fat_percentage = fat_percentage;
    }

    // Public methods
    public String getDate_expiry() {
        return date_expiry;
    }

    public int getFat_percentage() {
        return fat_percentage;
    }

    @Override
    public String toString() {
        return super.toString() +
                Colors.ANSI_GREEN + "Fecha de expiracion: " + Colors.ANSI_DEFAULT + date_expiry +
                Colors.ANSI_GREEN + "Porcentaje en calorias: " + Colors.ANSI_DEFAULT + fat_percentage;
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
