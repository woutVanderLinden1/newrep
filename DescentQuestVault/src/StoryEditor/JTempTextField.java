package StoryEditor;

import javax.swing.JTextField;

import frame.TemporaryAble;

public class JTempTextField extends JTextField implements TemporaryAble {

	@Override
	public boolean isTemporary() {
		// TODO Auto-generated method stub
		return false;
	}

}
