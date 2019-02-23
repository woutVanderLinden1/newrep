package controller.turns;

public abstract class Turn {
	private TurnHolder turnhold;

	public Turn(TurnHolder turnhold) {
		super();
		this.turnhold = turnhold;
	}

	public TurnHolder getTurnhold() {
		return turnhold;
	}

	public void setTurnhold(TurnHolder turnhold) {
		this.turnhold = turnhold;
	}

	
	public void performTurn() {
		turnhold.startTurn();
	}
	public abstract TurnKind getTurnKind();
}
