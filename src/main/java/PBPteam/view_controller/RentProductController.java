package PBPteam.view_controller;
import PBPteam.model.Inventory;
import PBPteam.model.Product;
import PBPteam.model.Rent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class RentProductController implements Initializable {
    @FXML
    private AnchorPane rentProductId;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField productTextField;
    @FXML
    private TextField inventoryTextField;
    @FXML
    private TextField timeTextField;

    @FXML
    void handleCancelRentProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Kölcsönzés visszavonása");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan vissza akarja vonni a kölcsönzést?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.get() == ButtonType.OK) {
            Parent rentProductScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene rentProductScene = new Scene(rentProductScreen);
            Stage rentProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            rentProductStage.setScene((rentProductScene));
            rentProductStage.show();
        }
    }

    @FXML
    void handleSaveRentProduct(ActionEvent event) throws IOException {
        Rent rent = new Rent();
        rent.setProductId(Integer.parseInt(idTextField.getText()));
        rent.setName(productTextField.getText());
        rent.setStock(Integer.parseInt(inventoryTextField.getText()));
        rent.setTimeLeft(timeTextField.getText());
        Inventory.addRent(rent);
        Parent rentProductScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene rentProductScene = new Scene(rentProductScreen);
        Stage rentProductStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        rentProductStage.setScene((rentProductScene));
        rentProductStage.show();
    }

    //megnyitáskor a mezők inicializálása
    @Override
    public void initialize (URL url, ResourceBundle rb){
        System.out.println(Inventory.getProductsInventory());
        System.out.println(MainController.getRentProductIndex());
        Product productToBeRented = Inventory.getProductsInventory().get(MainController.getRentProductIndex());
        idTextField.setText(String.valueOf(productToBeRented.getProductId()));
        productTextField.setText(productToBeRented.getName());
        inventoryTextField.setText(String.valueOf(productToBeRented.getStock()));
        timeTextField.setText("7 nap");
    }
}




