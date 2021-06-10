package application;

public class Product {
    private int id;
    private String name;
    private String description;
    private float price;
    private int stock;
    private int category;

    public Product(){};

    public Product(int id, int category, String name, String description, float price, int stock) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }



    @Override
    public String toString() {
        return Colors.ANSI_GREEN + "Id: " + Colors.ANSI_DEFAULT  + id +
               Colors.ANSI_GREEN + " Nombre del producto: " + Colors.ANSI_DEFAULT  + name +
               Colors.ANSI_GREEN + " Descripcion: " + Colors.ANSI_DEFAULT  + description +
               Colors.ANSI_GREEN + " Precio: $" + Colors.ANSI_DEFAULT  + price +
               Colors.ANSI_GREEN + " Stock: " + Colors.ANSI_DEFAULT + stock +
               Colors.ANSI_GREEN + " Categoria: " + Colors.ANSI_DEFAULT  + category;
    }
}
