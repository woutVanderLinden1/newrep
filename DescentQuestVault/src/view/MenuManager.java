package view;

import java.util.Stack;

import frame.MainFrame;
import view.Items.Map.ViewSquare;
import view.menu.MainMenu;
import view.menu.Menu;
import view.menu.Menus;
import view.menu.QuestEditorMenu;

public class MenuManager {

	private Stack<Menu> menuStack;
	private MainFrame frame;
	
	public MenuManager(MainFrame frame){
		this.frame=frame;
		menuStack=new Stack<Menu>();
		initialiseMenuStack();
	}
	
	private void initialiseMenuStack() {
		initialiseMenu(Menus.MAIN);
		refreshMenu();
	}


	public void GoToMenu(Menus newMenu){
		initialiseMenu(newMenu);
		refreshMenu();
	}
	
	public void goBackMenu(){
		menuStack.pop();
		refreshMenu();
	}

	private void initialiseMenu(Menus main) {
	
		
		Menu mainMenu=null;
		switch(main){
		case MAIN:
			mainMenu=new MainMenu(frame.getWidth(),frame.getHeight());
			
			break;
		case QUESTEDITOR:
			mainMenu=new QuestEditorMenu(frame.getWidth(),frame.getHeight());
			break;
		default:
			break;
		
		}
		menuStack.add(mainMenu);
		
	}
	private void refreshMenu(){
		frame.initialiseMenu(menuStack.peek());
	}

	public  ViewSquare getTileAt(int x, int y){
		return null;
	}

}
