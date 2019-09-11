package StoryEditor;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import controller.UserInputController;
import frame.MainFrame;
import misc.BaseFile;
import monsterEditor.MonsterEditor;
import monsterEditor.MonsterEditorController;
import monsterEditor.MonsterItemTabField;
import monsterEditor.MonsterOptions;
import view.events.EventBox;
import view.viewItems.ItemBox.ItemTabField;
import view.viewItems.ItemBox.SelectAble;

public class StoryEditor extends MainFrame{

	
	private BaseFile file;
	//has textframe
	private StoryTextFrame storyframe;
	
	public StoryTextFrame getStoryframe() {
		return storyframe;
	}

	

	private StoryOptions options;
	//hero/monster adder
	private EventBox eventsOf;
	private ItemTabField field;
	private StoryInfoItemBox infobox;
	private StoryEditorView view;
	private UserInputController oldcontroller;
	//events in actions
	
	//test function
	
	
	public void setStoryframe(StoryTextFrame storyframe) {
		this.storyframe = storyframe;
	}

	public EventBox getEventsOf() {
		return eventsOf;
	}

	public void setEventsOf(EventBox eventsOf) {
		this.eventsOf = eventsOf;
	}

	public StoryInfoItemBox getInfobox() {
		return infobox;
	}

	public void setInfobox(StoryInfoItemBox infobox) {
		this.infobox = infobox;
	}
	public StoryEditor() {
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setSize(1600,800);
		this.setMinimumSize(new Dimension(1800,800));
		this.setPreferredSize(new Dimension(1800,800));
		this.setVisible(true);
		this.setName("StoryEditor");
		this.setTitle("StoryEditor");
		eventsOf=new EventBox(400,800);
		eventsOf.setMinimumSize(new Dimension(350,800));
		infobox=new StoryInfoItemBox(400,800,300);
		infobox.setMinimumSize(new Dimension(300,800));
		
	
		
		options=new StoryOptions(300,250,this);
		options.setMinimumSize(new Dimension(300,250));
		infobox.setController((StoryEditorController) userInput);
		field=new StoryItemTabField(300,350);
		storyframe=new StoryTextFrame(600,800);
		view=new StoryEditorView(this);
		this.initialiseNewController();
		JSplitPane splitPane0 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,options,field);
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitPane0,infobox);
		JPanel pan2=new JPanel();
		pan2.add(splitPane1);
		JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pan2,storyframe);
		JSplitPane splitPane3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitPane2,eventsOf);
		JPanel panel=new JPanel();
		panel.setPreferredSize(this.getSize());
		panel.setSize(this.getSize());
		panel.add(splitPane3);
		lpane.add(panel,2,2);
		lpane.moveToFront(panel);
		setAlwaysOnTop(true);
		StoryEditor edit=this;
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        
		            returnOldController();
		        
		        
		    }
		});
		//options.defaultSelect();
		lpane.moveToFront(glasspane);
		
		
	}
	
	@Override
	public void doDragListenerEvent(MouseEvent e, SelectAble selectAble) {
		//lpane.moveToBack(glasspane);
		//currentMenu.dispatchEvent(e);
		//lpane.moveToFront(glasspane);
		Point p=e.getLocationOnScreen();
		Point newLocation=new Point(p.x-this.getX(),p.y-this.getY());
		eventsOf.sendEvent(e, newLocation, selectAble);
		infobox.sendEvent(e, newLocation, selectAble);
		options.sendEvent(e,newLocation,selectAble);
		storyframe.sendEvent(e,newLocation,selectAble);
		
	}
	
	

	private void initialiseNewController() {
		userInput=StoryEditorController.createStoryEditorController(null, view,this);
		oldcontroller=UserInputController.getController();
		UserInputController.setController(userInput);
	}

	protected void returnOldController() {
	
		UserInputController.setController(oldcontroller);
	}



/*
	public void addTextToTextFrameElement() {
		
		storyframe.addTextElement();
	}



	public void addDialogToTextFrameElement() {
		
		storyframe.addDialogElement();
	}



	public void addOptionToTextFrameElement() {
		
		storyframe.addOptionElement();
	}



	public void addSoundToTextFrameElement() {
		
		storyframe.addSoundElement();
	}
	*/
}
