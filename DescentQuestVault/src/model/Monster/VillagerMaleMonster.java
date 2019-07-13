package model.Monster;

import model.event.monster.DefaultZombieMoveTrigger;

public class VillagerMaleMonster extends Monster {

	private int [][] shape;
	public VillagerMaleMonster() {
		super("villagermale");
		int[][] mat ={{1}};
		shape=mat;
		defaultMovement=new DefaultZombieMoveTrigger(this);
		
		
	}

	@Override
	public int getMonsterLimit() {
		// TODO Auto-generated method stub
		return 12;
	}

	@Override
	public int[][] getShape() {
		// TODO Auto-generated method stub
		return shape;
	}

	@Override
	public double getScaleWidth() {
		// TODO Auto-generated method stub
		return 0.25;
	}

	@Override
	public double getScaleHeight() {
		// TODO Auto-generated method stub
		return 0.25;
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
		return 0;
	}

}
