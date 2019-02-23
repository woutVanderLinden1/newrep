package view.viewItems.ItemBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import controller.UserInputController;
import controller.commands.FillExitCommand;
import controller.commands.ICommand;
import frame.SubContainer;
import model.SelectedArea;
import view.Items.Map.ViewSquare;
import view.viewItems.ItemBox.*;

public class ItemInfoPanel  extends SubContainer implements Serializable{
	
	
	private ItemTabField tabs;
//	private AllInstanceItemBox instances;
	private InfoItemBox infoBox;
	//private JScrollPane infoscroll;

	public ItemInfoPanel(int width, int height) {
		super(width, height);
		this.setBackground(Color.BLUE);
		
		tabs=new ItemTabField(width,3*height/12);
		//instances=new AllInstanceItemBox(width,2*height/6);
		infoBox=new InfoItemBox(width-20,9*height/12);
	
		//JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,tabs,instances);
		JButton but=new JButton("Fill exits");
		but.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserInputController control =UserInputController.getController();
				control.performCommand(new FillExitCommand());
			}
		});
		this.add(but);
		JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,tabs,infoBox);
		this.add(splitPane);
	}


	public ICommand getSelectedCommand(ViewSquare tile) {
		// TODO Auto-generated method stub
		return infoBox.getSelectedCommand(tile);
	}


	public void setSelected(SelectAble item) {
		infoBox.setSelected(item);
	}


	public void rotateSelected() {
		infoBox.rotateSelected();
		
	}


	public boolean hasSelected() {
		// TODO Auto-generated method stub
		return infoBox.hasSelected();
	}


	public SelectAble getSelected() {
		// TODO Auto-generated method stub
		return infoBox.getSelected();
	}


	public SelectedArea getselectedArea() {
		// TODO Auto-generated method stub
		return infoBox.getSelectedArea();
	}


	public void deleteSelected() {
		infoBox.removeSelected();
		
	}


	public void unselect() {
		deleteSelected();
		
	}


	public void renewItemList() {
		// TODO Auto-generated method stub
		tabs.renewItemList();
	}


	public void refreshSelected() {
		infoBox.refreshSelected();
		
	}
}
