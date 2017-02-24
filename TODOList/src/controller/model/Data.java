package controller.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Data {
	private StringProperty title;
	private StringProperty text;
	
	public Data(){
		title = new SimpleStringProperty();
		text  = new SimpleStringProperty();
	}

	public StringProperty getTitle() {
		return title;
	}

	public StringProperty getText() {
		return text;
	}

	public void setTitle(String title) {
		this.title.set(title);
	}

	public void setText(String text) {
		this.text.set(text);
	}
	
}
