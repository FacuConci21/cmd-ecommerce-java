package application;

public class AlcoholicBeverage extends Product{

    // Private atrributes
    private float liter;
    private int percentage;

    // Constructors
    public AlcoholicBeverage() {}

    public AlcoholicBeverage(int id, int category, String name, String description, float price, int stock){
        super(id,category,name,description,price,stock);
    }

    public AlcoholicBeverage(int id, int category, String name, String description, float price, int stock,
                             float liter, int percentage)
    {
        super(id,category,name,description,price,stock);
        this.liter = liter;
        this.percentage = percentage;
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
                Colors.ANSI_GREEN +"Contenido en Litros: " + Colors.ANSI_DEFAULT + liter +
                Colors.ANSI_GREEN +"Porcentaje de alcohol: " + Colors.ANSI_DEFAULT + percentage;
    }
}