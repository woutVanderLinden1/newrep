package model.event.advancedevents;

import model.event.advancedevents.peril.Peril;
import model.event.advancedevents.peril.TextPeril;

public enum StandardPerilGenre {
	RANDOM,
	ZOMBIERISE{
		/*
		public Peril getPeril() {
			//return new ZombiePeril();
		}
		*/
	},
	DAMAGE,
	BLOODFORBLOOD,
	SPIDERZ,
	IMPENDINGDOOM,
	CUSTOM{
		public Peril getPeril() {
			return new TextPeril();
		}
	};
	
	public Peril getPeril() {
		return null;
	}
}
