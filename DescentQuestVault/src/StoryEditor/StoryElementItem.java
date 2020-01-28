package StoryEditor;

import java.awt.Image;
import java.util.ArrayList;

import view.events.StoryElement;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class StoryElementItem extends ImageItem implements StoryElement{
	
	private ArrayList<ViewArrow> nextarrows=new ArrayList<ViewArrow>();
	private ArrayList<ViewArrow> prevarrows=new ArrayList<ViewArrow>();
	private ArrayList<StoryElement> nextelements=new ArrayList<StoryElement>();
	private ArrayList<StoryElement> prevelements=new ArrayList<StoryElement>();

	public StoryElementItem() {
		item=new StoryItem("test");
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return .5;
	}
	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return .2;
	}
	@Override
	public ArrayList<ViewArrow> getNextArrows() {
		// TODO Auto-generated method stub
		return nextarrows;
	}

	@Override
	public ArrayList<ViewArrow> getPrevArrows() {
		// TODO Auto-generated method stub
		return prevarrows;
	}

	@Override
	public ArrayList<StoryElement> getNextStoryElements() {
		// TODO Auto-generated method stub
		return nextelements;
	}

	@Override
	public ArrayList<StoryElement> getPrevStoryElements() {
		// TODO Auto-generated method stub
		return prevelements;
	}


	@Override
	public void addNextArrow(ViewArrow next) {
		nextarrows.add(next);
		
	}

	@Override
	public void addPrevArrow(ViewArrow prev) {
		prevarrows.add(prev);
		
	}

	@Override
	public void addPrevStoryElement(StoryElement prev) {
		// TODO Auto-generated method stub
		prevelements.add(prev);
	}

	@Override
	public void addNextStoryElement(StoryElement next) {
		// TODO Auto-generated method stub
		nextelements.add(next);
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ItemOptions getOption() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageItem clone() {
		// TODO Auto-generated method stub
		return this;
	}


	public void setImage(Image image) {
		this.image = image;
		lastImage=image;
	}

}
