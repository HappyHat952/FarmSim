package world.entity.sprinkler;

import java.util.ArrayList;

import core.Images;
import item.Item;
import world.Cell;
import world.terrain.Dirt;
import world.terrain.Terrain;

public class BasicSprinkler extends Sprinkler{
	
	
	public BasicSprinkler()
	{
		image = Images.sprinklerEntity;
	}
	
	public void nextDay()
	{
		super.waterCells(cell.getFourNeighbors());
	}

	public boolean isValid(Terrain t) {
		return super.isValid();
	}

	public void clicked(Item item) {
		super.clicked();
		
	}
	
	
	
}
