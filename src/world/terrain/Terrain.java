package world.terrain;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import world.Cell;
import core.Images;
import item.Item;


abstract public class Terrain {
	
	//protected: can be accessed by subclass but not others
	protected Cell cell;
	protected Image image;
	
	abstract public void nextDay();
	abstract public void clicked(Item item);
	

	
	//default color is gray
	public Terrain()
	{
		image = Images.gray;		
	}
	
	//cell determined
	public void setCell(Cell c)
	{
		cell = c;
	}
	
	public void render(Graphics g)
	{
		int w = Cell.getWidth();
		int h = Cell.getHeight();
		g.drawImage(image, cell.getX()*w, cell.getY()*h);
	}
	
	
}
