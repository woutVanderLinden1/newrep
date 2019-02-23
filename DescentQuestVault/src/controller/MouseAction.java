package controller;

import java.awt.event.MouseEvent;

/**
 * keeps the different mouseactions possible and usable for this project
 * @author r0454860
 *
 */
public enum MouseAction {

	MOUSECLICKED,
	MOUSEPRESSED,
	MOUSEDRAGGED,
	MOUSERELEASED,
	MOUSEDOUBLECLICKED,
	NONE;

	public static MouseAction toMouseAction(int id, int clickCount) {
		switch(id){
			case MouseEvent.MOUSE_CLICKED:
				if(clickCount==1){
					return MOUSECLICKED;
				}
				else{
					return MOUSEDOUBLECLICKED;
				}
			
			case MouseEvent.MOUSE_PRESSED:
				return MOUSEPRESSED;
			case MouseEvent.MOUSE_RELEASED:
				return MOUSERELEASED;
			case MouseEvent.MOUSE_DRAGGED:
				return MOUSEDRAGGED;
			
		}
		// TODO Auto-generated method stub
		return NONE;
	}
}
