package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TestPanels {

	
	private JFrame testframe;
	private JLayeredPane pane;
	private JPanel panel1;
	private JPanel panel2;
	
	public static void main(String[] args) {
	
		TestPanels testPanels=new TestPanels();
		testPanels.initialise();
	}
	private void initialise() {
		// TODO Auto-generated method stub
		testframe.setVisible((true));
		
		testframe.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("mouseevent happened");
				// TODO Auto-generated method stub
				//pane.moveToBack(panel2);
				pane.moveToFront(panel2);
				//pane.remove(panel1);
				//pane.remove(panel2);
				//pane.add(panel2,0);
				//testframe.revalidate();
				//testframe.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}
	public TestPanels() {
		/*
		testframe=new JFrame();
		JLayeredPane pane = new JLayeredPane();
		for (int i = 0; i < 4; i++) {
		    JLabel lbl = new JLabel("Jlabel-"+(i+1));

		    lbl.setOpaque(true);
		    lbl.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mousePressed(MouseEvent e) {
		            if (pane.getPosition(lbl) != 0) 
		                pane.moveToFront(lbl);
		        }
		    });
		    lbl.setFont(lbl.getFont().deriveFont(48f));
		    lbl.setForeground(new Color(i*10, i*50, i*50));
		    lbl.setBorder(new LineBorder(Color.BLACK, 2));
		    lbl.setBounds(i*100, i*25, lbl.getPreferredSize().width, lbl.getPreferredSize().height);
		    pane.add(lbl, 0, new Integer(i+1));
		}
		testframe.add(pane);
		*/
		
		testframe=new JFrame();
		testframe.setPreferredSize(new Dimension(1000,1000));
		testframe.setSize(1000,1000);
		pane=new JLayeredPane();
		pane.setPreferredSize(new Dimension(1000,1000));
		pane.setSize(1000,1000);
		testframe.add(pane);
		panel1=new JPanel();
		panel1.setLocation(new Point(10,10));
		panel1.setBackground(Color.BLUE);
		panel1.setPreferredSize(new Dimension(100,100));
		panel1.setSize(100,100);
		panel2=new JPanel();
		panel2.setLocation(new Point(20,20));
		panel2.setBackground(Color.YELLOW);
		panel2.setPreferredSize(new Dimension(100,100));
		panel2.setSize(100,100);
		pane.add(panel1,0,2);
		pane.add(panel2,0,1);
		
	}
	
	
	
}
