package StoryEditor;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.UserInputController;
import controller.command.ClearEventBoxCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import model.generators.GeneratorItem;
import model.values.ValueItem;
import monsterEditor.MonsterEditorController;
import view.events.EventItem;
import view.events.TriggerItem;
import view.viewItems.MonsterItem;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.InfoItemBox;
import view.viewItems.ItemBox.SelectAble;

public class StoryInfoItemBox extends InfoItemBox {

	public StoryEditorController control;
	public StoryInfoItemBox(int width, int i) {
		super(width, i);
		// TODO Auto-generated constructor stub
	}

	public StoryInfoItemBox(int i, int j, int k) {
		super(i,j,k);
		imagePanel.setPreferredSize(new Dimension(i-50,k));	
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
			case HERO:
				break;
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
			case ARROW:
				showArrowSpecifics((ViewArrow) holded);
				break;
			default:
				needsrescale=false;
				//return;
				break;
			
			}
			
		}
		if(item!=null) {
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
	
	}

	public void setController(StoryEditorController userInput) {
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
