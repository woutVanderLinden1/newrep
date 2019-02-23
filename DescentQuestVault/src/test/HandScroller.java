package test;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class HandScroller extends JFrame {

    public static void main(String[] args) {
        new HandScroller();
    }

    public HandScroller() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        final JPanel background = new JPanel();
        background.add(new JLabel("Hand"));
        background.add(new JLabel("Scroller"));
        background.add(new JLabel("Test"));
        background.add(new JLabel("Click"));
        background.add(new JLabel("To"));
        background.add(new JLabel("Scroll"));
       // background.setPreferredSize(new Dimension(1000,1000));
        final JScrollPane scrollPane = new JScrollPane(background);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JViewport viewPort = scrollPane.getViewport();
                Point vpp = viewPort.getViewPosition();
                vpp.translate(10, 10);
                background.scrollRectToVisible(new Rectangle(vpp, viewPort.getSize()));
            }
        };

        scrollPane.getViewport().addMouseListener(mouseAdapter);
        scrollPane.getViewport().addMouseMotionListener(mouseAdapter);

        setContentPane(scrollPane);
        setSize(60,100);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
