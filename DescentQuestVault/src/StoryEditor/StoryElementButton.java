package StoryEditor;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import misc.Tools;

public class StoryElementButton extends JButton {


	
	private StoryElementOption opt;
	public StoryElementButton(StoryElementOption option) {
		opt=option;
		this.setVerticalTextPosition(SwingConstants.BOTTOM);
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		initialiseButton();
		
	}
	private void initialiseButton() {
		this.setName(opt.toString());
		this.setText(opt.toString());
		Image img=null;
		try {
			img = ImageIO.read(new File("Images//Story//"+opt.toString()+".png"));
			
			img=Tools.resize(50, 50, (BufferedImage) img);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setIcon(new ImageIcon(img));
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				StoryEditorController control=StoryEditorController.getStoryEditorController();
				opt.performAction(control);
				
			}
			
		});
	}

}
