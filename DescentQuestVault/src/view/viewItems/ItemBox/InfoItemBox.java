package view.viewItems.ItemBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import controller.IController;
import controller.UserInputController;
import controller.command.ClearEventBoxCommand;
import controller.command.game.MakeInvisibleCommand;
import controller.commands.AddEventToTriggerFieldCommand;
import controller.commands.AddTriggerToTriggerFieldCommand;
import controller.commands.ICommand;
import controller.commands.makeVisibleCommand;
import controller.commands.select.DeleteSelectedCommand;
import controller.commands.select.RotateSelectedCommand;
import frame.SubContainer;
import misc.ActivateAble;
import misc.NameHolder;
import model.SelectedArea;
import model.event.Event;
import model.event.Trigger;
import model.event.Univent;
import model.generators.CommandHolder;
import model.generators.GeneratorItem;
import model.values.ValueItem;
import view.Items.Map.EventHolder;
import view.Items.Map.MapItem;
import view.Items.Map.ViewDoor;
import view.Items.Map.ViewMonster;
import view.Items.Map.ViewSquare;
import view.Items.Map.ViewTile;
import view.Items.Map.ViewToken;
import view.events.EventItem;
import view.events.TriggerItem;
import view.viewItems.DoorItem;
import view.viewItems.MonsterItem;
import view.viewItems.TileItem;

public class InfoItemBox extends SubContainer implements SelectedChangeListener {

	protected SelectedArea selected=new SelectedArea();
	protected SubContainer imagePanel;
	protected ItemInfoContainer itemInfoText;
	protected int itemInfoSize;
	
