package view.viewItems.ItemBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;

import controller.UserInputController;
import frame.SubContainer;
import view.menu.CommandButton;

public class ItemBox extends SubContainer {
	
	private ArrayList<CommandButton> buttons=new ArrayList<CommandButton>();
	

	public void addButton(CommandButton commandButton) {
		commandButton.setSize(200, 50);
		commandButton.setPreferredSize(new Dimension(200,50));
		commandButton.addButtonPressedListener(UserInputController.getController());
		buttons.add(commandButton);
		//Container cont =new Container();
		//cont.add(commandButton);
		//cont.setSize(this.getWidth(),100);
		
		//commandButton.setLocation(this.getWidth()/2-100,100);
		this.add(commandButton);

	}
	
	public ItemBox(int width,int height){
		super(width,height);
		this.setBackground(Color.RED);
		this.setPreferredSize(new Dimension(width,height));
		//this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		//this.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	}

	public void setWidth(int width2) {
		this.setSize(width2,this.getHeight());
		
	}

	public void setHeight(int height2) {
		this.setSize(this.getWidth(),height2);
		
	}
}
