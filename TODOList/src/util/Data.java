package util;

public class Data {
	private String title;
	private String text;
	
	public Data(){
		title = "";
		text = "";
	}
	
	public Data(String title, String text){
		this.title = title;
		this.text = text;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void addText(String text) {
		this.text = this.text.concat(text);
	}
}
