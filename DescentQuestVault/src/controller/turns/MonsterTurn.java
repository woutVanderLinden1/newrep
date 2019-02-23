package controller.turns;

public class MonsterTurn extends Turn {

	public MonsterTurn(TurnHolder turnhold) {
		super(turnhold);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TurnKind getTurnKind() {
		// TODO Auto-generated method stub
		return TurnKind.MONSTER;
	}

}
