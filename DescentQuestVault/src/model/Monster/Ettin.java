package model.Monster;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import controller.IController;
import controller.UserInputController;
import controller.command.OpenMonsterEditorCommand;
import controller.commands.CreateNewOptionCommand;
import model.event.MonsterSpecial;
import model.event.MonsterTurnTrigger;
import model.event.Trigger;
import model.event.extraevents.TextOption;
import model.event.extraevents.TextTrigger;
import model.event.monster.DefaultZombieMoveTrigger;
import view.viewItems.ItemBox.ItemInfoContainer;

public class Ettin extends Monster {

	private int[][] shape;
	
	public Ettin() {
		super("ettin");
		int[][] mat ={ {1,1},{1,1}};
		map.put(2,new MonsterSet(0,1));
		map.put(3,new MonsterSet(1,0));
		map.put(4,new MonsterSet(1,1));
		shape=mat;
		this.addMonsterSpecial(new MonsterSpecial("damage"));
		defaultMovement=new DefaultZombieMoveTrigger(this);
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return  shape;
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 0.48;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 0.48;
	}

	@Override
	public int getLeftOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getRightOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBottomOff() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTopOff() {
		// TODO Auto-generated method stub
		return -3;
	}

	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		addOpenMonsterEditor(itemInfoText);
		super.addEventSpecifics(itemInfoText);
	}

	private void addOpenMonsterEditor(ItemInfoContainer itemInfoText) {
		JButton button=new JButton("open");
		Monster mon=this;
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserInputController control=UserInputController.getController();
				control.performCommand(new OpenMonsterEditorCommand(mon));
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("Monster Editor");
		
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
		
	}

	@Override
	public int getMonsterLimit() {
		// TODO Auto-generated method stub
		return 2;
	}


	

}
