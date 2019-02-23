package model.generators;

import java.util.ArrayList;

import controller.commands.ICommand;
import controller.commands.genrator.GenerateEmptyModifierCommand;
import controller.commands.genrator.GenerateOneOffListModifierCommand;
import controller.commands.genrator.GenerateOrderedListModifierCommand;
import controller.commands.genrator.GenerateReducingRandomListModifierCommand;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class ModifierGeneratorItem extends GeneratorItem implements CommandHolder{
	
	private ArrayList<ICommand> commandlist=new ArrayList<ICommand>();

	public ModifierGeneratorItem(ModifierGenerator tile) {
		super(tile);
		commandlist.add(new GenerateEmptyModifierCommand());
		commandlist.add(new GenerateOneOffListModifierCommand());
		commandlist.add(new GenerateReducingRandomListModifierCommand());
		commandlist.add(new GenerateOrderedListModifierCommand());
		
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
