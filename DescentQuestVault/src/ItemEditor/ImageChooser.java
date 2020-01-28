package ItemEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;

import controller.UserInputController;
import controller.commands.Utils;
import frame.SubContainer;

public class ImageChooser extends SubContainer{

	private JPanel imagepanel;
	private String currentimagestring;
	

	public ImageChooser(int i, int j) {
		super(i,j);
		this.setBackground(Color.blue);
	}

	public ImageChooser(Dimension dimension) {
		super(dimension);
		this.setBackground(Color.blue);
		 this.setLayout(new GridBagLayout());
		 GridBagConstraints c = new GridBagConstraints();
		
		 
		 
		imagepanel=new JPanel();
		imagepanel.setBackground(Color.LIGHT_GRAY);
		 c.insets = new Insets(5,5,20,0);  //top padding
		
		 
		 c.weightx = 0.5;
		 c.gridx = 0;
		 c.gridy = 0;
		 c.gridwidth = 3;
		 this.add(imagepanel, c);
		 imagepanel.setSize(new Dimension(360,360));
		 imagepanel.setPreferredSize(new Dimension(360,360));
		 imagepanel.setMaximumSize(new Dimension(360,360));
		 JButton loadbutton=new JButton("load");
		 loadbutton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				File image=loadSavedItem();
				if(image==null) {
					return;
				}
				//File saveTo=new File(System.getProperty("user.dir")+"/Items//"+image.getName());
				currentimagestring=System.getProperty("user.dir")+"/Items//"+image.getName();
				 Path copied = Paths.get(System.getProperty("user.dir")+"/Items//"+image.getName());
				    Path originalPath = image.toPath();
				    try {
						Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
						setImage(System.getProperty("user.dir")+"/Items//"+image.getName());
				    } catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				  
				    
			}
			 
		 });
		 c.insets = new Insets(5,5,20,0);  //top padding
		 //c.fill = GridBagConstraints.HORIZONTAL;
		 
		 c.weightx = 0.5;
		 c.gridx = 0;
		 c.gridy = 1;
		 this.add(loadbutton, c);
		
		// TODO Auto-generated constructor stub
	}
	
	protected File loadSavedItem() {
		UserInputController.renew();
		final JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
		fc.addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File f) {
			
			    if (f.isDirectory()) {
			        return true;
			    }
				return true;
/*
			    String extension = Utils.getExtension(f);
			    if (extension != null) {
			        if (extension.equals(Utils.ser)){
			                return true;
			        } else {
			            return false;
			        }
			    }

			    return false;
			    */
			}

			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return ".ser";
			}
		});
		File workingDirectory = new File(System.getProperty("user.home"));
		fc.setCurrentDirectory(workingDirectory);
		int returnVal = fc.showOpenDialog(this);
		File f=fc.getSelectedFile();
		return f;
	}

	public void setImage(String imgstring) {
		if(imgstring==null|| imgstring.equals("")) {
			return;
		}
		imagepanel.removeAll();
		currentimagestring=imgstring;
		BufferedImage img;
		try {
			System.out.println(imgstring);
			img = ImageIO.read(new File(imgstring));
			Image newimg = img.getScaledInstance(360,360,  java.awt.Image.SCALE_SMOOTH ) ;
			JLabel picLabel = new JLabel(new ImageIcon(newimg));
			imagepanel.add(picLabel);
			this.revalidate();
			this.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public String getImageString() {
		// TODO Auto-generated method stub
		return  currentimagestring;
	}

}
