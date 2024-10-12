package world.entity.sprinkler;

import core.Images;
import item.Item;
import world.terrain.Terrain;

public class AdvancedSprinkler extends Sprinkler{
	
	public AdvancedSprinkler()
	{
		image = Images.sprinklerEntity;
		
	}

	public void nextDay()
	{
		super.waterCells(cell.getEightNeighbors());
	}

	public boolean isValid(Terrain t) {
		return super.isValid();
	}

	public void clicked(Item item) {
		super.clicked();
		
	}
	
}
