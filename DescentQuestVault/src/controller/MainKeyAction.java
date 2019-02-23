package controller;

import java.awt.event.KeyEvent;


public enum MainKeyAction {

	TAB,
	CHAR,
	DELETE,
	ENTER,
	CTRLD,
	CTRLN,
	NONE,
	BACKSPACE, 
	UP,
	DOWN,
	LEFT,
	RIGHT,
	CTRLENTER,
	SPACE,
	U,
	D;

	public static MainKeyAction toMainKeyAction(int id,String keyText, int keyCode, int modifier,char keyChar) {
		System.out.println("code "+keyCode);
		
		switch(id){
		case KeyEvent.KEY_TYPED:
			Character cha=keyChar;
			if(!cha.isAlphabetic(cha)&&!cha.isDigit(cha)&&!cha.equals(':')&&!cha.equals(' ')&&!cha.equals('_')&&!cha.equals('(')&&!cha.equals(')')&&!cha.equals(',')){
				return NONE;
			}
		;
			
			switch(keyCode){
			case 8:
				return NONE;
			}
			return CHAR;
		case KeyEvent.KEY_PRESSED:
		
			if(modifier==2){
			
				switch(keyCode){
				case 68: 
					return CTRLD;
				case 78: 
				
					return CTRLN;
				case 10:
					return CTRLENTER;
				}
			}
			else {
				switch(keyCode) {
				case 32:
					return SPACE;
				case 38:
					return UP;
				case 40:
					return DOWN;
				case 37:
					return LEFT;
				case 39:
					return RIGHT;
				case 10:
					return ENTER;
				case 8:
					return BACKSPACE;
				case 127:
					return DELETE;
				case 85:
					return U;
				case 68:
					return D;
				
					
				}
			}
			
			
		}
		// TODO Auto-generated method stub
		return NONE;
	}
	
	
	
}
