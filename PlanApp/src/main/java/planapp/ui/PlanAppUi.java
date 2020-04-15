package planapp.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import planapp.dao.CourseFileDao;
import planapp.dao.PlanFileDao;
import planapp.domain.Course;
import planapp.domain.PlanService;

public class PlanAppUi extends Application {
    
    private PlanService planService;
    private Scene plan;
    private Scene login;
    private Scene register;
    
    @Override
    public void start(Stage stg) throws Exception {   
        // Some of plan components:
        VBox courseCollection = new VBox();
        Text planInstruction = new Text("");
        planInstruction.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        
        // Login components:
        GridPane logGrid = new GridPane();
        
        logGrid.setAlignment(Pos.CENTER);
        logGrid.setHgap(10);
        logGrid.setVgap(10);
        logGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text logNotification = new Text();
        Text logTitle = new Text();
        Text welcome = new Text();
        
        logTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        logTitle.setText("PlanApp");    
        
        Hyperlink registerLink = new Hyperlink("Register");
        TextField logUsername = new TextField();
        Label logUserLabel = new Label("Plan name:");
        Label registerInfo = new Label("Don't have a plan yet?");
        Button logButton = new Button("Login");
        
        logButton.setOnAction((ActionEvent push) -> {
            logNotification.setText("");
            if (logUsername.getText().isEmpty()) {
                logNotification.setFill(Color.FIREBRICK);
                logNotification.setText("Please enter name of a plan");   
            } else if (logUsername.getText().length() < 3 || logUsername.getText().length() > 12) {
                logNotification.setFill(Color.FIREBRICK);
                logNotification.setText("Plan name length is between 3-12 chars");
            } else if (!planService.login(logUsername.getText())) {
                logNotification.setFill(Color.FIREBRICK);
                logNotification.setText("Plan doesn't exist");
                
            } else {
                planService.login(logUsername.getText());
                welcome.setText("User: " + planService.thisPlansAuthor());
                planInstruction.setText("Welcome! System contains a total of " + planService.allCourses().size() + " courses");     
                
                if (courseCollection.getChildren().isEmpty()) {
                    courseCollection.getChildren().add(planInstruction);
                }
                
                stg.setScene(plan);
            }
        });
        
        registerLink.setOnAction((ActionEvent push) -> {
            stg.setScene(register);
            logNotification.setText("");
            logUsername.setText("");
        });
        
        logGrid.add(logTitle, 0, 1);
        logGrid.add(logUserLabel, 0, 2);
        logGrid.add(logUsername, 1, 2);
        logGrid.add(logButton, 1, 3);
        logGrid.add(registerInfo, 0, 4);
        logGrid.add(registerLink, 1, 4);
        logGrid.add(logNotification, 0, 5);
        
        login = new Scene(logGrid, 500, 200);
        
        
        // Register components:
        GridPane regGrid = new GridPane();
        regGrid.setAlignment(Pos.CENTER);
        regGrid.setHgap(10);
        regGrid.setVgap(10);
        regGrid.setPadding(new Insets(25, 25, 25, 25));

        Text regTitle = new Text();
        regTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        regTitle.setText("Register a plan");
        
        Text regNotification = new Text();
        Text regNotification1 = new Text();
        
        Label regUsernameLabel = new Label("Plan name:");
        Label regNameLabel = new Label("Your name:");
        TextField regUsername = new TextField();
        TextField regName = new TextField();
        Button regButton = new Button("Register");
        Hyperlink regReturn = new Hyperlink("Cancel");
        
        regButton.setOnAction((ActionEvent push) -> {
            regNotification.setText("");
            regNotification1.setText("");
            boolean typeValidatorError = false;
            boolean existingError = false;
            
            if (regUsername.getText().length() < 3 || regUsername.getText().length() > 12) {
                regNotification.setFill(Color.FIREBRICK);
                regNotification.setText("Plan name length must be between 3-12");
                typeValidatorError = true;
            }
                
            if (regName.getText().length() < 4 || regName.getText().length() > 30) {
                regNotification1.setFill(Color.FIREBRICK);
                regNotification1.setText("Name length must be between 4-30");
                typeValidatorError = true;
            }
            
            if (!typeValidatorError) {
                if (!planService.createPlan(regUsername.getText(), regName.getText())) {
                    regNotification.setFill(Color.FIREBRICK);
                    regNotification.setText("Plan already exists");
                    existingError = true;
                }
            }
                
            if (!typeValidatorError && !existingError) {
                try {
                    planService.createPlan(regUsername.getText(), regName.getText());
                    logNotification.setFill(Color.GREEN);
                    regNotification.setText("");
                    logNotification.setText("Registeration successful!");
                    stg.setScene(login);
                    regUsername.setText("");
                    regName.setText("");
                    
                } catch (Exception ex) {
                    regNotification.setFill(Color.FIREBRICK);
                    regNotification.setText("Something went wrong");
                    System.out.println(ex);
                }
            }            
        });
        
        regReturn.setOnAction((ActionEvent push) -> {
            stg.setScene(login);
            regUsername.setText("");
            regName.setText("");
            regNotification.setText("");
            regNotification1.setText("");
        });
                
        regGrid.add(regTitle, 0, 1);
        regGrid.add(regUsernameLabel, 0, 2);
        regGrid.add(regUsername, 1, 2);
        regGrid.add(regNameLabel, 0, 3);
        regGrid.add(regName, 1, 3);
        regGrid.add(regReturn, 0, 4);
        regGrid.add(regButton, 1, 4);
        regGrid.add(regNotification, 1, 5);
        regGrid.add(regNotification1, 1, 6);
        
        register = new Scene(regGrid, 500, 200);
        
        
        // Plan components:        
        List<Course> courses = planService.allCourses();
        List<Course> nextCourses = new ArrayList<>();
        
        Text planNotification = new Text("");
        
        BorderPane planGrid = new BorderPane();      
        BorderPane topBar = new BorderPane();
        
        AnchorPane actions = new AnchorPane();
        HBox links = new HBox();
        Hyperlink logout = new Hyperlink("Logout");
        Hyperlink delete = new Hyperlink("Delete plan");     
        
        VBox suggestedCourses = new VBox();
        Text suggestTitle = new Text("");
        
        suggestTitle.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        suggestedCourses.getChildren().add(suggestTitle);
        
        VBox studySubjects = new VBox();
        Text menuTitle = new Text("Menu");
        Button saveButton = new Button("Save");
                
        planInstruction.setFont(Font.font("Arial", FontWeight.BOLD, 12));        
        courseCollection.getChildren().add(planInstruction);
        courseCollection.setAlignment(Pos.TOP_LEFT);
        courseCollection.setPadding(new Insets(10, 10, 10, 10));
        List<CheckBox> boxes = new ArrayList<>();
 
        saveButton.setOnAction((ActionEvent push) -> {
            suggestedCourses.getChildren().clear();
            suggestedCourses.getChildren().add(suggestTitle);
            suggestTitle.setText("Suggested courses: ");
            nextCourses.clear();
            
            for (CheckBox c : boxes) {
                String pcs[] = c.getText().split(": ");
                Course cc = planService.findCourse(pcs[0]);
                
                if (c.isSelected() && !planService.selectedCourses().contains(cc)) {
                    planService.addCourse(cc);
                } else if (c.isSelected() && planService.selectedCourses().contains(cc)) {
                    continue;
                } else {
                    if (planService.selectedCourses().contains(cc)) {
                        planService.removeCourse(cc);
                    }
                }
            }            
            
            for (Course c : courses) {
                List<Course> prerequisites = c.getPrerequisites();
                List<Course> completed = planService.selectedCourses();
                                
                if (!completed.contains(c)) {
                    boolean allClear = true;
                    for (Course p : prerequisites) {
                        if (!completed.contains(p)) {
                            allClear = false;
                            break;
                        }
                    }
                    
                    if (allClear) {
                        nextCourses.add(c);
                    }
                }
            }            
            
            if (!nextCourses.isEmpty()) {
                nextCourses.forEach((cc) -> {
                    suggestedCourses.getChildren().add(new Text(cc.toString()));
                });
            }
        });
        
        menuTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        VBox.setMargin(menuTitle, new Insets(0, 0, 0, 2));
        studySubjects.getChildren().add(menuTitle);
        
        Hyperlink csLink = new Hyperlink("TKT-Courses");
        Hyperlink prerequisitesTKT = new Hyperlink("TKT-Prerequisites");
        
        VBox.setMargin(csLink, new Insets(0, 0, 0, 8));
        VBox.setMargin(prerequisitesTKT, new Insets(0, 0, 0, 8));
        
        csLink.setOnAction((ActionEvent push) -> {
            courseCollection.getChildren().clear();
            if (planService.selectedCourses().isEmpty()) {
                planInstruction.setText("Start by selecting all completed courses");
            } else {
                planInstruction.setText("Your plan: ");
            }
            
            courseCollection.getChildren().add(planInstruction);
            
            for (Course c : courses) {
                CheckBox box = new CheckBox(c.toString());
            
                if (planService.selectedCourses().contains(c)) {
                    box.setSelected(true);
                }
                
                boxes.add(box);
                courseCollection.getChildren().add(box);
            }
            
            courseCollection.getChildren().add(saveButton);
            courseCollection.getChildren().add(planNotification);
        });
        
        prerequisitesTKT.setOnAction((ActionEvent push) -> {
            courseCollection.getChildren().clear();
            suggestedCourses.getChildren().clear();
            planInstruction.setText("Prerequisites graph for TKT-courses: (Coming soon)");
            courseCollection.getChildren().add(planInstruction);
        });
        
        studySubjects.getChildren().add(csLink);
        studySubjects.getChildren().add(prerequisitesTKT);
        studySubjects.setStyle("-fx-background-color: #CACACA");
                
        logout.setOnAction((ActionEvent push) -> {
            suggestedCourses.getChildren().clear();
            courseCollection.getChildren().clear();
            
            logUsername.setText("");
            planService.logout();
            stg.setScene(login);
        });
        
        delete.setOnAction((ActionEvent push) -> {
            logUsername.setText("");
            if (planService.deletePlan()) {
                logNotification.setFill(Color.GREEN);
                logNotification.setText("Plan deleted successfully");
            } else {
                logNotification.setFill(Color.FIREBRICK);
                logNotification.setText("Something went wrong");
            }
            
            stg.setScene(login);
        });
        
        links.getChildren().addAll(logout, delete);
        actions.getChildren().addAll(links);
        AnchorPane.setRightAnchor(links, 10.0);
        
        suggestedCourses.setPadding(new Insets(10, 10, 10, 10));
        
        topBar.setPadding(new Insets(10, 10, 10, 10));
        topBar.setStyle("-fx-background-color: #CACACA");
        topBar.setRight(actions);
        topBar.setLeft(welcome);        
        
        planGrid.setLeft(studySubjects);
        planGrid.setRight(suggestedCourses);
        planGrid.setCenter(courseCollection);
        planGrid.setTop(topBar);
        
        plan = new Scene(planGrid, 1000, 800);
        
        
        // Run application:
        stg.setTitle("PlanApp");
        stg.setScene(login);
        stg.show();       
    }
    
    @Override
    public void init() throws Exception {
        Properties prop = new Properties();
        FileInputStream input = new FileInputStream(new File("config.file"));
        prop.load(new InputStreamReader(input, Charset.forName("UTF-8")));
        
        String planFile = prop.getProperty("plans");
        String courseFile = prop.getProperty("courses");
        String courseInfo = prop.getProperty("coursesInfo");
        
        CourseFileDao courseDao = new CourseFileDao(courseFile, courseInfo);
        PlanFileDao planDao = new PlanFileDao(planFile, courseDao);
        
        this.planService = new PlanService(planDao, courseDao);
    }
    
    @Override
    public void stop() {
        System.out.println("Closing...");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}