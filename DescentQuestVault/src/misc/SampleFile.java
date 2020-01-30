package misc;

import model.Hero.Hero;
import model.Hero.MikleoHero;

public class SampleFile extends BaseFile {

	public SampleFile() {
		super(2, 0,0,0,"test" );
		Hero her=new Hero("mikleo","Images//Hero//mikleo.png");
		Hero her2=new Hero("mikleo","Images//Hero//mikleo.png");
		
		this.addHero(her);
		this.addHero(her2);
		// TODO Auto-generated constructor stub
	}



}
