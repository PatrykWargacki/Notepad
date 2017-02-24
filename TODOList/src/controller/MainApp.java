package controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainApp extends Application {

	private Stage primaryStage;
	private AnchorPane anchorPane;
	
	private double xOffset = 0;
    private double yOffset = 0;
    
	@Override
	public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
		this.primaryStage.initStyle(StageStyle.UNDECORATED);
		initLayout();
		initDragAndDrop();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public void initLayout(){
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/Overlay.fxml"));
		anchorPane = (AnchorPane) loader.load();

		initImageOnButton("buttonAdd", "view/resources/add.png");
		initImageOnButton("buttonFnd", "view/resources/fnd.png");
		initImageOnButton("buttonSav", "view/resources/sav.png");
		initImageOnButton("buttonDel", "view/resources/del.png");
		initImageOnButton("buttonMin", "view/resources/min.png");
		initImageOnButton("buttonEsc", "view/resources/esc.png");
        
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initImageOnButton(String id, String resource){
		String img = MainApp.class.getResource(resource).toExternalForm();
		Button button = (Button) anchorPane.lookup("#" + id);
		button.setStyle("-fx-background-image: url('" + img + "');" +
		           		"-fx-background-position: center center;" +
		           		"-fx-background-repeat: stretch;");
		
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
