package PBPteam.view_controller;
import PBPteam.model.Inventory;
import PBPteam.model.Part;
import PBPteam.model.Product;
import PBPteam.model.Rent;
import javafx.application.Platform;
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
    public AnchorPane mainScreenId;
    @FXML
    private TextField partsSearchField;
    @FXML
    private TextField productsSearchField;
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

    //alkatrészek
    @SuppressWarnings("unused")
    @FXML
    private void handlePartSearch(ActionEvent event){
        String searchedPart = partsSearchField.getText();
        if(searchedPart.equals("")) { tvParts.setItems(Inventory.getPartsInventory()); }
        else tvParts.setItems(Inventory.lookupPart(searchedPart));
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

    @SuppressWarnings({"unused","Duplicates"})
    @FXML
    private void handleDeletePart(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan törölni szeretné?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.orElse(null) == ButtonType.OK) {
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

    //termékek
    @SuppressWarnings("unused")
    @FXML
    private void handleProductSearch(ActionEvent event){
        String searchedProduct = productsSearchField.getText();
        if(searchedProduct.equals("")) { tvProducts.setItems(Inventory.getProductsInventory()); }
        else tvProducts.setItems(Inventory.lookupProduct(searchedProduct));
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

    @SuppressWarnings({"unused","Duplicates"})
    @FXML
    private void handleDeleteProduct(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan törölni szeretné?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.orElse(null) == ButtonType.OK) {
            Inventory.removeProduct(tvProducts.getSelectionModel().getSelectedItem());
            productsIDColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
            productsNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            inventoryProductsColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
            priceProductsUnitColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
            tvProducts.setItems(Inventory.getProductsInventory());
            tvRent.setItems(Inventory.getRentInventory());
        }
    }

    //kölcsönzések
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

    @SuppressWarnings("unused")
    @FXML
    private void handleRentSearch(ActionEvent event){
        String searchedRent = rentSearchField.getText();
        if(searchedRent.equals("")) { tvRent.setItems(Inventory.getRentInventory()); }
        else tvRent.setItems(Inventory.lookupRent(searchedRent));
    }

    @SuppressWarnings({"unused","Duplicates"})
    @FXML
    private void handleDeleteRent(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Törlés megerősítése");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan törölni szeretné?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.orElse(null) == ButtonType.OK) {
            Inventory.removeRent(tvRent.getSelectionModel().getSelectedItem());
            rentIDColumn.setCellValueFactory(new PropertyValueFactory<>("productId"));
            rentNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            countRentUnitColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
            timeRentUnitColumn.setCellValueFactory(new PropertyValueFactory<>("timeLeft"));
            tvRent.setItems(Inventory.getRentInventory());
        }
    }

    //kilépés
    @SuppressWarnings("unused")
    @FXML
    private void handleExitApplication(ActionEvent event){
        Platform.exit();
    }

    //megnyitáskor a mezők inicializálása
    @SuppressWarnings("Duplicates")
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

