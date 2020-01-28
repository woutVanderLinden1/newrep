package StoryEditor;

import java.util.ArrayList;

import view.events.StoryElement;

public class TextElement implements StoryElement {

	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public ArrayList<ViewArrow> getNextArrows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ViewArrow> getPrevArrows() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StoryElement> getNextStoryElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<StoryElement> getPrevStoryElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNextArrow(ViewArrow next) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPrevArrow(ViewArrow prev) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPrevStoryElement(StoryElement prev) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addNextStoryElement(StoryElement next) {
		// TODO Auto-generated method stub
		
	}
}
