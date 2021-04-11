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

public class LoanProductController implements Initializable {
    @FXML
    private AnchorPane loanProductId;
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
    void handleCancelLoanProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Kölcsönzés visszavonása");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan vissza akarja vonni a kölcsönzést?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.get() == ButtonType.OK) {
            Parent loanProductScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene loanProductScene = new Scene(loanProductScreen);
            Stage loanProductStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            loanProductStage.setScene((loanProductScene));
            loanProductStage.show();
        }
    }
    @FXML
    void handleSaveLoanProduct(ActionEvent event) throws IOException {
        Product product = new Product();
        product.setProductId(Integer.parseInt(idTextField.getText()));
        product.setName(productTextField.getText());
        product.setStock(Integer.parseInt(inventoryTextField.getText()));
        product.setPrice(Double.parseDouble(timeTextField.getText()));
        Inventory.updateProduct(MainController.getModifyProductIndex(), product);
        Parent loanProductScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene loanProductScene = new Scene(loanProductScreen);
        Stage loanProductStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        loanProductStage.setScene((loanProductScene));
        loanProductStage.show();
    }
    //megnyitáskor a mezők inicializálása
    @Override
    public void initialize (URL url, ResourceBundle rb){
        Product productToBeLoaned = Inventory.getProductsInventory().get(MainController.getLoanProductIndex());
        idTextField.setText(String.valueOf(productToBeLoaned.getProductId()));
        productTextField.setText(productToBeLoaned.getName());
        inventoryTextField.setText(String.valueOf(productToBeLoaned.getStock()));
        timeTextField.setText("");
    }
}




