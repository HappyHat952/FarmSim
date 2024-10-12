package world.entity.plant.tree;

import core.Game;
import core.Sounds;
import item.Axe;
import item.Item;
import world.entity.plant.Plant;
import world.terrain.Grass;
import world.terrain.Terrain;

abstract public class Tree extends Plant{
	
	public final int CUT_ENERGY;
	
	public Tree()
	{
		value = 4;
		maturity = 10;
		daysGrown = (int) (Math.random()*maturity)+1;
		focus = true;
		CUT_ENERGY = 3;
		setHealth();
	}

	public boolean isValid(Terrain t) {
		if (t instanceof Grass)
		{
			return true;
		}
		return false;
	}
	
	//sets health based on days grown and limits max health
	public void setHealth()
	{
		if (daysGrown <= 10)
		{
			maxHealth = daysGrown*2;
		}
		else
		{
			maxHealth = 20;
		}
		
		curHealth = maxHealth;
	}

	public void nextDay()
	{
		damaged = false;
		setHealth();
		if (!(daysGrown == maxHealth))
		{
			daysGrown ++;
		}
		setImage();
		
	}
	public void clicked(Item item)
	{
		if (Game.hasStamina(CUT_ENERGY) && (item instanceof Axe))
		{
			Game.expendStamina(CUT_ENERGY);
			takeDamage(value);
			Sounds.axe.play((float) (1.25-getPercentHealth()*0.5),1);
			
			if (curHealth <=0)
			{
				Game.gainMoney(maxHealth/2, cell);
				cell.removeEntity();
				expired = true;
				Sounds.treeFall.play();
				
			}
			
		}
		else
		{
			Sounds.cancel.play();
		}
	}

}
