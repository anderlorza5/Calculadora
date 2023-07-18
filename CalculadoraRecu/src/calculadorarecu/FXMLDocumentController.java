/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadorarecu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ander
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void abrirCalculadora(ActionEvent event) {
        try {
            
            FXMLLoader fxmlLoader = new FXMLLoader(CalculadoraRecu.class.getResource("Calcu/Calcu.fxml"));
            //P2P_FileSare.class.get.Resource("AboutMain/FXML_MenuItem_About.fxml")
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("About");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
        }
    }
    
}
