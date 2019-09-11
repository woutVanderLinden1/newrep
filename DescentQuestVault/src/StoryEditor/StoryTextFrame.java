package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import frame.SubContainer;
import frame.TemporaryAble;
import view.events.BaseField;
import view.viewItems.ItemBox.SelectAble;

public class StoryTextFrame extends JPanel implements TemporaryAble{

	//private JPanel storyElementOptions=new StoryElementItemTabField(400,120);
	private StoryElementPanel storyElementPanel;
	
	public StoryTextFrame(int i, int j) {
		super();
		
		this.setBackground(new Color(245,222,179));
		this.setPreferredSize(new Dimension(i,j));
		//this.add(storyElementOptions);
		storyElementPanel=new StoryElementPanel(new Dimension(i-20,j-30));
		this.add(storyElementPanel);
	;
	}

	public void sendEvent(MouseEvent e, Point newLocation, SelectAble selectAble) {
		if(SubContainer.isMouseWithinComponent(this)) {
			storyElementPanel.sendEvent(e,newLocation,selectAble);
		}
		
	}

	public void startDragEvent(BaseField todrag) {
		storyElementPanel.startDragEvent(todrag);
		
	}

	public void startDragPanel(DraggAblePanel todrag) {
		storyElementPanel.startDragPanel(todrag);
		
	}

	@Override
	public boolean isTemporary() {
		// TODO Auto-generated method stub
		return false;
	}

	public void deleteSelected(SelectAble selected) {
		storyElementPanel.deleteSelected(selected);
		
	}

	/*
	public void addTextElement() {
		storyElementPanel.addTextElement();
		
	}

	public void addDialogElement() {
		
		storyElementPanel.addDialogElement();
	}

	public void addOptionElement() {
	
		storyElementPanel.addOptionElement();
	}

	public void addSoundElement() {
		storyElementPanel.addSoundElement();
		
	}
	*/

	
}
