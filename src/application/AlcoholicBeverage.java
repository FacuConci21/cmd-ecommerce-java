package application;

public class AlcoholicBeverage extends Product{
    private float liter;
    private String percentage;


    public AlcoholicBeverage(int id,String name,String description,float price,
                             int stock,float liter, String percentage){
        super(id,name,description,price,stock);


        this.liter = liter;
        this.percentage = percentage;
    }
    public AlcoholicBeverage(){

    }

    public float getLiter() {
        return liter;
    }

    public void setLiter(float liter) {
        this.liter = liter;
    }

    public String getName() {
        return percentage;
    }

    public void setName(String percentage) {
        this.percentage = percentage;
    }
}
