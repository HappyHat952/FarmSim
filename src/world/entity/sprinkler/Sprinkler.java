package world.entity.sprinkler;

import java.util.ArrayList;

import world.Cell;
import world.entity.Entity;
import world.terrain.Dirt;

abstract public class Sprinkler extends Entity{
	
	public boolean isValid()
	{
		return true;
	}
	
	public void clicked()
	{
		
	}
	public void waterCells(ArrayList<Cell> neighbors)
	{
		for (Cell n: neighbors)
		{
			
			if (n.getTerrain() instanceof Dirt)
			{
				Dirt d = (Dirt) n.getTerrain();
				d.waterSoil();
			}
		}
	}

}
