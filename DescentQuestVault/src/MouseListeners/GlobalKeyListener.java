package MouseListeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GlobalKeyListener implements KeyListener {

	
	public static GlobalKeyListener keylisten;
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static KeyListener getKeyListener() {
		if(keylisten==null) {
			keylisten=new GlobalKeyListener();
		}
		return keylisten ;
	}

}
