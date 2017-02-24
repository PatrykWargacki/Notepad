package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
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
			scanner = new Scanner(file, "UTF-8");
		
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
		File file;
		if(currentFile == null){
			fileChooser.setTitle("Save Memo");
			file = fileChooser.showSaveDialog(ownerWindow);
		}else{
			file = currentFile;
		}
		try {
			if(!file.exists()){
				file.createNewFile();
			}
			
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
			
			writer.append(data.getTitle());
			writer.append(data.getText());
			writer.flush();
			writer.close();
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
