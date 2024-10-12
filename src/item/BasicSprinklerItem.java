package item;

import core.Images;
import world.entity.Entity;
import world.entity.sprinkler.BasicSprinkler;

public class BasicSprinklerItem extends SprinklerItem {
	
	public BasicSprinklerItem()
	{
		image = Images.sprinklerItem;
		name = "basic sprinkler";
		cost = 5;
	}

	public Entity makeEntity() {
		return new BasicSprinkler();
	}


}
