package PBPteam.model;
import PBPteam.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    public static Database database = new Database();

    //product
    public static ObservableList<Product> getProductsInventory(){
        return database.selectAllProducts();
    }
    public static void addProduct(Product product){ database.insertProduct(product.getName(), product.getPrice(), product.getStock()); }
    public static void removeProduct(Product product){
        database.deleteProduct(product.getProductId());
    }

    //listák
    private static ObservableList<Part> partsInventory = FXCollections.observableArrayList();
    private static ObservableList<Product> productsInventory = FXCollections.observableArrayList();
    private static ObservableList<Rent> rentInventory = FXCollections.observableArrayList();

    //termék kezelés
    public static int lookupProduct(String searchItem){
        boolean isFound = false;
        int index = 0;
        if(isInteger(searchItem)){
            for(int i =0; i<productsInventory.size(); i++){
                if(Integer.parseInt(searchItem) == productsInventory.get(i).getProductId()){
                    index = i;
                    isFound = true;
                }
            }
        }
        else{
            for(int i =0; i<productsInventory.size(); i++) {
                if (searchItem.equals(productsInventory.get(i).getName()))
                    index = i;
                isFound = true;
            }
        }
        if(isFound){
            return index;
        }
        else {
            System.out.println("Nem található termék.");
            return -1;
        }
    }

    //termék frissítés metódus (ModifyProduct)
    public static void updateProduct(int productNum, Product product){
        database.updateProduct(product.getProductId(), product.getName(), product.getPrice(), product.getStock());
    }

    //part
    public static ObservableList<Part> getPartsInventory(){
        return database.selectAllPart();
    }
    public static void addParts(Part part){
        database.insertPart(part.getName(), part.getPrice(), part.getStock());
    }
    public static void removePart(Part part){
        database.deletePart(part.getPartId());
    }

    //alkatrész kezelés
    public static int lookupPart(String searchItem) {
        boolean isFound = false;
        int index = 0;
        if(isInteger(searchItem)){
            for(int i =0; i<partsInventory.size(); i++){
                if(Integer.parseInt(searchItem) == partsInventory.get(i).getPartId()){
                    index = i;
                    isFound = true;
                }
            }
        }
        else{
            for(int i =0; i<partsInventory.size(); i++) {
                if (searchItem.equals(partsInventory.get(i).getName()))
                    index = i;
                isFound = true;
            }
        }
        if(isFound){
            return index;
        }
        else {
            System.out.println("Nem található alkatrész.");
            return -1;
        }
    }

    //alkatrész frissítés metódus (ModifyPart)
    public static void updatePart(int partNum, Part part){
        database.updatePart(part.getPartId(), part.getName(), part.getPrice(), part.getStock());
    }

    //kölcsön kezelés
    public static ObservableList<Rent> getRentInventory(){ return database.selectAllRents(); }
    public static void addRent(Rent rent){
        database.insertRent(rent.getName(), rent.getStock(), rent.getTimeLeft());
    }
    public static void removeRent(Rent rent){
        database.deleteRent(rent.getProductId());
    }
    public static int lookupRent(String searchItem){
        boolean isFound = false;
        int index = 0;
        if(isInteger(searchItem)){
            for(int i = 0; i< rentInventory.size(); i++){
                if(Integer.parseInt(searchItem) == rentInventory.get(i).getProductId()){
                    index = i;
                    isFound = true;
                }
            }
        }
        else{
            for(int i = 0; i< rentInventory.size(); i++) {
                if (searchItem.equals(rentInventory.get(i).getName()))
                    index = i;
                isFound = true;
            }
        }
        if(isFound){
            return index;
        }
        else {
            System.out.println("Nem található termék.");
            return -1;
        }
    }

    //kölcsön frissítés metódus (ExtendRentProduct)
    public static void updateRent(int loanNum, Rent rent){
        database.updateRent(rent.getProductId(), rent.getTimeLeft()); }

    public static boolean isInteger(String inputItem){
        try{
            Integer.parseInt(inputItem);
            return true;
        }
        catch (Exception exception){
            return false;
        }
    }
}
