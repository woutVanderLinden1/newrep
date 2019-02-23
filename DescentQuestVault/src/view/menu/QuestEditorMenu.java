package view.menu;

import java.awt.Color;
import java.io.Serializable;

import controller.commands.ExitCommand;
import controller.commands.GoBackCommand;
import controller.commands.LoadCommand;
import controller.commands.StartEditorCommand;
import view.viewItems.TitleBox;


public class QuestEditorMenu extends Menu {

	public QuestEditorMenu(int width, int height) {
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
		itemBox.addButton(new CommandButton("New",new StartEditorCommand()));
	}
	private void addLoadButton() {
		itemBox.addButton(new CommandButton("Load",new LoadCommand(this)));
	}
	private void addBackButton() {
		itemBox.addButton(new CommandButton("Back",new GoBackCommand()));
	}
}
