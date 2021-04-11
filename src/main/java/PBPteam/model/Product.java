package PBPteam.model;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class Product {
    private static ObservableList<Part> parts = FXCollections.observableArrayList();
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
    public IntegerProperty productIdProperty() {
        return productId;
    }
    public String getName() {
        return name.get();
    }
    public StringProperty nameProperty() {
        return name;
    }
    public double getPrice() {
        return price.get();
    }
    public DoubleProperty priceProperty() {
        return price;
    }
    public int getStock() {
        return stock.get();
    }
    public IntegerProperty stockProperty() {
        return stock;
    }
    //metódusok hozzáadáshoz és törléshez
    public void addPart(Part part){
        parts.add(part);
    }
    public boolean removePart(int partNum){
        parts.remove(partNum);
        return true;
    }
}

