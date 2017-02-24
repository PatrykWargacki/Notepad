package controller.model;

import java.net.URL;
import java.util.ResourceBundle;

import controller.MainApp;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.Data;
import util.FileHandler;

public class OverlayController implements Initializable {
	
	@FXML
	private Button buttonAdd;
	
	@FXML
	private Button buttonFnd;
	
	@FXML
	private Button buttonSav;
	
	@FXML
	private Button buttonDel;
	
	@FXML
	private Button buttonMin;
	
	@FXML
	private Button buttonEsc;

	@FXML
	private TextField title;

	@FXML
	private TextArea text;

	private FileHandler fileHandler = new FileHandler(MainApp.stage);
	
	@FXML
	private void buttonAddAction(){
		newWindow();
	}
	
	@FXML
	private void buttonFndAction(){
		Data data = fileHandler.load();
		if(data != null){
			title.setText(data.getTitle());
			text.setText(data.getText());
			
			Stage stage = (Stage) title.getScene().getWindow();
			stage.setTitle(title.getText());
		}
	}
	
	@FXML
	private void buttonSavAction(){
		Stage stage = (Stage) title.getScene().getWindow();
		stage.setTitle(title.getText());

		fileHandler.save(new Data(title.getText(), text.getText()));
	}
	
	@FXML
	private void buttonDelAction(){
		fileHandler.delete();
	}
	
	@FXML
	private void buttonMinAction(){
		Stage stage = (Stage) buttonMin.getScene().getWindow();
		stage.setIconified(true);
	}
	
	@FXML
	private void buttonEscAction(){
		Stage stage = (Stage) buttonEsc.getScene().getWindow();
		stage.close();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initImagesOnButtons();
		
	}
	
	public void newWindow(){
		Platform.runLater(new Runnable(){
			public void run(){
				new MainApp().start(new Stage());
			}
		});
	}
	
	public void initImagesOnButtons(){
		setStyle(buttonAdd, "add");
		setStyle(buttonFnd, "fnd");
		setStyle(buttonSav, "sav");
		setStyle(buttonDel, "del");
		setStyle(buttonMin, "min");
		setStyle(buttonEsc, "esc");
	}
	
	private void setStyle(Button button, String img){
		img = MainApp.class.getResource("view/resources/" + img + ".png").toExternalForm();
		button.setStyle("-fx-background-image: url('" + img + "');" +
        		   		"-fx-background-position: center center;" +
        		   		"-fx-background-repeat: stretch;");
	}
}
