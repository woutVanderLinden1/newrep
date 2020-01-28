package ItemEditor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.NumberFormat;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

import Shop.ItemShop.ImageHolder;
import StoryEditor.StoryTextElement;
import model.values.Modification;
import view.viewItems.ItemBox.InfoItemBox;
import view.viewItems.ItemBox.ItemInfoContainer;

public class SavedItem  implements Serializable,ImageHolder{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2725658341360225106L;
	private String name;
	private String imagestring;
	private String effectText;
	private int minfame;
	private int maxfame;
	private int goldprice;
	private int amount=1;
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDefaultamount() {
		return defaultamount;
	}

	public void setDefaultamount(int defaultamount) {
		this.defaultamount = defaultamount;
	}
	private int defaultamount=1;
	private boolean shopavailable;

	public boolean isShopavailable() {
		return shopavailable;
	}

	public void setShopavailable(boolean shopavailable) {
		this.shopavailable = shopavailable;
	}

	public String getImagestring() {
		return imagestring;
	}

	public SavedItem(String name, String imagestring, String effectText, int minfame, int maxfame, int goldprice) {
		super();
		this.name = name;
		this.imagestring = imagestring;
		this.effectText = effectText;
		this.minfame = minfame;
		this.maxfame = maxfame;
		this.goldprice = goldprice;
	}

	public void setImagestring(String imagestring) {
		this.imagestring = imagestring;
	}

	public String getEffectText() {
		return effectText;
	}

	public void setEffectText(String effectText) {
		this.effectText = effectText;
	}

	public int getMinfame() {
		return minfame;
	}

	public void setMinfame(int minfame) {
		this.minfame = minfame;
	}

	public int getMaxfame() {
		return maxfame;
	}

	public void setMaxfame(int maxfame) {
		this.maxfame = maxfame;
	}

	public int getGoldprice() {
		return goldprice;
	}

	public void setGoldprice(int goldprice) {
		this.goldprice = goldprice;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	public Icon getImageIcon() {
		BufferedImage img;
		if(imagestring==null||imagestring.equals("")) {
			return null;
		}
		try {
			img = ImageIO.read(new File(imagestring));
			Image newimg = img.getScaledInstance(100,100,  java.awt.Image.SCALE_SMOOTH ) ;
			
			return new ImageIcon(newimg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public void addItemSpecifics(ItemInfoContainer box) {
		box.addEditButton("name", box, name, new ActionTaker<String>() {

			@Override
			public void perform(String performstring) {
				// TODO Auto-generated method stub
				setName(performstring);
			}

		
			
		});
		box.addTextArea( box, effectText, new ActionTaker<String>() {

			@Override
			public void perform(String performstring) {
				// TODO Auto-generated method stub
				setEffectText(performstring);
			}

		
			
		});
		box.addNumericEditButton("minfame", box, minfame,new ActionTaker<Integer>() {
		

			@Override
			public void perform(Integer value) {
				// TODO Auto-generated method stub
				setMinfame(value);
			}
		});
		box.addNumericEditButton("maxfame", box, maxfame,new ActionTaker<Integer>() {
		

			@Override
			public void perform(Integer value) {
				// TODO Auto-generated method stub
				setMaxfame(value);
			}
		});
		box.addNumericEditButton("gold", box, goldprice,new ActionTaker<Integer>() {
	
			@Override
			public void perform(Integer value) {
				// TODO Auto-generated method stub
				setGoldprice(value);
			}
		});
		box.addNumericEditButton("amount", box, amount,new ActionTaker<Integer>() {
			
			@Override
			public void perform(Integer value) {
				// TODO Auto-generated method stub
				setAmount(value);
			}

			
		});
		box.addCheckBox("shop available", shopavailable,new ActionTaker<Boolean>() {
			
			@Override
			public void perform(Boolean value) {
				// TODO Auto-generated method stub
				shopavailable=value;
			}
		});
		
		//addEdditButton
		//addNumericEdditButton
	}
	private void setAmount(Integer value) {
		// TODO Auto-generated method stub
		amount=value;
		defaultamount=amount;
	}
	


}

