package item;

import core.Images;
import world.entity.Entity;
import world.entity.plant.crop.Potato;

public class PotatoSeed extends CropSeed{
	public PotatoSeed()
	{
		image = Images.potatoSeed;
		name = "potato seed";
		cost = 1;
	}
	
	public Entity makeEntity()
	{
		Entity e = new Potato();
		return e;
	}
}
