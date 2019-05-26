/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chillies_avenue.scenes.commonScenes;

import chillies_avenue.utility.AlertBox;
import db.DbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michelle
 */

public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Connection connection;
    private PreparedStatement myStmt;
    private ResultSet myRs;
    
    
    
    @FXML
    private Button btn_login;
    
    @FXML
    private TextField textFielduserId;
    
    @FXML
    private PasswordField passwordFielduserPassword;
    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private void login(ActionEvent event) throws Exception {
        
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        
        if(textFielduserId.getText().length()!=0 && passwordFielduserPassword.getText().length()!=0 )
        {
            myStmt= connection.prepareStatement("SELECT * FROM users WHERE userId=? AND password =?");
            
            myStmt.setString(1,(textFielduserId.getText()));
            myStmt.setString(2,passwordFielduserPassword.getText());
            
            myRs=myStmt.executeQuery();
                if(myRs.next())
                {
                FXMLLoader Loader=new FXMLLoader(getClass().getResource("/chillies_avenue/scenes/admin/adminPanel.fxml"));  
                stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                root=(Parent) Loader.load();
              
              
                scene =new Scene(root);
                stage.setScene(scene);
                stage.setMaximized(true);
                stage.show();
                }
                else{
                     AlertBox.display("Error","User Not Found");
                }
                  
        }
        else{
            AlertBox.display("Warrning","User ID and Password Should Not Be Empty");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }
    
    
    
}
