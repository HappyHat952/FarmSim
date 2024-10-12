package item;

import core.Images;
import world.entity.Entity;
import world.entity.sprinkler.AdvancedSprinkler;

public class AdvancedSprinklerItem extends SprinklerItem{
	
	public AdvancedSprinklerItem()
	{
		image = Images.sprinklerItem;
		name = "advanced sprinkler";
		cost = 25;
	}

	public Entity makeEntity() {
		return new AdvancedSprinkler();
	}

}
