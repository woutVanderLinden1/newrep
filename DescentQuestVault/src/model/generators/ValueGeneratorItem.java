package model.generators;

import java.util.ArrayList;

import controller.command.CreateIntegerValueCommand;
import controller.commands.CreateBooleanValueCommand;
import controller.commands.ICommand;
import controller.commands.genrator.GenerateTextOptionCommand;
import controller.commands.genrator.GenerateTextStopCommand;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class ValueGeneratorItem extends GeneratorItem implements CommandHolder{

	private ArrayList<ICommand> commandlist=new ArrayList<ICommand>();

	public ValueGeneratorItem(ValueGenerator tile) {
		super(tile);
		commandlist.add( new CreateBooleanValueCommand());
		commandlist.add(new CreateIntegerValueCommand());
		commandlist.add(new CreateGainHeroExpCommand());
		commandlist.add(new CreateObtainItemCommand());
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.GENERATOR;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ItemOptions getOption() {
		// TODO Auto-generated method stub
		return ItemOptions.Generator;
	}

	@Override
	public ImageItem clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<ICommand> getCommands() {
		// TODO Auto-generated method stub
		return commandlist;
	}

}
