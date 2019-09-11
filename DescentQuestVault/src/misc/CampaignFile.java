package misc;

import java.io.Serializable;
import java.util.HashMap;

import model.values.CustomValue;

public class CampaignFile implements Serializable{

	public HashMap<String, CustomValue> getValues() {
		return values;
	}
	public void setValues(HashMap<String, CustomValue> values) {
		this.values = values;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//has all values this campaing has
	protected HashMap<String,CustomValue> values=new HashMap<String,CustomValue>();
	
	
}