	public InfoItemBox(int width, int i) {
		super(width,i);
		this.setBackground(Color.YELLOW);
		imagePanel=new SubContainer(width-20,350);
		
		this.add(imagePanel);
		addRotateImageButton();
		addDeleteImageButton();
		imagePanel.addMouseListener(new SelectedImageListener());
		imagePanel.addMouseMotionListener(new SelectedImageListener());
	
		
		
		imagePanel.setLayout(new GridBagLayout());
		selected.addSelectedChangeListener(this);
	
		
		itemInfoText=new ItemInfoContainer(new Dimension(width-20,400));
		//itemInfoText.setLayout(  new GridLayout(0,2));
		itemInfoText.setBackground(Color.yellow);
		itemInfoText.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		JScrollPane pane=new JScrollPane(itemInfoText,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        	    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(new Dimension(width,this.getHeight()-440));
		pane.setLocation(5,375);
		itemInfoSize=300;
		this.add(pane);
	//	selectedChangeListeners.add(selectedArea)
	}

	public InfoItemBox(int width, int i, int k) {
		super(width,i);
		itemInfoSize=k*3/4;
		this.setBackground(Color.YELLOW);
		imagePanel=new SubContainer(width-40,k);
		
		this.add(imagePanel);
		addRotateImageButton();
		addDeleteImageButton();
		imagePanel.addMouseListener(new SelectedImageListener());
		imagePanel.addMouseMotionListener(new SelectedImageListener());
	
		
		
		imagePanel.setLayout(new GridBagLayout());
		selected.addSelectedChangeListener(this);
	
		
		itemInfoText=new ItemInfoContainer(new Dimension(width-20,400));
		//itemInfoText.setLayout(  new GridLayout(0,2));
		itemInfoText.setBackground(Color.yellow);
		itemInfoText.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		JScrollPane pane=new JScrollPane(itemInfoText,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        	    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setPreferredSize(new Dimension(width,this.getHeight()-350));
		pane.setLocation(5,375);
		
		this.add(pane);
	}

	private void addRotateImageButton(){
		JButton rotateImageButton=new JButton("rotate");
		rotateImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RotateSelectedCommand comm=new RotateSelectedCommand();
				UserInputController.getController().performCommand(comm);
			}
		});
		rotateImageButton.setLocation(40,360);
		this.add(rotateImageButton);
	}
	private void addDeleteImageButton() {
		JButton deleteImageButton=new JButton("delete");
		deleteImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("selected will be deleted");
				DeleteSelectedCommand comm=new DeleteSelectedCommand();
				UserInputController.getController().performCommand(comm);
			}
		});
		this.add(deleteImageButton);
		deleteImageButton.setLocation(80,360);
		
	}

	public ICommand getSelectedCommand(ViewSquare tile) {
	
		return selected.generateDropCommand(tile);
	}

	public void setSelected(SelectAble item) {
		imagePanel.removeAll();
		//selected.setAngle(0);
		
		selected.setHolded(item);
		System.out.println("selected set to new selected");
		refreshImage();
		
	}
	
	

	public void refreshImage() {
		imagePanel.removeAll();
		System.out.println("resetti");
		itemInfoText.reset();
		if(selected.getHolded()==null) {
			System.out.println("holded is null");
			return;
		}
		System.out.println("here "+selected.getHolded());
		ImageItem item=selected.getHolded().getImageItem();
		int angle=0;
		SelectAble holded=selected.getHolded();
		boolean needsrescale=true;
		UserInputController control=UserInputController.getController();
		if(holded!=null) {
			showName(holded);
			showIDName(holded);
			switch(holded.getKind()) {
			case MONSTER:
				System.out.println("showingmonsterspecifics");
				showMonsterSpecifics((MonsterItem) holded.getImageItem());
				break;
			case TRIGGER:
				showMakeInvisible(holded);
				showEventSpecifics((TriggerItem) holded.getImageItem());
				break;
			case EVENT:
				showMakeInvisible(holded);
				showEventSpecifics((EventItem) holded.getImageItem());
				break;
			case VALUE:
				showCreateNew((ValueItem) holded);
				showEvents((ValueItem)holded);
				break;
			case TILEITEM:
				TileItem tile=((TileItem) holded);
				angle=tile.getAngle();
				
				break;
			case VIEWTILE:
				ViewTile tile2=((ViewTile) holded);
				angle=tile2.getAngle();
				showEvents(tile2);
		
				break;
			case DOOR:
				DoorItem door=((DoorItem) holded);
				angle=door.getAngle();
				break;
			case VIEWDOOR:
				ViewDoor viewdoor=((ViewDoor) holded);
				angle=viewdoor.getAngle();
				showEvents(viewdoor);
				addActivations(viewdoor);
				break;
			case VIEWTOKEN:
				ViewToken viewtoken=((ViewToken) holded);
				angle=viewtoken.getAngle();
				showEvents(viewtoken);
				addActivations(viewtoken);
				break;
			case GENERATOR:
				showCommands((GeneratorItem) holded);
				break;
			case VIEWMONSTER:
				showMonsterSpecifics((ViewMonster) holded);
				showEvents((ViewMonster) holded);
				break;
			default:
				needsrescale=false;
				//return;
				break;
			
			}
			
		}
		Image img = item.getImage();
		Image newimg=null;
		double max=  Math.max(item.getScaleWidth(), item.getScaleHeight());
		
		if(needsrescale) {
			switch( angle){
			case 0:
			case 180:
				//newimg = img.getScaledInstance( (int)(300*item.getScaleWidth()),(int) (300*item.getScaleHeight()),  java.awt.Image.SCALE_SMOOTH ) ;
				if(max!=0) {
					double factor= itemInfoSize/max;
					newimg = img.getScaledInstance( (int)(factor*(item.getScaleWidth())),(int)( factor*(item.getScaleHeight())),  java.awt.Image.SCALE_SMOOTH ) ;
					
				}
				else {
					newimg = img.getScaledInstance(itemInfoSize,itemInfoSize,  java.awt.Image.SCALE_SMOOTH ) ;
					
				}
				break;
			
			case 90:
			case 270:
				if(max!=0) {
					double factor= itemInfoSize/max;
					newimg = img.getScaledInstance( (int)(factor*(item.getScaleHeight())),(int)( factor*(item.getScaleWidth())),  java.awt.Image.SCALE_SMOOTH ) ;
					
				}
				else {
					newimg = img.getScaledInstance(itemInfoSize,itemInfoSize,  java.awt.Image.SCALE_SMOOTH ) ;
					
				}
				//newimg = img.getScaledInstance( (int)(300*item.getScaleHeight()),(int) (300*item.getScaleWidth()),  java.awt.Image.SCALE_SMOOTH ) ;
				break;
			
			}
		}
		else {
			newimg=img;
		}
		
	
		
		showSquareList();
		imagePanel.add(new JLabel(new ImageIcon(newimg)));
	}



	

	private void addActivations(ActivateAble viewdoor) {
		// TODO Auto-generated method stub
		viewdoor.InitialiseActivation(itemInfoText);
	}

	protected void showMonsterSpecifics(MonsterItem imageItem) {
		System.out.println("showing monster item specifics");
		/*
		imageItem.addMonsterSpecifics(itemInfoText);
		//itemInfoText.addPreButton(field,button);
		UserInputController control=UserInputController.getController();
		control.performCommand(new  AddTriggerToTriggerFieldCommand(imageItem.getTrig(),null));
		this.revalidate();
		this.repaint();
		*/
		
	}

	protected void showCommands(CommandHolder holded) {
		// TODO Auto-generated method stub
		for(ICommand comm:holded.getCommands()) {
			JButton button=new JButton("perform");
			button.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					UserInputController control=UserInputController.getController();
					control.performCommand(comm);
				
				}
				
			});
			 JLabel field = new JLabel();
			 field.setText(comm.getStringName());
			
			 field.setEnabled(false);
			 field.setBackground(Color.yellow);
			
	       // itemInfoText.add(lab);
	       // itemInfoText.add(field);
	        int w=itemInfoText.getWidth();
	        field.setSize(new Dimension(w/2-20,25));
	        button.setSize(new Dimension(w/2,25));
	        button.setPreferredSize(new Dimension((int)(w/2-20),25));
	        field.setPreferredSize(new Dimension(w/2,25));
	        button.setHorizontalAlignment(SwingConstants.RIGHT);
			itemInfoText.addPreButton(field,button);
			
		}
		this.revalidate();
		this.repaint();
	}

	protected void showEventSpecifics(ImageItem holded) {
		System.out.println("showing event specifics");
		holded.addEventSpecifics(itemInfoText);
		//itemInfoText.addPreButton(field,button);
		this.revalidate();
		this.repaint();
	}
	private void showMonsterSpecifics(ViewMonster holded) {
		System.out.println("showing event specifics");
		holded.addMonsterSpecifics(itemInfoText);
		//itemInfoText.addPreButton(field,button);
		this.revalidate();
		this.repaint();
	}

	

	protected void showCreateNew(ValueItem holded) {
		// TODO Auto-generated method stub
				JButton button=new JButton("new");
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						UserInputController control=UserInputController.getController();
						control.performCommand(holded.getNewCreationCommand());
					
					}
					
				});
				 JLabel field = new JLabel();
				 field.setText("create new");
				
				 field.setEnabled(false);
				 field.setBackground(Color.yellow);
				
		       // itemInfoText.add(lab);
		       // itemInfoText.add(field);
		        int w=itemInfoText.getWidth();
		        field.setSize(new Dimension(w/2-20,25));
		        button.setSize(new Dimension(w/2,25));
		        button.setPreferredSize(new Dimension((int)(w/2-20),25));
		        field.setPreferredSize(new Dimension(w/2,25));
		        button.setHorizontalAlignment(SwingConstants.RIGHT);
				itemInfoText.addPreButton(field,button);
				this.revalidate();
				this.repaint();
		
	}

	private void showSquareList() {
		// TODO Auto-generated method stub
		
	}

	protected void showEvents(EventHolder holder) {
		ArrayList<Univent> eventlist=holder.getEvents();
		
		for(Univent vent:eventlist) {
			JButton button=new JButton("add");
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					UserInputController control=UserInputController.getController();
					switch(vent.getKind()) {
				
					case EVENT:
						ICommand command=new AddEventToTriggerFieldCommand((Event) vent,null);
						
						control.performCommand(command);
						break;
				
					case TRIGGER:
						ICommand command2=new AddTriggerToTriggerFieldCommand((Trigger) vent,null);
						
						control.performCommand(command2);
						break;
			
					default:
						break;
					
					}
					
					
				}
				
			});
			 JLabel field = new JLabel();
			 field.setText(vent.getName());
			
			 field.setEnabled(false);
			 field.setBackground(Color.yellow);
			
	       // itemInfoText.add(lab);
	       // itemInfoText.add(field);
	        int w=itemInfoText.getWidth();
	        field.setSize(new Dimension(w/2-20,25));
	        button.setSize(new Dimension(w/2,25));
	        button.setPreferredSize(new Dimension((int)(w/2-20),25));
	        field.setPreferredSize(new Dimension(w/2,25));
	        button.setHorizontalAlignment(SwingConstants.RIGHT);
			itemInfoText.addPreButton(field,button);
		}
		 
	}
	private void showMakeInvisible(SelectAble viewtoken) {
		// TODO Auto-generated method stub
		JCheckBox button=new JCheckBox();
		button.addActionListener(new ActionListener() {
			boolean ischeked=false;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ischeked=!ischeked;
				if(ischeked) {
					UserInputController control=UserInputController.getController();
					ICommand command2=new MakeInvisibleCommand(viewtoken);
					
					control.performCommand(command2);
				}
				else {
					UserInputController control=UserInputController.getController();
					ICommand command2=new makeVisibleCommand(viewtoken);
					
					control.performCommand(command2);
				
				}
				
			}
			
		});
		 JLabel field = new JLabel();
		 field.setText("make invisible");
		
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        field.setSize(new Dimension(w/2-20,25));
        button.setSize(new Dimension(w/2,25));
        button.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        button.setHorizontalAlignment(SwingConstants.RIGHT);
		itemInfoText.addPreChekBox(field,button);
	}

	protected void showIDName(NameHolder holder) {
		//JPanel namepanel=new JPanel();
		//namepanel.setSize(this.getWidth(),this.getHeight());
		JLabel lab=new JLabel("IDname: ");
		 JTextField field = new JFormattedTextField();
		 field.setText(holder.getIDName());
		 field.setColumns(10);
		 field.setEnabled(false);
		 field.setBackground(Color.yellow);
		 field.setDisabledTextColor(Color.BLACK);
        field.addPropertyChangeListener("IDname",new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				
				
			}
        	
        });
       // itemInfoText.add(lab);
       // itemInfoText.add(field);
        int w=itemInfoText.getWidth();
        lab.setSize(new Dimension(w/2-20,25));
        field.setSize(new Dimension(w/2,25));
        lab.setPreferredSize(new Dimension((int)(w/2-20),25));
        field.setPreferredSize(new Dimension(w/2,25));
        lab.setHorizontalAlignment(SwingConstants.RIGHT);
        
        itemInfoText.addPreText(lab,field);
     
	
	}
	
	
	

	protected void showName(NameHolder holder) {
		JLabel lab=new JLabel("name: ");
		 JTextField field = new JFormattedTextField();
		 System.out.println("this is the name of this "+holder+ "   h "+holder.getName());
		 field.setName(holder.getName());
		 field.setColumns(10);
		 field.setText(holder.getName());
		 field.getDocument().addDocumentListener(new DocumentListener() {
			  public void changedUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void removeUpdate(DocumentEvent e) {
			    warn();
			  }
			  public void insertUpdate(DocumentEvent e) {
			    warn();
			  }

			  public void warn() {
				 // System.out.println("changedname "+field.getText());
					holder.changeName((String)field.getText());
			  }
			});
		 field.addPropertyChangeListener("name",new PropertyChangeListener() {

				@Override
				public void propertyChange(PropertyChangeEvent arg0) {
					System.out.println(field.getText());
					holder.changeName((String)field.getText());
					
				}
	        	
	        });
		
      // itemInfoText.add(lab);
      // itemInfoText.add(field);
       int w=itemInfoText.getWidth();
       lab.setSize(new Dimension(w/2-20,25));
       field.setSize(new Dimension(w/2,25));
       lab.setPreferredSize(new Dimension((int)(w/2-20),25));
       field.setPreferredSize(new Dimension(w/2,25));
       lab.setHorizontalAlignment(SwingConstants.RIGHT);
       
       itemInfoText.addPreText(lab,field);
    
	}
	
	private void showMakeInvisible() {
		//show wether to make it invisble or not
	}

	public void rotateSelected() {
		imagePanel.removeAll();
		SelectAble holded=selected.getHolded();
		selected.rotate();
		refreshImage();
		
	}

	public boolean hasSelected() {
		// TODO Auto-generated method stub
		return selected.hasSelected();
	}

	public SelectAble getSelected() {
		// TODO Auto-generated method stub
		return selected.getHolded();
	}

	public SelectedArea getSelectedArea() {
		// TODO Auto-generated method stub
		return selected;
	}

	@Override
	public void notify(SelectAble holded) {
		
	
		refreshImage();
	}

	public void removeSelected() {
		// TODO Auto-generated method stub
		if(selected.getHolded()!=null) {
			selected.getHolded().deselect();
		}

		selected.setHolded(null);
		imagePanel.removeAll();
	}

	public void refreshSelected() {
		// TODO Auto-generated method stub
		refreshImage();
	}
	
	
	
	
}
