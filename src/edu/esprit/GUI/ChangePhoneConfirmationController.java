/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.GUI;

import static edu.esprit.GUI.ValidateUserController.userToConfirm;
import edu.esprit.utils.CodeGeneration;
import edu.esprit.utils.SendSMS;
import edu.esprit.utils.ServiceMail;
import edu.esprit.utils.ServiceManager;
import edu.esprit.utils.UserManager;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.mail.MessagingException;

/**
 * FXML Controller class
 *
 * @author Ayoub
 */
public class ChangePhoneConfirmationController implements Initializable {

    @FXML
    private TextField confirmationTxt;
    private String code;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        code = CodeGeneration.usingMathClass();
        
            code = CodeGeneration.usingMathClass();
            SendSMS.sendSms(code, ChangePhoneController.phoneToChange);
    }    

    @FXML
    private void onValidateCode(MouseEvent event) throws IOException {
        
       if (confirmationTxt.getText().equals(code)) {
                UserManager.getUser().setPhone(ChangePhoneController.phoneToChange);
                ServiceManager.getInstance().getUserService().edit(UserManager.getUser());
                Stage x = (Stage) confirmationTxt.getScene().getWindow();
                x.close();
                AnchorPane root = FXMLLoader.load(getClass().getResource("ModifyUserDetails.fxml"));
                    HomeController.instance.setContent(root);
                
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Code invalid !!");
            alert.showAndWait();
            confirmationTxt.requestFocus();
        } 
    }
    
}
