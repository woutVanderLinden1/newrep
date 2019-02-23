package model.generators;

import java.util.ArrayList;

import controller.commands.ICommand;
import controller.commands.genrator.GenerateEmptyModifierCommand;
import model.Item;
import model.event.Univent;
import view.Items.Map.EventHolder;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class ModifierGenerator extends Generator {

	
	
	
	public ModifierGenerator() {
		super("modgenerator");
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemOptions getGeneratorKind() {
		// TODO Auto-generated method stub
		return ItemOptions.Event;
	}






}
