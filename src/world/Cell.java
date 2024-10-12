package world;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;
import core.Main;
import world.terrain.Terrain;
import world.entity.Entity;
import world.entity.plant.crop.Crop;
import world.entity.sprinkler.Sprinkler;
import item.Item;
import item.SprinklerItem;

public class Cell {
	
	private int x;
	private int y;
	private Terrain terrain;
	private Entity entity;
	
	public Cell(int x, int y)
	{
		this.x=x;
		this.y=y;
		
	}
	
	public int getX()
	{
		return x;
	}
	public int getXPixel()
	{
		return x*getWidth();
	}
	
	public int getY()
	{
		return y;
	}
	public int getYPixel()
	{
		return y*getHeight();
	}
	
	public Terrain getTerrain()
	{
		return terrain;
	}
	public Entity getEntity()
	{
		return entity;
	}

	public static int getWidth()
	{
		return Main.getScreenWidth() /World.WIDTH;
	}
	public static int getHeight()
	{
		return (Main.getScreenHeight()-128) / World.HEIGHT;
	}
	
	//assigns terrain based on parameter and tells which cell
	public void setTerrain(Terrain t)
	{
		//the cell has a terrain.
		terrain = t;
		terrain.setCell(this);
	}
	public void setEntity(Entity e)
	{
		entity = e;
		entity.setCell(this);
	}
	public boolean hasEntity()
	{
		return entity != null;
	}
	
	public void removeEntity()
	{
		if (entity != null)
		{
			entity.setCell(null);
			entity = null;
		}
	}
	public void render(Graphics g)
	{
		terrain.render(g);
		if (entity != null && entity instanceof Crop && ((Crop)entity).isMature())
		{
			g.setColor(Color.white);
			g.setLineWidth(2);
			g.drawRect(getX()*getWidth(), getY()*getHeight(), getWidth()-4, getHeight()-4);
			g.resetLineWidth();
		}
		
		if (isMouseOver())
		{
			g.setColor(new Color(255,255,255,50));
			g.fillRect(getX()*getWidth(), getY()*getHeight(), getWidth(), getHeight());
		}
		
	}
	
	public void clicked(Item item)
	{
		
		if ((entity != null)&&(entity.getFocus()))
		{
			entity.clicked(item);
		}
		else if (item instanceof SprinklerItem)
		{
			Sprinkler s = (Sprinkler)item.makeEntity();
			World.addEntity(false, s, getX(), getY());
			item.Expire();
		}
		else
		{
			terrain.clicked(item);
		}
		
	}
	
	public boolean isMouseOver()
	{
		int mouseX = Game.getMouseX();
		int mouseY = Game.getMouseY();
		
		if ((getXPixel()<mouseX)&&(getXPixel()+getWidth()>mouseX))
		{
			if ((getYPixel()<mouseY)&&(getYPixel()+getHeight()>mouseY))
			{
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Cell> addCellToList(ArrayList<Cell> list, int x, int y)
	{
		if (!(World.getCell(x, y) == null))
		{
			Cell cell = World.getCell(x, y);
			list.add(cell);
		}
		
		return list;
	}
	
	public ArrayList<Cell> getFourNeighbors()
	{
		ArrayList<Cell> list = new ArrayList<Cell>();
		list = addCellToList(list, x+1, y);
		list = addCellToList(list, x-1, y);
		list = addCellToList(list, x, y+1);
		list = addCellToList(list, x, y-1);
		System.out.println(list);
		return list;
	}
	
	public ArrayList<Cell> getEightNeighbors()
	{
		ArrayList<Cell> list = new ArrayList<Cell>();
		list = addCellToList(list, x+1, y);
		list = addCellToList(list, x-1, y);
		list = addCellToList(list, x, y+1);
		list = addCellToList(list, x, y-1);
		list = addCellToList(list, x-1,y-1);
		list = addCellToList(list, x-1,y+1);
		list = addCellToList(list, x+1,y-1);
		list = addCellToList(list, x+1,y+1);
		System.out.println(list);
		return list;
	}
	
	public void nextDay()
	{
		terrain.nextDay();
	}
}
