package PBPteam.model;
import PBPteam.Database;
import javafx.collections.ObservableList;

public class Inventory {
    public static Database database = new Database();

    //termék kezelés
    public static ObservableList<Product> getProductsInventory(){
        return database.selectAllProducts();
    }
    public static void addProduct(Product product){ database.insertProduct(product.getName(), product.getPrice(), product.getStock()); }
    public static void removeProduct(Product product) {
        database.deleteProduct(product.getProductId());
        database.deleteRent(product.getProductId());
    }
    public static ObservableList<Product> lookupProduct(String searchItem){
        return database.selectAllProductsByName(searchItem);
    }

    //termék frissítés metódus (ModifyProduct)
    @SuppressWarnings("unused")
    public static void updateProduct(int productNum, Product product){
        database.updateProduct(product.getProductId(), product.getName(), product.getPrice(), product.getStock());
    }

    //alkatrész kezelés
    public static ObservableList<Part> getPartsInventory(){
        return database.selectAllPart();
    }
    public static void addParts(Part part){
        database.insertPart(part.getName(), part.getPrice(), part.getStock());
    }
    public static void removePart(Part part){
        database.deletePart(part.getPartId());
    }
    public static ObservableList<Part> lookupPart(String searchItem) {
        return database.selectAllPartsByName(searchItem);
    }

    //alkatrész frissítés metódus (ModifyPart)
    @SuppressWarnings("unused")
    public static void updatePart(int partNum, Part part){
        database.updatePart(part.getPartId(), part.getName(), part.getPrice(), part.getStock());
    }

    //kölcsön kezelés
    public static ObservableList<Rent> getRentInventory(){ return database.selectAllRents(); }
    public static void addRent(Rent rent){
        int stockrent = rent.getStock();
        database.insertRent(rent.getName(), stockrent, rent.getTimeLeft());
        Product product = database.selectProduct(rent.getName());
        database.updateProductWhenRented(rent.getName(), product.getStock() - stockrent );
    }
    public static void removeRent(Rent rent){
        database.deleteRent(rent.getProductId());
    }
    public static ObservableList<Rent> lookupRent(String searchItem){
        return database.selectAllRentsByName(searchItem);
    }

    //kölcsön frissítés metódus (ExtendRentProduct)
    @SuppressWarnings("unused")
    public static void updateRent(int loanNum, Rent rent){ database.updateRent(rent.getProductId(), rent.getTimeLeft()); }
}
