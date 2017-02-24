package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

public class FileHandler {
	private FileChooser fileChooser;
	private Window ownerWindow;
	private File currentFile;
	
	public FileHandler(Window ownerWindow){
		this.ownerWindow = ownerWindow;
		currentFile = null;
		fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().add(
				new ExtensionFilter("TXT Files", "*.txt"));
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}
	
	public Data load(){
		fileChooser.setTitle("Open Memo");
		File file = fileChooser.showOpenDialog(ownerWindow);
		Scanner scanner;
		Data data = new Data();
		try {
			scanner = new Scanner(file);
		
			if(scanner.hasNextLine()){
				data.setTitle(scanner.nextLine());
			}
			while(scanner.hasNextLine()){
				data.addText(scanner.nextLine());
			}
			scanner.close();
			currentFile = file;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public void save(Data data){
		fileChooser.setTitle("Save Memo");
		File file = fileChooser.showSaveDialog(ownerWindow);
		PrintWriter printWriter;
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			printWriter = new PrintWriter(file);
			printWriter.println(data.getTitle());
			printWriter.println(data.getText());
			printWriter.close();
			currentFile = file;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(){
		if(currentFile!=null){
			currentFile.delete();
			currentFile = null;
		}
	}
}
