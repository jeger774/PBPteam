package PBPteam.view_controller;
import PBPteam.model.Inventory;
import PBPteam.model.Product;
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

public class ModifyProductController implements Initializable {
    @FXML
    private TextField idTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField inventoryTextField;
    @FXML
    private TextField priceTextField;

    //visszavonás
    @SuppressWarnings("Duplicates")
    @FXML
    void handleCancelModifyProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Visszavonás megerősítése");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan vissza akarja vonni a módosításokat?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.orElse(null) == ButtonType.OK) {
            Parent modifyProductScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene addPartsScene = new Scene(modifyProductScreen);
            Stage addPartsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            addPartsStage.setScene((addPartsScene));
            addPartsStage.show();
        }
    }

    //mentés
    @FXML
    void handleSaveModifyProduct(ActionEvent event) throws IOException {
        Product product = new Product();
        product.setProductId(Integer.parseInt(idTextField.getText()));
        product.setName(nameTextField.getText());
        product.setStock(Integer.parseInt(inventoryTextField.getText()));
        product.setPrice(Double.parseDouble(priceTextField.getText()));
        Inventory.updateProduct(MainController.getModifyProductIndex(), product);
        Parent modifyProductScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene modifyProductScene = new Scene(modifyProductScreen);
        Stage modifyProductStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        modifyProductStage.setScene((modifyProductScene));
        modifyProductStage.show();
    }

    //megnyitáskor a mezők inicializálása
    @Override
    public void initialize (URL url, ResourceBundle rb){
        Product productToBeModified = Inventory.getProductsInventory().get(MainController.getModifyProductIndex());
        idTextField.setText(String.valueOf(productToBeModified.getProductId()));
        nameTextField.setText(productToBeModified.getName());
        inventoryTextField.setText(String.valueOf(productToBeModified.getStock()));
        priceTextField.setText(String.valueOf(productToBeModified.getPrice()));
    }
}




