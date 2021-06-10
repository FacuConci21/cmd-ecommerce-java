package application;
import java.util.Vector;
import java.time.LocalDate;

public class Dairy extends Product{

    private int fat_percentage;
    private String date_expiry;
    private Vector <String> vitamins;

    public Dairy() {
    }

    public Dairy(int fat_percentage, String date_expiry, Vector<String> vitamins,
                 int id, int category, String name, String description, float price, int stock) {

        super(id, category, name, description, price, stock);
        this.fat_percentage = fat_percentage;
        this.date_expiry = date_expiry;
        this.vitamins = vitamins;
    }

    public int getFat_percentage() {
        return fat_percentage;
    }

    public String getDate_expiry() {
        return date_expiry;
    }

    public Vector<String> getVitamins() {
        return vitamins;
    }

    @Override
    public String toString() {
        return super.toString() +
                Colors.ANSI_GREEN+ " Porcentaje de calorias: " + Colors.ANSI_DEFAULT +fat_percentage+
                Colors.ANSI_GREEN+ " Fecha de expiracion: " + Colors.ANSI_DEFAULT + date_expiry+
                Colors.ANSI_GREEN+ " Vitaminas que contiene: " + Colors.ANSI_DEFAULT + vitamins;
    }


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

                System.out.println(month);

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
