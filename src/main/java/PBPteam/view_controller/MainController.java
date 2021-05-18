package PBPteam.view_controller;
import PBPteam.model.Inventory;
import PBPteam.model.Part;
import PBPteam.model.Product;
import PBPteam.model.Rent;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane mainScreenId;
    @FXML
    private Button partSearchButton;
    @FXML
    private TextField partsSearchField;
    @FXML
    private Button productSearchButton;
    @FXML
    private TextField productsSearchField;
    @FXML
    private Button rentSearchButton;
    @FXML
    private TextField rentSearchField;
    @FXML
    private TableView<Part> tvParts;
    @FXML
    private TableColumn<Part, Integer> partsIDColumn;
    @FXML
    private TableColumn<Part, String> partsNameColumn;
    @FXML
    private TableColumn<Part, Integer> inventoryPartsColumn;
    @FXML
    private TableColumn<Part, Double> pricePartUnitColumn;
    @FXML
    private TableView<Product> tvProducts;
    @FXML
    private TableColumn<Product, Integer> productsIDColumn;
    @FXML
    private TableColumn<Product, String> productsNameColumn;
    @FXML
    private TableColumn<Product, Integer> inventoryProductsColumn;
    @FXML
    private TableColumn<Product, Double> priceProductsUnitColumn;
    @FXML
    private TableView<Rent> tvRent;
    @FXML
    private TableColumn<Rent, Integer> rentIDColumn;
    @FXML
    private TableColumn<Rent, String> rentNameColumn;
    @FXML
    private TableColumn<Rent, Integer> countRentUnitColumn;
    @FXML
    private TableColumn<Rent, String> timeRentUnitColumn;

    private static int modifyPartIndex;
    private static int modifyProductIndex;
    private static int extendRentIndex;
    private static int rentProductIndex;

    public static int getModifyPartIndex(){
        return modifyPartIndex;
    }
    public static int getModifyProductIndex(){
        return modifyProductIndex;
    }
    public static int getExtendRentIndex(){
        return extendRentIndex;
    }
    public static int getRentProductIndex(){ return rentProductIndex; }

    @FXML
    private void handlePartSearch(ActionEvent event){
        String searchedPart = partsSearchField.getText();
        int tvPartIndex;
        if(Inventory.lookupPart(searchedPart) == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hiba a keresésben!");
            alert.setHeaderText("Nem található ilyen alkatrész.");
            alert.setContentText("A megadott keresőszóra nincs egyező alkatrész.");
            alert.showAndWait();
        }
        else if(searchedPart.equals("")){
            tvParts.setItems(Inventory.getPartsInventory());
        }
        else{
            tvPartIndex = Inventory.lookupPart(searchedPart);
            Part tempSearchPart = Inventory.getPartsInventory().get(tvPartIndex);
            ObservableList<Part> tempObservableList = FXCollections.observableArrayList();
            tempObservableList.add(tempSearchPart);
            tvParts.setItems(tempObservableList);
        }
    }

    @FXML
    public void handleGoToModifyParts(ActionEvent event)throws IOException{
        Part tempPart = tvParts.getSelectionModel().getSelectedItem();
        ObservableList<Part> parts = Inventory.getPartsInventory();
        modifyPartIndex = 0;
        for (modifyPartIndex = 0; modifyPartIndex < parts.size(); ++modifyPartIndex ){
            if (parts.get(modifyPartIndex).getPartId() == tempPart.getPartId()){
                break;
            }
        }
        Parent modifyPartsScreen = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene modifyPartsScene = new Scene(modifyPartsScreen);
        Stage modifyPartsStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        modifyPartsStage.setScene((modifyPartsScene));
        modifyPartsStage.show();
    }

    @FXML
    private void handleDeletePart(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan törölni szeretné?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.get() == ButtonType.OK) {
            Inventory.removePart(tvParts.getSelectionModel().getSelectedItem());
            partsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
            partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            inventoryPartsColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
            pricePartUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            tvParts.setItems(Inventory.getPartsInventory());
        }
    }

    @FXML
    private void handleGoToAddParts(ActionEvent event) throws IOException{
        Parent addPartsScreen = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene addPartsScene = new Scene(addPartsScreen);
        Stage addPartsStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addPartsStage.setScene((addPartsScene));
        addPartsStage.show();
    }

    @FXML
    private void handleProductSearch(ActionEvent event){
        String searchedProduct = productsSearchField.getText();
        int tvProductIndex;
        if(Inventory.lookupProduct(searchedProduct) == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hiba a keresésben!");
            alert.setHeaderText("Nem található ilyen termék.");
            alert.setContentText("A megadott keresőszóra nincs egyező termék.");
            alert.showAndWait();
        }
        else if(searchedProduct.equals("")){
            tvProducts.setItems(Inventory.getProductsInventory());
        }
        else{
            tvProductIndex = Inventory.lookupProduct(searchedProduct);
            Product tempSearchProduct = Inventory.getProductsInventory().get(tvProductIndex);
            ObservableList<Product> tempObservableList = FXCollections.observableArrayList();
            tempObservableList.add(tempSearchProduct);
            tvProducts.setItems(tempObservableList);
        }
    }

    @FXML
    private void handleGoToModifyProducts(ActionEvent event)throws IOException{
        Product tempProduct = tvProducts.getSelectionModel().getSelectedItem();
        ObservableList<Product> products = Inventory.getProductsInventory();
        modifyProductIndex = 0;
        for (modifyProductIndex = 0; modifyProductIndex < products.size(); ++modifyProductIndex ){
            if (products.get(modifyProductIndex).getProductId() == tempProduct.getProductId()){
                break;
            }
        }
        Parent modifyProductScreen = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene modifyProductScene = new Scene(modifyProductScreen);
        Stage modifyProductStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        modifyProductStage.setScene((modifyProductScene));
        modifyProductStage.show();
    }

    @FXML
    private void handleGoToAddProducts (ActionEvent event)throws IOException{
        Parent addPartsScreen = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addPartsScene = new Scene(addPartsScreen);
        Stage addPartsStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addPartsStage.setScene((addPartsScene));
        addPartsStage.show();
    }

    @FXML
    private void handleDeleteProduct(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan törölni szeretné?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.get() == ButtonType.OK) {
            Inventory.removeProduct(tvProducts.getSelectionModel().getSelectedItem());
            productsIDColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
            productsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            inventoryProductsColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
            priceProductsUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            tvProducts.setItems(Inventory.getProductsInventory());
        }
    }

    @FXML
    public void handleGoToRentProduct(ActionEvent event)throws IOException{
        Product tempProduct = tvProducts.getSelectionModel().getSelectedItem();
        System.out.println(tempProduct);
        ObservableList<Product> products = Inventory.getProductsInventory();
        rentProductIndex = 0;
        for (rentProductIndex = 0; rentProductIndex < products.size(); ++rentProductIndex ){
            if (products.get(rentProductIndex).getProductId() == tempProduct.getProductId()){
                break;
            }
        }
        Parent rentProductScreen = FXMLLoader.load(getClass().getResource("RentProduct.fxml"));
        Scene rentProductScene = new Scene(rentProductScreen);
        Stage rentProductStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        rentProductStage.setScene((rentProductScene));
        rentProductStage.show();
    }

    @FXML
    private void handleGoToExtendRent(ActionEvent event)throws IOException{
        Rent tempRent = tvRent.getSelectionModel().getSelectedItem();
        //extendRentIndex = Inventory.getRentInventory().indexOf(tempRent);
        ObservableList<Rent> rents = Inventory.getRentInventory();
        extendRentIndex = 0;
        for (extendRentIndex = 0; extendRentIndex < rents.size(); ++extendRentIndex ){
            if (rents.get(extendRentIndex).getProductId() == tempRent.getProductId()){
                break;
            }
        }
        Parent extendRentProductScreen = FXMLLoader.load(getClass().getResource("ExtendRentProduct.fxml"));
        Scene extendRentProductScene = new Scene(extendRentProductScreen);
        Stage extendRentProductStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        extendRentProductStage.setScene((extendRentProductScene));
        extendRentProductStage.show();
    }

    @FXML
    private void handleRentSearch(ActionEvent event){
        String searchedRent = rentSearchField.getText();
        int tvRentIndex;
        if(Inventory.lookupRent(searchedRent) == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hiba a keresésben!");
            alert.setHeaderText("Nem található ilyen kölcsönzés.");
            alert.setContentText("A megadott keresőszóra nincs egyező kölcsönzés.");
            alert.showAndWait();
        }
        else if(searchedRent.equals("")){
            tvRent.setItems(Inventory.getRentInventory());
        }
        else{
            tvRentIndex = Inventory.lookupProduct(searchedRent);
            Rent tempSearchRent = Inventory.getRentInventory().get(tvRentIndex);
            ObservableList<Rent> tempObservableList = FXCollections.observableArrayList();
            tempObservableList.add(tempSearchRent);
            tvRent.setItems(tempObservableList);
        }
    }

    @FXML
    private void handleDeleteRent(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan törölni szeretné?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.get() == ButtonType.OK) {
            Inventory.removeRent(tvRent.getSelectionModel().getSelectedItem());
            rentIDColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
            rentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            countRentUnitColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
            timeRentUnitColumn.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
            tvRent.setItems(Inventory.getRentInventory());
        }
    }

    @FXML
    private void handleExitApplication(ActionEvent event){
        Platform.exit();
    }

    //megnyitáskor a mezők inicializálása
    @Override
    public void initialize (URL url, ResourceBundle rb){
        //termékek betöltése
        productsIDColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryProductsColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceProductsUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tvProducts.setItems(Inventory.getProductsInventory());

        //alkatrészek betöltése
        partsIDColumn.setCellValueFactory(new PropertyValueFactory<>("partId"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        inventoryPartsColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        pricePartUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        tvParts.setItems(Inventory.getPartsInventory());

        //kölcsönzések betöltése
        rentIDColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
        rentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countRentUnitColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        timeRentUnitColumn.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
        tvRent.setItems(Inventory.getRentInventory());
    }
}

