package world.terrain;


import core.Images;
import core.Sounds;
import item.Item;

public class Grass extends Terrain {
	
	public Grass()
	{
		image = Images.grass;
	}
	
	public void clicked(Item item)
	{
		Sounds.cancel.play();
	}
	public void nextDay()
	{
		
	}
	

}
