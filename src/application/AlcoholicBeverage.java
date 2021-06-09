package application;

public class AlcoholicBeverage extends Product{

    private float liter;
    private int percentage;

    public AlcoholicBeverage(int id, int category, String name,String description,float price,
                             int stock){
        super(id,category,name,description,price,stock);
    }

    public AlcoholicBeverage(int id,int category,String name,String description,float price,
                             int stock, float liter, int percentage) {
        super(id,category,name,description,price,stock);
        this.liter = liter;
        this.percentage = percentage;
    }

    public AlcoholicBeverage() {}

    public float getLiter() {
        return liter;
    }

    public void setLiter(float liter) {
        this.liter = liter;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return super.toString() + "Contenido en Litros: " + liter
                +"Porcentaje de alcohol: "+ percentage;
    }
}