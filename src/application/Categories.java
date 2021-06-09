package application;

public class Categories {
     private int id;
     private String name;

    public Categories(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Categories() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Id Categoria: " + id
                +"Nombre de categoria: "+ name;
    }
}
