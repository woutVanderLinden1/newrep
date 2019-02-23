package controller.turns;

public class HeroTurn extends Turn {

	public HeroTurn(TurnHolder turnhold) {
		super(turnhold);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TurnKind getTurnKind() {
		// TODO Auto-generated method stub
		return TurnKind.HEROES;
	}
}
