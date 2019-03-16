package model.event.extraevents;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.UserInputController;
import controller.commands.ContinueCommand;
import controller.commands.Game.HoldToContinueCommand;
import controller.commands.Game.ShowTextCommand;
import model.event.Event;
import model.event.Univent;
import view.menu.QuestCreator;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.SelectKind;

//when triggered stops the flow of events and shows text
// continues triggering after activated and continue is pressed
public class TextStop extends Event implements StopAble {

	private boolean stopped;
	private String thetext;
	private ArrayList<TextOption> options;
	
	private static int nrtextstops=0;
	private  static String getNewName() {
		nrtextstops++;
		return "textstop"+nrtextstops;
	}
	public TextStop(String thestring) {
		thetext=thestring;
		this.setIDName("textStop");
		this.setName(getNewName());
		options=new ArrayList<TextOption>();
		options.add(new TextOption("continue",new ContinueCommand(this)));
	}
	
	public TextStop() {
		thetext="pls press continue";
		this.setIDName("textStop");
		this.setName(getNewName());
		options=new ArrayList<TextOption>();
		options.add(new TextOption("continue",new ContinueCommand(this)));
	}
	
	public void trigger() {
		
		stopped=true;
		
		UserInputController control=UserInputController.getController();
		control.performCommand(new HoldToContinueCommand(this));
		control.performCommand(new ShowTextCommand(thetext,options));
		while(stopped) {
			try {
				System.out.println("here");
				Thread.sleep(500);
				//TimeUnit.SECONDS.sleep((long) .5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
		//stop the triggers for a while
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.EVENT;
	}

	@Override
	public void initialise(QuestCreator questCreator) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isStopEvent() {
		return true;
	}

	public void continueStop() {
		// TODO Auto-generated method stub
		stopped=false;
	}
	
	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		
		JTextArea area=new JTextArea(200,400);
		area.setText(thetext);
		
		
		area.getDocument().addDocumentListener(new DocumentListener() {

		
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				warn();
				
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				warn();
				
			}
			private void warn() {
				thetext= area.getText();
				
			}
		});
		//area.setSize(200,400);
	
		JScrollPane pan=new JScrollPane(area);
		itemInfoText.addToPanel(pan);
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		TextStop toreturn= new TextStop();
	
		return toreturn;
		
	}
	
	
}
