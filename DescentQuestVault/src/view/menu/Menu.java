package view.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.commands.ExitCommand;
import frame.SubContainer;
import misc.listeners.IResizeListeners;
import view.viewItems.TitleBox;
import view.viewItems.ItemBox.ItemBox;

public abstract class Menu extends SubContainer implements IResizeListeners{

	protected TitleBox titleBox;
	protected ItemBox itemBox;
	private JScrollPane scrollPane;


	public Menu(int width,int height){
		
		super(width,height);
		
		//this.setLayout(mgr);
		//this.setSize(width,height);
		
		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//this.setAlignmentX(Component.CENTER_ALIGNMENT);
		initialiseTitleBox();
		
		
		itemBox=new ItemBox(250,height-100);
	//	itemBox.setPreferredSize(new Dimension(250,600));
		scrollPane= new JScrollPane(itemBox,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		itemBox.setAutoscrolls(true);
		scrollPane.setPreferredSize(new Dimension(250,height-150));
		this.add(scrollPane);
		prepareItemBox();
		setComponentSizes(width,height);
	}

	protected void initialiseTitleBox() {
		this.setBackground(Color.BLUE);
		titleBox=new TitleBox("Menu",this.getWidth(),80);
		
		this.add(titleBox);
		
	}

	protected abstract void prepareItemBox();
	
	public void setSize(int w,int h){
		super.setSize(w, h);
		
	}


	@Override
	public void resizePart(int width, int height) {
		setComponentSizes(width,height);
		
	}


	public void setComponentSizes(int width, int height) {
		System.out.println("sized");
		System.out.println("resized to "+width+" "+height);
		Dimension d=new Dimension(width,80);
		this.setSize(width,height);
		titleBox.setSize(width,80);
		titleBox.setPreferredSize(d);
		//itemBox.setSize(250,height-100);
		//itemBox.setPreferredSize(new Dimension(250,height-100));
		System.out.println("titlleboxwidth: "+titleBox.getWidth());
		//titleBox=new TitleBox("Menu",width,80);
		//itemBox=new ItemBox(250,height-100);
		scrollPane.setPreferredSize(new Dimension(250,height-150));
	}

}
