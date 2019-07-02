package model;

import java.util.ArrayList;
import java.util.Arrays;

public enum Condition {
	Disease,
	Poison,
	Stun,
	Freeze,
	Cursed,
	Blinded,
	Bleed,
	Imobilised;
	
	public static ArrayList<Condition> getConditions(){
		return new ArrayList<Condition>(Arrays.asList(Disease,
				Poison,
				Stun,
				Freeze,
				Cursed,
				Blinded,
				Bleed,
				Imobilised));
	}
}
