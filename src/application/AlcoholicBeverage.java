package application;

public class AlcoholicBeverage extends Product{
    private float liter;
    private String name;


    public AlcoholicBeverage(float liter, String name){
        this.liter = liter;
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
