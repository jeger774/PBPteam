package PBPteam.model;
import javafx.beans.property.*;

public class Product {
    private final IntegerProperty productId;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty stock;

    public Product() {
        productId = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        stock = new SimpleIntegerProperty();
    }

    //set
    public void setProductId(int productId) {
        this.productId.set(productId);
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public void setPrice(double price) {
        this.price.set(price);
    }
    public void setStock(int stock) {
        this.stock.set(stock);
    }

    //get
    public int getProductId() {
        return productId.get();
    }
    public String getName() {
        return name.get();
    }
    public double getPrice() {
        return price.get();
    }
    public int getStock() {
        return stock.get();
    }
}

