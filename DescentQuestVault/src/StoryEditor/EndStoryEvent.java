package StoryEditor;

public class EndStoryEvent extends StoryTextEvent {

	public EndStoryEvent() {
		super();
		this.name="End story";
		this.setIDName(name);
		this.setStoryKind(StoryEventKind.END);
	}

}
