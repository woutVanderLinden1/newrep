package misc;

import model.values.CustomBoolean;
import model.values.CustomInteger;
import model.values.CustomValue;

public class DefaultCampaingFile extends CampaignFile {

	public DefaultCampaingFile() {
		values.put("money",new CustomInteger("money",0));
		values.put("hope",new CustomInteger("hope",0));
		values.put("money",new CustomInteger("money",0));
		values.put("despair",new CustomInteger("despair",0));
		values.put("fame",new CustomInteger("fame",0));
		values.put("peril",new CustomInteger("peril",0));
		values.put("lostlastgame", new CustomBoolean("lostlastgame",false));
	}
}
