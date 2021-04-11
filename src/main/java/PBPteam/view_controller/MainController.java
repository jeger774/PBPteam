package PBPteam.view_controller;
import PBPteam.model.Inventory;
import PBPteam.model.Part;
import PBPteam.model.Product;
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
    private Button loanSearchButton;
    @FXML
    private TextField loanSearchField;
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
    private TableColumn<Part, Integer> productsIDColumn;
    @FXML
    private TableColumn<Part, String> productsNameColumn;
    @FXML
    private TableColumn<Part, Integer> inventoryProductsColumn;
    @FXML
    private TableColumn<Part, Double> priceProductsUnitColumn;
    @FXML
    private TableView<Product> tvLoan;
    @FXML
    private TableColumn<Part, Integer> loanIDColumn;
    @FXML
    private TableColumn<Part, String> loanNameColumn;
    @FXML
    private TableColumn<Part, Integer> countLoanUnitColumn;
    @FXML
    private TableColumn<Part, Double> timeLoanUnitColumn;
    //
    private static int modifyPartIndex;
    private static int modifyProductIndex;
    private static int loanProductIndex;
    public static int getModifyPartIndex(){
        return modifyPartIndex;
    }
    public static int getModifyProductIndex(){
        return modifyProductIndex;
    }
    public static int getLoanProductIndex(){ return loanProductIndex; }
    private static Part modifiedPart;
    private static Product modifiedProduct;
    private static Product loanedProduct;
    //
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
        modifyPartIndex = Inventory.getPartsInventory().indexOf(tempPart);
        Parent addPartsScreen = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene addPartsScene = new Scene(addPartsScreen);
        Stage addPartsStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addPartsStage.setScene((addPartsScene));
        addPartsStage.show();
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
            partsIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partId"));
            partsNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            inventoryPartsColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
            pricePartUnitColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
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
        modifyProductIndex = Inventory.getProductsInventory().indexOf(tempProduct);
        Parent addPartsScreen = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene addPartsScene = new Scene(addPartsScreen);
        Stage addPartsStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addPartsStage.setScene((addPartsScene));
        addPartsStage.show();
        modifiedPart = tvParts.getSelectionModel().getSelectedItem();
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
            productsIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("productId"));
            productsNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
            inventoryProductsColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
            priceProductsUnitColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        }
    }
    @FXML
    public void handleGoToLoanProduct(ActionEvent event)throws IOException{
        Product tempProduct = tvProducts.getSelectionModel().getSelectedItem();
        loanProductIndex = Inventory.getProductsInventory().indexOf(tempProduct);
        Parent loanProductScreen = FXMLLoader.load(getClass().getResource("LoanProduct.fxml"));
        Scene loanProductScene = new Scene(loanProductScreen);
        Stage loanProductStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        loanProductStage.setScene((loanProductScene));
        loanProductStage.show();
    }
    @FXML
    private void handleLoanSearch(ActionEvent event){
        String searchedProduct = loanSearchField.getText();
        int tvLoanIndex;
        if(Inventory.lookupPart(searchedProduct) == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Hiba a keresésben!");
            alert.setHeaderText("Nem található ilyen kölcsönzés.");
            alert.setContentText("A megadott keresőszóra nincs egyező kölcsönzés.");
            alert.showAndWait();
        }
        else{
            tvLoanIndex = Inventory.lookupProduct(searchedProduct);
            Product tempSearchLoan = Inventory.getProductsInventory().get(tvLoanIndex);
            ObservableList<Product> tempObservableList = FXCollections.observableArrayList();
            tempObservableList.add(tempSearchLoan);
            tvLoan.setItems(tempObservableList);
        }
    }
    @FXML
    private void handleExitApplication(ActionEvent event){
        Platform.exit();
    }
    /*egyelőre nincs implementálva adatbázis így üres metódus
    private void handleSaveToDatabase(ActionEvent event) {;}*/
    //megnyitáskor a mezők inicializálása
    @Override
    public void initialize (URL url, ResourceBundle rb){
        //alkatrészek betöltése
        partsIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("partId"));
        partsNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        inventoryPartsColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        pricePartUnitColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        tvParts.setItems(Inventory.getPartsInventory());
        //termékek betöltése
        productsIDColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("productId"));
        productsNameColumn.setCellValueFactory(new PropertyValueFactory<Part, String>("name"));
        inventoryProductsColumn.setCellValueFactory(new PropertyValueFactory<Part, Integer>("stock"));
        priceProductsUnitColumn.setCellValueFactory(new PropertyValueFactory<Part, Double>("price"));
        tvProducts.setItems(Inventory.getProductsInventory());
    }
}

