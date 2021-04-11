package PBPteam.model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> partsInventory = FXCollections.observableArrayList();
    private static ObservableList<Product> productsInventory = FXCollections.observableArrayList();
    //termék kezelés
    public static ObservableList<Product> getProductsInventory(){
        return productsInventory;
    }
    public static void addProduct(Product product){
        productsInventory.add(product);
    }
    public static void removeProduct(Product product){
        productsInventory.remove(product);
    }
    public static int lookupProduct(String searchItem){
        boolean isFound = false;
        int index = 0;
        if(isInteger(searchItem) == true){
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
        if(isFound = true){
            return index;
        }
        else {
            System.out.println("Nem található termék.");
            return -1;
        }
    }
    //termék frissítés metódus (ModifyProduct)
    public static void updateProduct(int productNum, Product product){
        productsInventory.set(productNum, product);
    }
    //alkatrész kezelés
    public static ObservableList<Part> getPartsInventory(){
        return partsInventory;
    }
    public static void addParts(Part part){
        partsInventory.add(part);
    }
    public static void removePart(Part part){
        partsInventory.remove(part);
    }
    public static int lookupPart(String searchItem) {
        boolean isFound = false;
        int index = 0;
        if(isInteger(searchItem) == true){
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
        if(isFound = true){
            return index;
        }
        else {
            System.out.println("Nem található alkatrész.");
            return -1;
        }
    }
    //alkatrész frissítés metódus (ModifyPart)
    public static void updatePart(int partNum, Part part){
        partsInventory.set(partNum, part);
    }
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
