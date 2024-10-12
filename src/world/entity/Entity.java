package world.entity;

import org.newdawn.slick.Color;
import world.Cell;
import world.terrain.Terrain;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import core.Images;
import item.Item;

abstract public class Entity {
	
	protected Cell cell;
	protected Color color;
	protected Image image;
	
	protected int value;
	protected boolean expired;
	
	protected boolean focus;
	protected int curHealth;
	protected int maxHealth;
	protected int damageTimer;
	protected boolean damaged;
	protected final int DAMAGE_EFFECT_DURATION=10;
	
	
	
	
	public void setCell(Cell c)
	{
		cell = c;
	}
	
	public void render (Graphics g)
	{
		
		int w = Cell.getWidth();
		int h = Cell.getHeight();
		g.setColor(color); 
		
		damageTimer++;
		
		if (damaged && damageTimer<DAMAGE_EFFECT_DURATION)
		{
			image.setImageColor(1, .6f, .4f);
			
		}
		else 
		{
			image.setImageColor(1, 1, 1);
		}
		
		image.draw(cell.getX()*w, cell.getY()*h-h/2,w,h*1.5f);
		
	
	}
	
	public int getValue()
	{
		return value;
	}
	public boolean getExpired()
	{
		return expired;
	}
	
	public boolean getFocus()
	{
		return focus;
	}
	
	public float getPercentHealth()
	{
		return (float)curHealth/(float)maxHealth;
	}
	
	public void takeDamage (int amount)
	{
		if (curHealth >= amount)
		{
			curHealth -= amount;
			damageTimer = 0;
			damaged = true;
		}
		else
		{
			curHealth = 0;
		}
	}
	
	abstract public boolean isValid(Terrain t);
	abstract public void nextDay();
	abstract public void clicked(Item item);
}
