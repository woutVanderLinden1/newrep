package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;

import view.viewItems.ItemBox.ListContainer;

public class StoryListContainer extends ListContainer {

	
	private ArrayList<StoryElementOption> options=new ArrayList<StoryElementOption>();
	
	public StoryListContainer(int width, int height) {
		super(width, height);
		this.setPreferredSize(new Dimension(width,height));
		this.setBackground(Color.gray);
		defaultSize=130;
		addStoryButtons();
		
		// TODO Auto-generated constructor stub
	}

	private void addStoryButtons() {
		
		options.add(StoryElementOption.Text);
		options.add(StoryElementOption.Dialog);
		options.add(StoryElementOption.Option);
		options.add(StoryElementOption.Sound);
		
		this.setLayout(new GridLayout(1,itemList.size()));
		this.setPreferredSize(new Dimension(defaultSize*itemList.size(),this.getHeight()));
		for(StoryElementOption option:options) {
			createButton(option);
			
		}
		
	}

	private void createButton(StoryElementOption option) {
		JButton butt=new StoryElementButton(option);
		butt.setPreferredSize(new Dimension(defaultSize,defaultSize));
		
		this.add(butt);
	}

}
