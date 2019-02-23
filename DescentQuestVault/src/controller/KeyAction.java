package controller;
/**
 * a class that denotates a keyaction
 * asks for a mainact that is a special act for the window
 * and an optional char. The char is needed if it was not recognized as
 * a mainact
 * @author r0454860
 *
 */
public class KeyAction {

	private MainKeyAction mainact;
	private char charpressed;
	
	public KeyAction(MainKeyAction ctrld) {
		mainact=ctrld;
	}
	
	public KeyAction(MainKeyAction c, char d) {
		mainact=c;
		charpressed=d;
	}

	public MainKeyAction getMainact() {
		return mainact;
	}
	public void setMainact(MainKeyAction mainact) {
		this.mainact = mainact;
	}
	public char getCharpressed() {
		if(mainact!=MainKeyAction.CHAR){
			return ' ';
		}
		return charpressed;
	}
	public void setCharpressed(char charpressed) {
		this.charpressed = charpressed;
	}

	public static KeyAction toKeyAction(int id,String keyText, int keyCode, int modifier, char keyChar) {
		MainKeyAction act=MainKeyAction.toMainKeyAction(id,keyText,keyCode,modifier,keyChar);
		// TODO Auto-generated method stub
		return new KeyAction(act,keyChar);
	}
}
