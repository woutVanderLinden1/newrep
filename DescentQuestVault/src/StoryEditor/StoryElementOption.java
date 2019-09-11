package StoryEditor;

public enum StoryElementOption {

	Text{
		public void performAction(StoryEditorController control) {
			// TODO Auto-generated method stub
		//	control.addTextToTextFrameElement();
		}
	},
	Dialog{
		public void performAction(StoryEditorController control) {
			// TODO Auto-generated method stub
		//	control.addDialogToTextFrameElement();
		}
	},
	Option{
		public void performAction(StoryEditorController control) {
			// TODO Auto-generated method stub
		//	control.addOptionToTextFrameElement();
		}
	},
	Sound{
		public void performAction(StoryEditorController control) {
			// TODO Auto-generated method stub
		//	control.addSoundToTextFrameElement();
		}
	};

	public void performAction(StoryEditorController control) {
		// TODO Auto-generated method stub
		
	}
}
