package view.menu;

import java.awt.Color;
import java.io.Serializable;

import StoryEditor.LoadCampaignGameCommand;
import controller.commands.ExitCommand;
import controller.commands.GoBackCommand;
import controller.commands.LoadCommand;
import controller.commands.StartEditorCommand;
import view.LoadNewCampaignGameCommand;
import view.menu.CommandButton;
import view.menu.Menu;
import view.viewItems.TitleBox;


public class CampaignMenu extends Menu {

	public CampaignMenu(int width, int height) {
		super(width, height);
		
	}

	@Override
	protected void prepareItemBox() {
		addNewButton();
		addLoadButton();
		addBackButton();
		
	}
	@Override
	protected void initialiseTitleBox() {
		this.setBackground(Color.BLUE);
		titleBox=new TitleBox("QuestEditor",this.getWidth(),80);
		
		this.add(titleBox);
		
	}

	private void addNewButton() {
		itemBox.addButton(new CommandButton("New",new LoadNewCampaignGameCommand(this)));
	}
	private void addLoadButton() {
		itemBox.addButton(new CommandButton("Load",new LoadCampaignGameCommand(this)));
	}
	private void addBackButton() {
		itemBox.addButton(new CommandButton("Back",new GoBackCommand()));
	}
}
