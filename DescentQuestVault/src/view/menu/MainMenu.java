package view.menu;

import controller.command.OpenMonsterEditorCommand;
import controller.command.storyeditor.*;
import controller.commands.ExitCommand;
import controller.commands.GoToMenuCommand;

public class MainMenu extends Menu {

	public MainMenu(int width, int height) {
	
		super(width, height);
		// TODO Auto-generated constructor stub
	}


	protected void prepareItemBox() {
		addCampaignButton();
		addSpecificQuestButton();
		addQuestEditorButton();
		addMonsterEditorButton();
		addStoryEditorButton();
		addItemEditorButton();
		addClassEditorButton();
		//addSubQuestEditorButton();
		addHeroEditorButton();
		addCityEditorButton();
		addOptionsButton();
		addCreditsButton();
		addExitButton();
		
		
	}


	private void addClassEditorButton() {
		itemBox.addButton(new CommandButton("SkillEditor",new OpenSkillEditorCommand()));
		
	}


	private void addStoryEditorButton() {
		itemBox.addButton(new CommandButton("StoryEditor",new OpenStoryEditorCommand()));
		
	}


	private void addCreditsButton() {
		itemBox.addButton(new CommandButton("Credits",new ExitCommand()));
		
		
	}


	private void addOptionsButton() {
		itemBox.addButton(new CommandButton("Options",new ExitCommand()));
		
		
	}


	private void addCityEditorButton() {
		itemBox.addButton(new CommandButton("CityEditor",new ExitCommand()));
		
	}


	private void addMonsterEditorButton() {
		itemBox.addButton(new CommandButton("MonsterEditor",new OpenMonsterEditorCommand()));
		
	}
	private void addHeroEditorButton() {
		itemBox.addButton(new CommandButton("HeroEditor",new OpenHeroEditorCommand()));
		
	}


	private void addExitButton() {
		itemBox.addButton(new CommandButton("Exit",new ExitCommand()));
		
	}





	private void addQuestEditorButton() {
		itemBox.addButton(new CommandButton("QuestEditor",new GoToMenuCommand(Menus.QUESTEDITOR)));
		
	}

	private void addItemEditorButton() {
		itemBox.addButton(new CommandButton("ItemEditor",new OpenItemEditorCommand()));
		
	}



	private void addSpecificQuestButton() {
		itemBox.addButton(new CommandButton("Quest",new SelectAndStartCommand()));
		
	}


	private void addCampaignButton() {
		itemBox.addButton(new CommandButton("Campaign",new GoToMenuCommand(Menus.CAMPAIGN)));
		
	}
}
