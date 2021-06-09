package application;
import java.util.Vector;

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
}
