package view.viewItems.ItemBox;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.IController;
import controller.UserInputController;
import controller.command.ClearEventBoxCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import model.generators.GeneratorItem;
import model.values.ValueItem;
import monsterEditor.MonsterEditorController;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.events.EventItem;
import view.events.TriggerItem;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;
import view.viewItems.TileItem;

public class MonsterInfoItemBox extends InfoItemBox {

	public MonsterEditorController control;
	public MonsterInfoItemBox(int width, int i, int k) {
		super(width, i, k);
		imagePanel.setPreferredSize(new Dimension(i-50,k));
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void refreshImage() {
		imagePanel.removeAll();
		itemInfoText.reset();
		if(selected.getHolded()==null) {
			System.out.println("holded is null");
			return;
		}
		System.out.println("here "+selected.getHolded());
		ImageItem item=selected.getHolded().getImageItem();
		int angle=0;
		SelectAble holded=selected.getHolded();
		boolean needsrescale=true;
		if(holded!=null) {
			showName(holded);
			showIDName(holded);
			switch(holded.getKind()) {
			case MONSTER:
				System.out.println("showingmonsterspecifics");
				showMonsterSpecifics((MonsterItem) holded.getImageItem());
				
				break;
			case TRIGGER:
				System.out.println("this is the cause "+holded);
				showEventSpecifics((TriggerItem) holded.getImageItem());
				break;
			case EVENT:
		
				showEventSpecifics((EventItem) holded.getImageItem());
				break;
				
			case VALUE:
				showCreateNew((ValueItem) holded);
				showEvents((ValueItem)holded);
				break;
				/*
			case TILEITEM:
				TileItem tile=((TileItem) holded);
				angle=tile.getAngle();
				
				break;
			case VIEWTILE:
				ViewTile tile2=((ViewTile) holded);
				angle=tile2.getAngle();
				showEvents(tile2);
		
				break;
			case DOOR:
				DoorItem door=((DoorItem) holded);
				angle=door.getAngle();
				break;
			case VIEWDOOR:
				ViewDoor viewdoor=((ViewDoor) holded);
				angle=viewdoor.getAngle();
				showEvents(viewdoor);
			
				break;
			case VIEWTOKEN:
				ViewToken viewtoken=((ViewToken) holded);
				angle=viewtoken.getAngle();
				showEvents(viewtoken);
				
				break;
				*/
			case GENERATOR:
				showCommands((GeneratorItem) holded);
				break;
				/*
			case VIEWMONSTER:
				showMonsterSpecifics((ViewMonster) holded);
				showEvents((ViewMonster) holded);
				break;
				*/
			default:
				needsrescale=false;
				//return;
				break;
			
			}
			
		}
		Image img = item.getImage();
		Image newimg=null;
		double max=  Math.max(item.getScaleWidth(), item.getScaleHeight());
		
		if(needsrescale) {
			switch( angle){
			case 0:
			case 180:
				//newimg = img.getScaledInstance( (int)(300*item.getScaleWidth()),(int) (300*item.getScaleHeight()),  java.awt.Image.SCALE_SMOOTH ) ;
				if(max!=0) {
					double factor= itemInfoSize/max;
					newimg = img.getScaledInstance( (int)(factor*(item.getScaleWidth())),(int)( factor*(item.getScaleHeight())),  java.awt.Image.SCALE_SMOOTH ) ;
					
				}
				else {
					newimg = img.getScaledInstance(itemInfoSize,itemInfoSize,  java.awt.Image.SCALE_SMOOTH ) ;
					
				}
				break;
			
			case 90:
			case 270:
				if(max!=0) {
					double factor= itemInfoSize/max;
					newimg = img.getScaledInstance( (int)(factor*(item.getScaleHeight())),(int)( factor*(item.getScaleWidth())),  java.awt.Image.SCALE_SMOOTH ) ;
					
				}
				else {
					newimg = img.getScaledInstance(itemInfoSize,itemInfoSize,  java.awt.Image.SCALE_SMOOTH ) ;
					
				}
				//newimg = img.getScaledInstance( (int)(300*item.getScaleHeight()),(int) (300*item.getScaleWidth()),  java.awt.Image.SCALE_SMOOTH ) ;
				break;
			
			}
		}
		else {
			newimg=img;
		}
		
	
		
	
		imagePanel.add(new JLabel(new ImageIcon(newimg)));
	}

	public void setController(MonsterEditorController userInput) {
		// TODO Auto-generated method stub
		control=userInput;
	}


	protected void showMonsterSpecifics(MonsterItem imageItem) {
		System.out.println("showing monster item specifics");
		imageItem.addMonsterSpecifics(itemInfoText);
		//itemInfoText.addPreButton(field,button);
		UserInputController control=UserInputController.getController();
		control.performCommand(new ClearEventBoxCommand());
		control.performCommand(new  AddTriggerToTriggerFieldCommand(imageItem.getTrig(),null));
		this.revalidate();
		this.repaint();
		
	}
	

}
