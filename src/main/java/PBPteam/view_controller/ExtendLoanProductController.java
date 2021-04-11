package PBPteam.view_controller;
import PBPteam.model.Inventory;
import PBPteam.model.Loan;
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

public class ExtendLoanProductController implements Initializable {
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
    void handleCancelExtendLoanProduct(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Módosítások visszavonása");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan vissza akarja vonni a módosításokat?");
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
    void handleSaveExtendLoanProduct(ActionEvent event) throws IOException {
        Loan loan = new Loan();
        loan.setProductId(Integer.parseInt(idTextField.getText()));
        loan.setName(productTextField.getText());
        loan.setStock(Integer.parseInt(inventoryTextField.getText()));
        loan.setTimeLeft(timeTextField.getText());
        Inventory.updateLoan(MainController.getExtendLoanIndex(), loan);
        Parent loanProductScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene loanProductScene = new Scene(loanProductScreen);
        Stage loanProductStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        loanProductStage.setScene((loanProductScene));
        loanProductStage.show();
    }
    //megnyitáskor a mezők inicializálása
    @Override
    public void initialize (URL url, ResourceBundle rb){
        Loan loanToBeExtended = Inventory.getLoansInventory().get(MainController.getExtendLoanIndex());
        idTextField.setText(String.valueOf(loanToBeExtended.getProductId()));
        productTextField.setText(loanToBeExtended.getName());
        inventoryTextField.setText(String.valueOf(loanToBeExtended.getStock()));
        timeTextField.setText(String.valueOf(loanToBeExtended.getTimeLeft()));
    }
}




