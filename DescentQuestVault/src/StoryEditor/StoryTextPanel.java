package StoryEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class StoryTextPanel  extends JTextArea {


	public StoryTextPanel(TextElement ment) {
		this.setBackground(new Color(255,222,173));
		this.setFont(new Font("descentquestbuilderfont",Font.PLAIN, 20));
		this.setText(ment.getText());
		StoryTextPanel thisthing=this;
		this.setMargin( new Insets(10,10,10,10) ); 
		this.setPreferredSize(new Dimension(100,800));
		setLineWrap(true);
		setWrapStyleWord(true);
		Border border = BorderFactory.createLineBorder(new Color(139,69,19));
		this.setBorder(BorderFactory.createCompoundBorder(border,BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		this.getDocument().addDocumentListener(new DocumentListener() {
	
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				warn();
				
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				warn();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				warn();
				
			}
			private void warn() {
				ment.setText(thisthing.getText());
				
			}
		});
		//area.setSize(200,400);
	
		//JScrollPane pan=new JScrollPane(this);
		
		
		// TODO Auto-generated constructor stub
	}

}
