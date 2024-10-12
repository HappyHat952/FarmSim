package world.terrain;

import core.Game;
import core.Images;
import core.Sounds;
import item.Bucket;
import item.Item;

public class Water extends Terrain{
	
	final int WATER_ENERGY;
	
	public Water()
	{
		WATER_ENERGY = 5;
		image = Images.water;
	}
	
	public void clicked(Item item)
	{
		if ((item instanceof Bucket)&&(Game.hasStamina(WATER_ENERGY)))
		{
			Bucket bucket = (Bucket) item;
			bucket.fill();
			Game.expendStamina(WATER_ENERGY);
			Sounds.waterScoop.play();
		}
		else
		{
			Sounds.cancel.play();
		}
	}
	public void nextDay()
	{
		
	}

}
