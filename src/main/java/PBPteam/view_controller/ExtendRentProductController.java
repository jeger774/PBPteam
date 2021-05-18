package PBPteam.view_controller;
import PBPteam.model.Inventory;
import PBPteam.model.Rent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ExtendRentProductController implements Initializable {
    @FXML
    private TextField idTextField;
    @FXML
    private TextField productTextField;
    @FXML
    private TextField inventoryTextField;
    @FXML
    private TextField timeTextField;

    //visszavonás
    @SuppressWarnings("Duplicates")
    @FXML
    void handleCancelExtendRentProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Módosítások visszavonása");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan vissza akarja vonni a módosításokat?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.orElse(null) == ButtonType.OK) {
            Parent rentProductScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene rentProductScene = new Scene(rentProductScreen);
            Stage rentProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            rentProductStage.setScene((rentProductScene));
            rentProductStage.show();
        }
    }

    //mentés
    @FXML
    void handleSaveExtendRentProduct(ActionEvent event) throws IOException {
        Rent rent = new Rent();
        rent.setProductId(Integer.parseInt(idTextField.getText()));
        rent.setName(productTextField.getText());
        rent.setStock(Integer.parseInt(inventoryTextField.getText()));
        rent.setTimeLeft(timeTextField.getText());
        Inventory.updateRent(MainController.getExtendRentIndex(), rent);
        Parent rentProductScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene rentProductScene = new Scene(rentProductScreen);
        Stage rentProductStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        rentProductStage.setScene((rentProductScene));
        rentProductStage.show();
    }

    //megnyitáskor a mezők inicializálása
    @Override
    public void initialize (URL url, ResourceBundle rb){
        Rent rentToBeExtended = Inventory.getRentInventory().get(MainController.getExtendRentIndex());
        idTextField.setText(String.valueOf(rentToBeExtended.getProductId()));
        productTextField.setText(rentToBeExtended.getName());
        inventoryTextField.setText(String.valueOf(rentToBeExtended.getStock()));
        timeTextField.setText(String.valueOf(rentToBeExtended.getTimeLeft()));
    }
}




