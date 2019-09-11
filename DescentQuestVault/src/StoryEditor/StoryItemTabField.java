package StoryEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.ItemTabField;

public class StoryItemTabField extends ItemTabField {

	public StoryItemTabField(int width, int height) {
		super(width, height);
		//should only have customvaluesetters/generators and
		//modifiers and textgenerators
		// TODO Auto-generated constructor stub
	}


	@Override
	public void initialiseBox() {
		ItemOptions[] comboOptions = { ItemOptions.Hero, ItemOptions.Event, ItemOptions.Text,ItemOptions.Value ,ItemOptions.Generator};
		//maybe allow creation of new groups
		box=new JComboBox<ItemOptions>(comboOptions) {
			public void actionPerformed(ActionEvent e) {
				list.addItems(box);
		    }
		};
		box.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				list.addItems(box);
		    }
		});
		this.add(box);
		box.setLocation(this.getWidth()/2-40, this.getHeight()/2);
	
	}
}
