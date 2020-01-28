package StoryEditor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.UserInputController;
import controller.commands.LoadMovementCommand;
import controller.commands.SaveMovementCommand;
import frame.SubContainer;

public class StoryOptions extends SubContainer{

	private JButton saveButton;
	private JButton loadButton;
	private JButton testButton;
	
	public StoryOptions(int i, int j, StoryEditor storyEditor) {
		super(i,j);
		initialiseSaveButton();
		initialiseLoadButton();
		initialiseTestButton();
		initialiseSaveAsTravelEventButton();
		initialiseSaveAsCityEventButton();
		initialiseAddCityEventButton();
		this.setBackground(Color.BLUE);
		
		
	}

	
	private void initialiseAddCityEventButton() {
		testButton=new JButton("Add city event");
		testButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StoryEditorController control=(StoryEditorController) StoryEditorController.getController();
				control.performCommand(new AddCityEventCommand(control.getMainFrame()));
				
			}
			
		});
		this.add(testButton);
		
	}


	private void initialiseTestButton() {
		testButton=new JButton("Test");
		testButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StoryEditorController control=(StoryEditorController) StoryEditorController.getController();
				control.performCommand(new TestCampaignCommand());
				
			}
			
		});
		this.add(testButton);
	}


	private void initialiseLoadButton() {
		loadButton=new JButton("Load");
		loadButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StoryEditorController control=(StoryEditorController) StoryEditorController.getController();
				control.performCommand(new LoadCampaignCommand(control.getMainFrame()));
				
			}
			
		});
		this.add(loadButton);
		
	}



	private void initialiseSaveButton() {
		saveButton=new JButton("Save");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StoryEditorController control=(StoryEditorController) StoryEditorController.getController();
				control.performCommand(new SaveCampaignCommand());
				
			}
			
		});
		this.add(saveButton);
	}

	private void initialiseSaveAsTravelEventButton() {
		saveButton=new JButton("Make TravelEvent");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StoryEditorController control=(StoryEditorController) StoryEditorController.getController();
				control.performCommand(new SaveTravelEventCommand());
				
			}
			
		});
		this.add(saveButton);
	}
	private void initialiseSaveAsCityEventButton() {
		saveButton=new JButton("Make CityEvent");
		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StoryEditorController control=(StoryEditorController) StoryEditorController.getController();
				control.performCommand(new SaveCityEventCommand());
				
			}
			
		});
		this.add(saveButton);
	}


}
