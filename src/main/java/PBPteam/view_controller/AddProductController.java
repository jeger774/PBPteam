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

public class AddProductController implements Initializable {
    @FXML
    public AnchorPane addProductId;
    @FXML
    public Button saveButton;
    @FXML
    public Button cancelButton;
    @FXML
    private TextField addProductName;
    @FXML
    private TextField addInventory;
    @FXML
    private TextField addPrice;

    //visszavonás
    @SuppressWarnings("Duplicates")
    @FXML
    void handleCancelAddProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Visszavonás megerősítése");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan visszavonja a hozzáadást?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.orElse(null) == ButtonType.OK) {
            Parent addProductsScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene addProductsScene = new Scene(addProductsScreen);
            Stage addProductsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            addProductsStage.setScene((addProductsScene));
            addProductsStage.show();
        }
    }

    //mentés
    @FXML
    void handleSaveAddProduct(ActionEvent event) throws IOException {
        Product product = new Product();
        product.setName(addProductName.getText());
        product.setStock(Integer.parseInt(addInventory.getText()));
        product.setPrice(Double.parseDouble(addPrice.getText()));
        Inventory.addProduct(product);
        Parent addProductsScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene addProductsScene = new Scene(addProductsScreen);
        Stage addProductsStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        addProductsStage.setScene((addProductsScene));
        addProductsStage.show();
    }

    //megnyitáskor a mezők inicializálása
    @Override
    public void initialize (URL url, ResourceBundle rb){}
}
