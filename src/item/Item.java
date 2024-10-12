package item;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Color;

import core.Main;
import world.entity.Entity;

abstract public class Item {

	Image image;
	boolean expired = false;
	
	String name;
	int cost;
	boolean unique;
	
	public void render (Graphics g, int i)
	{
		image.draw( i*96+32,Main.getScreenHeight()-64-30,64,64);
		g.setColor(Color.white);
		g.drawString(""+(i+1),i*96+58,Main.getScreenHeight()-120);
	}
	
	public boolean isUnique()
	{
		return unique;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getCost()
	{
		return cost;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public boolean getExpired()
	{
		return expired;
	}
	
	public void Expire()
	{
		expired = true;
	}
	
	public abstract Entity makeEntity();
}
