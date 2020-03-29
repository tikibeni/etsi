package planapp.ui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class PlanAppUi extends Application {
    private Scene plan;
    private Scene login;
    private Scene register;
    
    @Override
    public void start(Stage stg) throws Exception {   
        // Login components:
        GridPane logGrid = new GridPane();
        
        logGrid.setAlignment(Pos.CENTER);
        logGrid.setHgap(10);
        logGrid.setVgap(10);
        logGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text logNotification = new Text();
        Text logTitle = new Text();
        
        logTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        logTitle.setText("PlanApp v. 0.1");    
        
        TextField logUsername = new TextField();
        Label logUserLabel = new Label("Username:");
        Label registerInfo = new Label("Don't have an account yet?");
        Button logButton = new Button("Login");
        Hyperlink registerLink = new Hyperlink("Register");
        
        logButton.setOnAction((ActionEvent push) -> {
            logNotification.setText("");
            if(logUsername.getText().isEmpty()){
                logNotification.setFill(Color.FIREBRICK);
                logNotification.setText("Please enter the username");
            }
            // If username doesn't match any existing one from DB:
            // -> "Wrong username"
            // else:
            
            stg.setScene(plan);
        });
        
        registerLink.setOnAction((ActionEvent push) -> {
            stg.setScene(register);
            logNotification.setText("");
            // username.clear();
        });
        
        logGrid.add(logTitle, 0,1);
        logGrid.add(logUserLabel, 0,2);
        logGrid.add(logUsername, 1,2);
        logGrid.add(logButton, 1,3);
        logGrid.add(registerInfo, 0,4);
        logGrid.add(registerLink, 1,4);
        logGrid.add(logNotification, 0,5);
        
        login = new Scene(logGrid, 500, 200);
        
        // Register components:
        GridPane regGrid = new GridPane();
        regGrid.setAlignment(Pos.CENTER);
        regGrid.setHgap(10);
        regGrid.setVgap(10);
        regGrid.setPadding(new Insets(25, 25, 25, 25));

        Text regTitle = new Text();
        regTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        regTitle.setText("Create an account");
        
        Text regNotification = new Text();
        Text regNotification1 = new Text();
        
        Label regUsernameLabel = new Label("Username:");
        Label regNameLabel = new Label("Name:");
        TextField regUsername = new TextField();
        TextField regName = new TextField();
        Button regButton = new Button("Register");
        
        regButton.setOnAction((ActionEvent push) -> {
            regNotification.setText("");
            regNotification1.setText("");
            boolean er1 = false;
            boolean er2 = false;
            
            if(regUsername.getText().length() < 3 || regUsername.getText().length() > 12){
                regNotification.setFill(Color.FIREBRICK);
                regNotification.setText("Username length must be between 3-12");
                er1 = true;
            } 
            if(regName.getText().length() < 4 || regName.getText().length() > 30){
                regNotification1.setFill(Color.FIREBRICK);
                regNotification1.setText("Name length must be between 4-30");
                er2 = true;
            }
            
            if(!er2 && !er1){
                // Insert new account to database
                logNotification.setFill(Color.GREEN);
                regNotification.setText("");
                logNotification.setText("Registeration successful!");
                stg.setScene(login);
            }
            
            // If username already exists:
            // -> "Username must be unique"
            // Would be great if it'd validate username while writing (with 1 second delay && when over 2chars)
            
        });
                
        regGrid.add(regTitle, 0, 1);
        regGrid.add(regUsernameLabel, 0, 2);
        regGrid.add(regUsername, 1, 2);
        regGrid.add(regNameLabel, 0, 3);
        regGrid.add(regName, 1, 3);
        regGrid.add(regButton, 1, 4);
        regGrid.add(regNotification, 1, 5);
        regGrid.add(regNotification1, 1, 6);
        
        register = new Scene(regGrid, 500, 200);
                
        // Run application:
        stg.setTitle("PlanApp");
        stg.setScene(login);
        stg.show();       
        
    }
    
    @Override
    public void stop(){
        // Save possible changes to database when closing.
        System.out.println("Closing...");
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
}