package view.viewItems;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.IController;
import controller.UserInputController;
import controller.commands.AddTriggerToTriggerFieldCommand;
import model.Monster.Monster;
import model.Monster.MonsterSet;
import model.event.MonsterTurnTrigger;
import model.event.monster.DefaultZombieMoveTrigger;
import model.search.BasicToken;
import view.viewItems.ItemBox.ImageItem;
import view.viewItems.ItemBox.ItemInfoContainer;
import view.viewItems.ItemBox.ItemOptions;
import view.viewItems.ItemBox.SelectKind;

public class MonsterItem extends ShapeItem {

	private Monster monster;
	private MonsterTurnTrigger trig;

	
	public MonsterItem(Monster token) {
		super(token);
		monster=token;
		this.setName(token.getName());
		this.setIDName(token.getName());
		trig=token.getDefaultTrigger();
		
	}
	
	

	public MonsterTurnTrigger getTrig() {
		return trig;
	}



	public void setTrig(MonsterTurnTrigger trig) {
		this.trig = trig;
	}



	public Map<Integer,MonsterSet> getMap(){
		return monster.getMap();
	}

	@Override
	public SelectKind getKind() {
		// TODO Auto-generated method stub
		return SelectKind.MONSTER;
	}

	@Override
	public ImageItem getImageItem() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public ItemOptions getOption() {
		// TODO Auto-generated method stub
		return ItemOptions.Monster;
	}

	@Override
	public ImageItem clone() {
		// TODO Auto-generated method stub
		return new MonsterItem((Monster) item);
	}
	
	
	public void addMonsterSpecifics(ItemInfoContainer itemInfoText) {
		
		addMonsterMovementEventButton(itemInfoText);
		System.out.println("button added");
	}

	private void addMonsterMovementEventButton(ItemInfoContainer itemInfoText) {
		
			JButton button=new JButton("add");
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					UserInputController control=UserInputController.getController();
					control.performCommand(new  AddTriggerToTriggerFieldCommand(trig,null));
				
				}
				
			});
			 JLabel field = new JLabel();
			 field.setText("Monster Movement");
			
			 field.setEnabled(false);
			 field.setBackground(Color.yellow);
			
	       // itemInfoText.add(lab);
	       // itemInfoText.add(field);
	        int w=itemInfoText.getWidth();
	        field.setSize(new Dimension(w/2-20,25));
	        button.setSize(new Dimension(w/2,25));
	        button.setPreferredSize(new Dimension((int)(w/2-20),25));
	        field.setPreferredSize(new Dimension(w/2,25));
	        button.setHorizontalAlignment(SwingConstants.RIGHT);
			itemInfoText.addPreButton(field,button);
			itemInfoText.revalidate();
			itemInfoText.repaint();
		
		
	}



	public int getMonsterLimit() {
		// TODO Auto-generated method stub
		return monster.getMonsterLimit();
	}
	

	
	
	

}
