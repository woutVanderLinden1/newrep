package view.events;

import java.io.Serializable;
import java.util.ArrayList;

import StoryEditor.Arrow;
import StoryEditor.ViewArrow;

public interface StoryElement extends Serializable {
	
	public ArrayList<ViewArrow> getNextArrows();
	public ArrayList<ViewArrow> getPrevArrows();
	public ArrayList<StoryElement> getNextStoryElements();
	public ArrayList<StoryElement> getPrevStoryElements();
	public void addNextArrow(ViewArrow next);
	public void addPrevArrow(ViewArrow prev);
	public void addPrevStoryElement(StoryElement prev);
	public void addNextStoryElement(StoryElement next);

}
