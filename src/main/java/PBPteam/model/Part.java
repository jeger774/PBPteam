package PBPteam.model;
import javafx.beans.property.*;

public class Part {
    private final IntegerProperty partId;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty stock;

    public Part() {
        partId = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        stock = new SimpleIntegerProperty();
    }

    //set
    public void setPartId(int partId) {
        this.partId.set(partId);
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public void setPrice(double price) {
        this.price.set(price);
    }
    public void setStock(int Stock) {
        this.stock.set(Stock);
    }

    //get
    public int getPartId() {
        return partId.get();
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