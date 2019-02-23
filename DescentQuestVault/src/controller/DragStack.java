package controller;

import view.viewItems.ItemBox.SelectAble;

//an element that holds what element is currently being dragged to another place
public class DragStack {
	private SelectAble tomove;

	public SelectAble getTomove() {
		return tomove;
	}

	public void setTomove(SelectAble tomove) {
		this.tomove = tomove;
	}

	public DragStack() {
		super();
		
	}


	

}
