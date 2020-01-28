package StoryEditor;

import model.event.Trigger;

public interface AddStoryEventListener {

	void storyEventAdded(StoryEvent added);

	void storyEventRemoved(StoryEvent removed);

	void triggerAdded(Trigger added);

	void triggerRemoved(Trigger removed);

}
