package world;

import world.Cell;
import item.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import world.terrain.Dirt;
import world.terrain.Grass;
import world.terrain.Water;
import world.weather.Weather;
import world.entity.Entity;
import world.entity.plant.crop.Crop;
import world.entity.plant.crop.Fern;
import world.entity.plant.crop.Corn;
import world.entity.plant.crop.Potato;
import world.entity.plant.tree.PineTree;
import world.entity.rock.Rock;
import world.entity.sprinkler.AdvancedSprinkler;
import world.entity.sprinkler.BasicSprinkler;


public class World {
	
	public static final int WIDTH = 30;
	public static final int HEIGHT = 15;
	
	private static Cell[][] cells;
	private static ArrayList<Entity> entities;
	private static ArrayList<Entity> sprinklers;
	private static Weather weather;
	
	public World()
	{
		cells = new Cell[WIDTH][HEIGHT];
		for (int w=0;w<WIDTH;w++)
		{
			for (int h=0;h<HEIGHT;h++)
			{
				cells[w][h] = new Cell(w,h);
			}
		}
		
		entities = new ArrayList<Entity>();
		weather = new Weather();
		sprinklers = new ArrayList<Entity>();
		
		
		readFile();
		

		
		
	}
	
	public static boolean isRaining()
	{	
		return weather.isRaining();
	}
	
	public static void addEntity(boolean entity, Entity e, int x, int y)
	{
		
		if (e.isValid(cells[x][y].getTerrain()))
		{
			if(entity)
			{
				entities.add(e);
			}
			else
			{
				sprinklers.add(e);
			}
			cells[x][y].setEntity(e);
			e.setCell(cells[x][y]);
		}
		
	}
	
	
	public void setTerrain(Cell cell, char code)
	{
		if (code == '.' )
		{
			cell.setTerrain(new Dirt(false,false));
		}
		else if (code == ',')
		{
			cell.setTerrain(new Dirt(true, false));
		}
		else if (code == '_')
		{
			cell.setTerrain(new Grass());
		}
		else if(code == 'w')
		{
			cell.setTerrain(new Water());
		}
		else if (code == 'o')
		{
			cell.setTerrain(new Dirt(false,false));
			addEntity(true, new Rock(), cell.getX(),cell.getY());
		}
		else if (code == 't')
		{
			cell.setTerrain(new Grass());
			addEntity(true, new PineTree(false), cell.getX(), cell.getY());
		}
	}
	
	
	
	
	//checks the file and creates a new cell based on map file
	public void readFile()
	{
		try
		{
			File mapFile = new File ("Maps/map1.txt");
			Scanner scan = new Scanner (mapFile);
			
			//loops through rows and collum
			
			for (int h=0; h< HEIGHT; h++)
			{
				String row = scan.nextLine();
				
				for (int r=0; r<WIDTH; r++)
				{
					char input = row.charAt(r);
					setTerrain(cells[r][h], input);
				}
			}
			
			scan.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Cannot find file WHOOPS.");
		}
	}
	
	public void nextDay()
	{
		weather.nextDay();
		
		
		for (int e=0; e<entities.size();e++)
		{
			entities.get(e).nextDay();
		}
		
		
		for (int w=0;w<WIDTH;w++)
		{
			for (int h=0;h<HEIGHT;h++)
			{
				cells[w][h].nextDay();
			}
		}
		
		for (int e=0; e<sprinklers.size();e++)
		{
			sprinklers.get(e).nextDay();
		}
		
		
	}
	
	public void mousePressed(int button, int x, int y, Item item)
	{
		x/=Cell.getWidth();
		y/=Cell.getWidth();
		
		if (inBounds(x,y))
		{
			cells[x][y].clicked(item);
		}
	}
	
	public static boolean inBounds (int x, int y)
	{
		if ((x<WIDTH) && (y<HEIGHT))
		{
			return true;
			
		}
		return false;
	}
	
	public void render(Graphics g)
	{
		for (int w=0;w<WIDTH;w++)
		{
			for (int h=0;h<HEIGHT;h++)
			{
				cells[w][h].render(g);
			}
		}
		
		for (int e=0;e<entities.size();e++)
		{
			entities.get(e).render(g);
		}
		
		for (int i=0; i<sprinklers.size();i++)
		{
			sprinklers.get(i).render(g);
		}
		
		weather.render(g);
		
	}
	
	public void cleanup()
	{
		for (int e=0;e<entities.size();e++)
		{
			if (entities.get(e).getExpired())
			{
				entities.remove(e);
				e--;
				
			}
		}
	}
	
	public static Cell getCell(int x, int y)
	{
		if ((x>=0)&&(x<WIDTH)&&(y>=0)&&(y<HEIGHT))
		{
			return cells[x][y];
		}
		return cells[0][0];
	}

}
