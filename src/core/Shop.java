package core;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
//import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import world.World;
import core.Images;
import core.messages.MessageManager;
import item.Item;
import item.CornSeed;
import item.FernSeed;
import item.PotatoSeed;
import item.Hoe;
import item.AdvancedSprinklerItem;
import item.Axe;
import item.BasicSprinklerItem;
import item.Bucket;
import item.ItemBar;
import item.Pick;

public class Shop extends BasicGameState {
	
	private int id;
	public World world;
	private StateBasedGame sbg;
	private ArrayList <Item> stock;

	public Shop(int id) 
	{
		this.id = id;
		stock = new ArrayList <Item>();
		
		stock.add(new Hoe());
		stock.add(new Pick());
		stock.add(new Axe());
		stock.add(new CornSeed());
		stock.add(new PotatoSeed());
		stock.add(new BasicSprinklerItem());
		stock.add(new AdvancedSprinklerItem());
		stock.add(new FernSeed());
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		gc.setShowFPS(true);
		this.sbg = sbg;
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
		MessageManager.update();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame.
		g.setBackground(Color.black);
		g.drawImage(Images.shop, 0, 0);
		renderMoneyDay(g);
		renderStock(g);
		Game.getItemBar().render(g);
		MessageManager.render(g);
		
		}
	
	public void renderMoneyDay (Graphics g)
	{
		g.drawString("$ "+Game.getMoneyValue(), 50, 50);
		g.drawString("Day "+Game.day, 50, 75);
		
	}
	
	public void renderStock(Graphics g)
	{
		g.setColor(Color.black);
		g.setFont(Fonts.big);
		for (int i=0;i<stock.size();i++)
		{
			String n = stock.get(i).getName();
			int c = stock.get(i).getCost();
			
			g.drawString(""+(i+1)+". "+n+" $ "+c, 50, 100+45*i);
		}
	}
	
	private void buy(Item i)
	{
		if (Game.hasMoney(i.getCost()))
		{
			Game.spendMoney(i.getCost());
		}
		Game.getItemBar().addItem(i);
	}
	
	
	public Item buildItem(Class<? extends Item> clazz)
	{
		Item i = null;
		
		try
		{
			i = clazz.getDeclaredConstructor().newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		return i;
	}
	
	
	
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}

	public void keyPressed(int key, char c)
	{
		// This code happens every time the user presses a key
		if (key == Input.KEY_E)
		{
			sbg.enterState(Main.GAME_ID);
		}
		
		if ((key-2<stock.size())&&(key>=2)&&(Game.getItemBar().hasSpace()))
		{
			int index = key -2;
			if(Game.hasMoney(stock.get(index).getCost())&&(Game.getItemBar().hasSpace()))
			{
				Item original = stock.get(key-2);
				Item newItem = buildItem(original.getClass());
				buy (newItem);
				Sounds.coins.play();
				
				if (original.isUnique())
				{
					stock.remove(original);
				}
			}
			else
			{
				Sounds.cancel.play();
			}
			
					
		}
		
	}
	
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
	}
	

}
