package item;

import core.Images;
import world.entity.Entity;
import world.entity.plant.crop.Corn;

public class CornSeed extends CropSeed{
	public CornSeed()
	{
		image = Images.cornSeed;
		name = "corn seed";
		cost = 3;
	}
	
	public Entity makeEntity()
	{
		Entity e = new Corn();
		return e;
	}
}