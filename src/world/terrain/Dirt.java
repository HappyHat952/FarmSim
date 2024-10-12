package world.terrain;

import core.Game;
import core.Images;
import core.Sounds;
import item.Item;
import item.CropSeed;
import world.World;
import world.entity.plant.crop.Crop;

import item.Hoe;
import item.Bucket;

public class Dirt extends Terrain{
	
	boolean soil;
	boolean wet;
	final int SEED_ENERGY;
	final int TILL_ENERGY;
	final int WATER_ENERGY;
	
	public Dirt(boolean soil, boolean wet)
	{
		this.soil = soil;
		this.wet = wet;
		SEED_ENERGY = 2;
		TILL_ENERGY = 4;
		WATER_ENERGY = 1;
		setImage();
		setWeatherWet();
	}
	
	public boolean isSoil()
	{
		return soil;
	}
	
	public boolean isWet()
	{
		return wet;
	}
	
	private void setWeatherWet()
	{
		if (World.isRaining())
		{
			wet = true;
		}
		else
		{
			wet = false;
		}
	}
	
	public void waterSoil()
	{
		wet = true;
		setImage();
	}
	
	public void setImage()
	{
		if (soil&&wet)
		{
			image = Images.wetSoil;
		}
		else if(soil)
		{
			image = Images.drySoil;
		}
		else
		{
			image = Images.dirt;
		}
	}

	
	public void clicked(Item item)
	{
		if ((Game.hasStamina(SEED_ENERGY))&&(soil) && (!cell.hasEntity()) && (item instanceof CropSeed))
		{
			Sounds.seeds.play();
			Crop c = (Crop) item.makeEntity();
			World.addEntity(true, c, cell.getX(), cell.getY());
			Game.expendStamina(SEED_ENERGY);
			
			
			
			item.Expire();
			
		}
		
		// if soil and no entity and item click is a cropseed
		////make a crop c, and add it to the world.
		else if ((Game.hasStamina(TILL_ENERGY))&&(!soil)&&(item instanceof Hoe))
		{
			soil = true;
			Sounds.hoe.play();
			Game.expendStamina(TILL_ENERGY);
		}
		else if ((Game.hasStamina(WATER_ENERGY))&&(!wet)&&soil&&(item instanceof Bucket))
		{
			Bucket bucket = (Bucket) item;
			if (bucket.isFilled())
			{
				wet = true;
				Game.expendStamina(WATER_ENERGY);
				bucket.use();
				Sounds.waterLap.play((float) (1.25-bucket.getPercentFilled()*0.5),1);
			}
			
		}
		else
		{
			Sounds.cancel.play();
		}
		setImage();
	}
	
	public void nextDay()
	{
		if (wet)
		{
			wet = false;
		}
		setWeatherWet();
		setImage();
		
	}
}
