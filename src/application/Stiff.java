package application;

public class Stiff extends Product {
    private String date_expiry;
    private int fat_percentage;

    public Stiff() {

    }

    public Stiff(int id, int category, String name, String description, float price, int stock, String date_expiry, int fat_percentage) {
        super(id, category,name, description, price, stock);
        this.date_expiry = date_expiry;
        this.fat_percentage = fat_percentage;
    }

    public String getDate_expiry() {
        return date_expiry;
    }

    public int getFat_percentage() {
        return fat_percentage;
    }
}
