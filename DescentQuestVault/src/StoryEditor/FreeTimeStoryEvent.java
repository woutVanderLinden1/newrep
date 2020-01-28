package StoryEditor;

import ItemEditor.ActionTaker;
import controller.UserInputController;
import controller.stack.EndGameCommand;
import model.event.Univent;
import view.viewItems.ItemBox.ItemInfoContainer;

public class FreeTimeStoryEvent extends StoryEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7568355528472025290L;
	private int amount;
	private int currentamount;
	private static int nr=0;
	
	public FreeTimeStoryEvent(int amount) {
		this.amount=amount;
		this.name="FreeTimeStoryEvent"+ nr++;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public Univent copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void triggerStoryEvent(ProgressStatus status) {
		currentamount=amount;
		
		
		UserInputController control=UserInputController.getController();
		control.addNextFreeTimeListener(new FreeTimeEndListener() {
		
		

			@Override
			public void FreeTimeEnded() {
				// TODO Auto-generated method stub
				nextFreeTime();
			}
		});
		control.beginFreeTimeEvent();
		//control.addNextTextListener(this);
		
		//this.nextText();
		
		this.nextFreeTime();
		
	}
	private void nextFreeTime() {
		UserInputController control=UserInputController.getController();
		 if(currentamount==0) {

				
				control.endFreeTimeEvent();
				control.performCommand(new EndGameCommand());
				//control.endGame();
		 }
		 else {
			 System.out.println("in free time");
			 control.nextFreeTime(currentamount); 
		 }
		 currentamount--;
		
	}

	@Override
	public void addEventSpecifics(ItemInfoContainer itemInfoText) {
		itemInfoText.addNumericEditButton("Amount ", itemInfoText, amount, new ActionTaker<Integer>() {

			@Override
			public void perform(Integer value) {
				// TODO Auto-generated method stub
				setAmount(value);
			}
			
		});
		super.addEventSpecifics(itemInfoText);
		
	}
}
