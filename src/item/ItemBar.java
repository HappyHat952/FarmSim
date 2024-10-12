package item;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;

import core.Game;
import core.Images;
import core.Main;
import core.Sounds;

public class ItemBar {
	
	//make items an arraylist of stack
	public ArrayList <Stack> itemStack;
	public ArrayList <String> itemStackName;
	
	private int selectedIndex;
	public final static int MAX_ITEMS=9;
	
	public ItemBar()
	{
		selectedIndex = -1;
		
		//NEW!
		itemStack = new ArrayList<>();
		itemStackName = new ArrayList<>();
		
		addItem(new Bucket());
		addItem(new CornSeed());
		addItem(new PotatoSeed());
		addItem(new PotatoSeed());
		
		setCursor();
		
	}
	
	
	public void keyPressed(int key, char c)
	{
		if (selectedIndex == key-2)
		{
			Sounds.unselect.play(1, (float)0.25);
			selectedIndex = -1;
		}
		else if ((key-2>=0)&&(key-2<itemStack.size()))
		{
			Sounds.select.play(1,(float)0.25);
			selectedIndex = key-2;
		}
		
		setCursor();
		
	}
	
	public void mousePressed(int button, int x, int y)
	{
		setCursor();
	}
	
	public void render(Graphics g)
	{
		//OLD
		//for (int i=0;i<items.size();i++)
		{
			//items.get(i).render(g,i);
		}
		
		//NEW
		
		for (int i=0;i<itemStack.size();i++)
		{
			itemStack.get(i).render(g,i);
		}
		
		if (hasSelectedItem())
		{
			int sel = selectedIndex;
			g.setLineWidth(2);
			g.drawRect(sel*96+28,Main.getScreenHeight()-97,70,70);
		}
	}
	
	public void setCursor()
	{
		
			try
			{
				
				if(!hasSelectedItem())
				{
					Game.gc.setMouseCursor(Images.blankCursor,1,1);
				}
				else
				{
				
					Image i= getSelectedItem().getImage().getScaledCopy(32,32);
					Game.gc.setMouseCursor(i, 16, 16);
				}
				
			}
			
			catch (SlickException e)
			{
				
				e.printStackTrace();
			}
		
	}
	
	public void cleanup()
	{
		for(int i =0; i<itemStack.size();i++)
		{
			itemStack.get(i).expireItems();
			if (itemStack.get(i).getExpired())
			{
				if (selectedIndex == i)
				{
					selectedIndex = -1;
				}
				
				itemStack.remove(i);
				itemStackName.remove(i);
				i--;
			}
		}
	}
	
	public void clearSelection()
	{
		selectedIndex = -1;
	}
	
	public boolean hasSelectedItem()
	{
		if (selectedIndex<0)
		{
			return false;
		}
		return true;
	}
	
	public boolean hasSpace()
	{
		return (MAX_ITEMS>itemStack.size());
	}
	
	public void addItem(Item item)
	{
		boolean hasItem=false;
		
		if (itemStackName.size()!=0)
		{
			for (int i =0; i<itemStackName.size();i++)
			{
				System.out.println(i);
				System.out.println(itemStackName.get(i));
				System.out.println(item.getName());
				if (itemStackName.get(i).equals(item.getName()))
				{
					hasItem = true;
					itemStack.get(i).addItem(item);
				}
			}
		}
		
		
		if (hasSpace())
		{
			
			if (!hasItem)
			{
				itemStack.add(new Stack(item));
				itemStackName.add(item.getName());
				for (int i=0;i<itemStackName.size();i++)
				{
					System.out.println(itemStackName.get(i));
				}
			}
		
		}
	}
	public Item getSelectedItem()
	{
		if (!hasSelectedItem())
		{
			//also sus 
			return null;
		}
		return itemStack.get(selectedIndex).getItem();
		
	}
	

}
