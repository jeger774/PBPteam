package PBPteam.view_controller;
import PBPteam.model.Inventory;
import PBPteam.model.Part;
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

public class ModifyPartController implements Initializable {
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
    void handleModifyPartCancel(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initModality(Modality.NONE);
        alert.setTitle("Visszavonás megerősítése");
        alert.setHeaderText("Megerősítés");
        alert.setContentText("Biztosan vissza akarja vonni a módosításokat?");
        Optional<ButtonType> outcome = alert.showAndWait();
        if(outcome.orElse(null) == ButtonType.OK) {
            Parent modifyPartsScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene modifyPartsScene = new Scene(modifyPartsScreen);
            Stage modifyPartsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            modifyPartsStage.setScene((modifyPartsScene));
            modifyPartsStage.show();
        }
    }

    //mentés
    @FXML
    void handleModifyPartSave(ActionEvent event) throws IOException {
        Part part = new Part();
        part.setPartId(Integer.parseInt(idTextField.getText()));
        part.setName(nameTextField.getText());
        part.setStock(Integer.parseInt(inventoryTextField.getText()));
        part.setPrice(Double.parseDouble(priceTextField.getText()));
        Inventory.updatePart(MainController.getModifyPartIndex(), part);
        Parent modifyPartsScreen = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene modifyPartsScene = new Scene(modifyPartsScreen);
        Stage modifyPartsStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        modifyPartsStage.setScene((modifyPartsScene));
        modifyPartsStage.show();
    }

    //megnyitáskor a mezők inicializálása
    @Override
    public void initialize (URL url, ResourceBundle rb){
        Part partToBeModified = Inventory.getPartsInventory().get(MainController.getModifyPartIndex());
        idTextField.setText(String.valueOf(partToBeModified.getPartId()));
        nameTextField.setText(partToBeModified.getName());
        inventoryTextField.setText(String.valueOf(partToBeModified.getStock()));
        priceTextField.setText(String.valueOf(partToBeModified.getPrice()));
    }
}
