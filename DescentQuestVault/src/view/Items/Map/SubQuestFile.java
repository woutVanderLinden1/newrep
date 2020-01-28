package view.Items.Map;

import java.io.Serializable;

import misc.save.WorldSaveFile;

public class SubQuestFile implements Serializable{
	private WorldSaveFile file;
	private MapLocation loc;
	
	private String name;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public SubQuestFile(WorldSaveFile saveGame, MapLocation maploc) {
		file=saveGame;
		loc=maploc;
	}

	public WorldSaveFile getFile() {
		return file;
	}

	public void setFile(WorldSaveFile file) {
		this.file = file;
	}

	public MapLocation getLoc() {
		return loc;
	}

	public void setLoc(MapLocation loc) {
		this.loc = loc;
	}

	public void setName(String string) {
		// TODO Auto-generated method stub
		name=string;
	}


}
