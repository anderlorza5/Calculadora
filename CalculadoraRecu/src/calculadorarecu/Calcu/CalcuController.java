/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadorarecu.Calcu;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * FXML Controller class
 *
 * @author ander
 */
public class CalcuController implements Initializable {

    @FXML
    private TextField resultado;
    private ArrayList<String> resultados= new ArrayList<String>();
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private String getButtonValue(ActionEvent event){
        Button button = (Button)event.getSource();
        return button.getText();
        
    }
    //funcion para recoger el ultomo caracter de una cadena, hacemos un truco por si la cadena no tiene ningun caracter ya que si no, da fallo al intentar meter el primer numero en la calculadora 
    private char ultimoChar(String cadena){
        char lastChar;
        int longitud=cadena.length();
        if (longitud==0){
            lastChar=' ';
        }else{
            lastChar = cadena.charAt(cadena.length() - 1);
        }
       
        return lastChar;
    }
    private char dosUltimos(String cadena){
        char lastChar;
        int longitud=cadena.length();
        if (longitud<2){
            lastChar=' ';
        }else{
            lastChar = cadena.charAt(cadena.length() - 2);
        }
       
        return lastChar;
    }

    @FXML
    private void añadirNumero(ActionEvent event) {
        String cadena= resultado.getText();
        char ultimo= ultimoChar(cadena);
        String number = getButtonValue(event);
        if (ultimo==' ' && (number.equals("*") || number.equals("/"))){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("la operacion no esta disponible");
            alert.showAndWait();
            return;
        }
       
        if(number.equals("+") ||number.equals("-") ||number.equals("*")||number.equals("/") ){
           
            if (ultimo=='*'){
                if (number.equals("-")){
                    String textResultado = resultado.getText();
                    resultado.setText(textResultado+number);
                    
                    return;
                }
                else{
                    String textResultado = resultado.getText();
                    textResultado= textResultado.substring(0, textResultado.length()-1);
                    resultado.setText(textResultado+number);
                    return;
                }
                
                
            }
            if (ultimo=='/'){
                if (number.equals("-")){
                    String textResultado = resultado.getText();
                    resultado.setText(textResultado+number); 
                    return;
                }
                else{
                    String textResultado = resultado.getText();
                textResultado= textResultado.substring(0, textResultado.length()-1);
                resultado.setText(textResultado+number);
                return;
                }
            }
            if (ultimo=='-'){
                String textResultado = resultado.getText();
                textResultado= textResultado.substring(0, textResultado.length()-1);
                resultado.setText(textResultado+number);
                return;
            }if (ultimo=='+'){
                String textResultado = resultado.getText();
                textResultado= textResultado.substring(0, textResultado.length()-1);
                resultado.setText(textResultado+number);
                return;
            }
            else{
                String textResultado = resultado.getText();
                resultado.setText(textResultado+number);
                return;
            }
           
                
            
        }else{
          String textResultado = resultado.getText();
          resultado.setText(textResultado+number);  
          
          return;
        }
        
    }

    

    @FXML
    private void igual(ActionEvent event) throws ScriptException {
        String ecuacion= resultado.getText();
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        
        String salida =engine.eval(ecuacion).toString();
        String operacion =ecuacion+"= " +salida;
        resultados.add(operacion);
        resultado.clear();
        resultado.setText(salida);
              
        
       
    }

    @FXML
    private void limpiar(ActionEvent event) {        
        resultado.clear();      
    }

    @FXML
    private void abrirHistorial(ActionEvent event) {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.WINDOW_MODAL);
                                
        VBox dialogVbox = new VBox(20);
        for (int i=0; i<resultados.size(); i++){
            dialogVbox.getChildren().add(new Text(resultados.get(i)));
        }
                              
        Scene dialogScene = new Scene(dialogVbox, 350, 150);
        dialog.setScene(dialogScene);
        dialog.show();
        return;
        
    }



    
}
