package item;

import core.Images;
import world.entity.Entity;
import world.entity.plant.crop.Corn;
import world.entity.plant.crop.Fern;

public class FernSeed extends CropSeed{
	public FernSeed()
	{
		image = Images.fernSeed;
		name = "fern seed";
		cost = 5;
	}
	
	public Entity makeEntity()
	{
		Entity e = new Fern();
		return e;
	}
}
