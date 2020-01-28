package StoryEditor;

import java.io.Serializable;
import java.util.ArrayList;

import controller.commands.ContinueCommand;
import model.event.extraevents.TextOption;

public class StoryTextElement implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Text;
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected StoryTextKind kind;
	
	public StoryTextElement(int nroftext) {
		kind=StoryTextKind.DEFAULT;
		this.name= "StoryTextElement"+nroftext;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public StoryTextKind getKind() {
		return kind;
	}

	public ArrayList<TextOption> getTrigOptions() {
		// TODO Auto-generated method stub
		ArrayList<TextOption> toadd=new ArrayList<TextOption>();
		toadd.add( new TextOption("continue", new ContinueCommand()));
		return toadd;
	}
}
