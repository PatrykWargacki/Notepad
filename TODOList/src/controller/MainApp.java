package controller;

import java.io.IOException;

import controller.model.OverlayController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

	public static Stage stage;
	private Stage primaryStage;
	private AnchorPane anchorPane;
	
	private double xOffset = 0;
    private double yOffset = 0;
    
	@Override
	public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
		this.primaryStage.initStyle(StageStyle.UNDECORATED);
		this.primaryStage.getIcons().add(new Image(MainApp.class.getResourceAsStream("view/resources/icon.png")));
		stage = primaryStage;
		initLayout();
		initDragAndDrop();
		
		TextField textField = (TextField) anchorPane.lookup("#title");
		textField.requestFocus();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void initLayout(){
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/Overlay.fxml"));
		loader.setController(new OverlayController());
        anchorPane = (AnchorPane) loader.load();
		
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initDragAndDrop(){
		anchorPane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
		anchorPane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
	}
	
}
