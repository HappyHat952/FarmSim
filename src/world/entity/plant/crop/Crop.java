package world.entity.plant.crop;

import world.entity.plant.Plant;
import world.terrain.Terrain;
import world.terrain.Dirt;
import core.Game;
import item.Item;

abstract public class Crop extends Plant {
	
	public boolean isValid(Terrain t)
	{
		//"Dirt" occurs first, checks if it is a dirt
		//second promises to java that the terrain is a dirt, asks if it is a soil.
		//TLDR: if not dirt or not soil, returns false.
		return t instanceof Dirt && ((Dirt) t).isSoil();
		
	}
	
	public void nextDay()
	{
		//creates a temporary d (dirt is implied bc already growing). if wet, and soil, increases.
		Dirt d = (Dirt) cell.getTerrain();
		
		if(d.isSoil() && d.isWet())
		{
			

			daysGrown++;
			setImage();
		}
	}
	
	public void clicked(Item item)
	{
		if (isMature())
		{
			Game.gainMoney(value,cell);
			cell.removeEntity();
			expired = true;
			
		}
	}

}
