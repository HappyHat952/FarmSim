package world.entity.rock;

import world.entity.Entity;
import world.terrain.Terrain;
import core.Game;
import core.Images;
import core.Sounds;
import item.Item;
import item.Pick;

public class Rock extends Entity{
	
	final int ENERGY;

	public Rock()
	{
		focus = true;
		image = Images.rock;
		curHealth = 10;
		maxHealth = 10;
		value = 2;
		ENERGY = 3;
	}

	
	public boolean isValid(Terrain t) {
		return true;
	}

	public void nextDay() {
		
	}

	public void clicked(Item item) {
		if (Game.hasStamina(ENERGY) && (item instanceof Pick))
		{
			takeDamage(value);
			Game.expendStamina(ENERGY);
			Sounds.pick.play((float) (1.25-getPercentHealth()*0.5),1);
			
			if (curHealth == 0)
			{
				Game.gainMoney(value, cell);
				cell.removeEntity();
				expired = true;
				Sounds.rockBreak.play();
				
			}
		}
		else
		{
			Sounds.cancel.play();
		}
		

	}

	
}
