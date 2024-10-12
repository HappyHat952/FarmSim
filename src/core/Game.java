package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import core.messages.AchievementMessage;
import core.messages.BigMessage;
import core.messages.FloatMessage;
import core.messages.Message;
import core.messages.MessageManager;
import world.World;
import item.ItemBar;
import world.Cell;

public class Game extends BasicGameState 
{	
	private int id;
	public World world;
	private StateBasedGame sbg;
	private static ItemBar itemBar;
	private static int money;
	public static int day;
	public static int earnings;
	public static GameContainer gc;
	private static int curStamina;
	private static int maxStamina;
	private static int BASE_STAMINA;

	public Game(int id) 
	{
		this.id = id;
	}
	
	public int getID() 
	{
		return id;		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a game state for the *first time.*
		Images.loadImages();
		Fonts.loadFonts();
		Sounds.loadSounds();
		gc.setShowFPS(true);
		world = new World();

		this.sbg = sbg;
		this.gc = gc;
		itemBar = new ItemBar();
		
		money =10;
		earnings =0;
		day = 1;
		BASE_STAMINA = 50;
		maxStamina = BASE_STAMINA;
		curStamina = BASE_STAMINA;
		
		MessageManager.init();
		
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
	{	
		// This is updates your game's logic every frame.  NO DRAWING.
		world.cleanup();
		MessageManager.update();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
	{
		// This code renders shapes and images every frame.
		g.setBackground(Color.black);
		world.render(g);
		itemBar.render(g);
		renderMoneyDay(g);
		renderStamina(g);
		MessageManager.render(g);
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{
		// This code happens when you enter a gameState.  
		
	}

	public void leave(GameContainer gc, StateBasedGame sbg) 
	{
		// This code happens when you leave a gameState. 
	}
	
	//money accessor
	public static boolean hasMoney (int amount)
	{
		return (money>=amount);
	}
	
	public static void spendMoney (int amount)
	{
		money -= amount;
	}
	
	//version 2: adds money
	public static void gainMoney (int amount)
	{
		money += amount;
		addEarnings(amount);
	}
	//version 2: adds money and outputs a centered message
	public static void gainMoney (int amount, int x, int y)
	{
		gainMoney(amount);
		String text = "" +amount;
		Font font = Fonts.big;
		Color color = Color.yellow;
		int textLength = font.getWidth(text);
		int textHeight = font.getHeight(text);
		Message m = new FloatMessage(text, x-textLength/2, y-textHeight/2, color, 60);
		MessageManager.addMessage(m);
	}
	
	//version 3: adds mone, outputs a message at center of cell
	public static void gainMoney(int amount, Cell cell)
	{
		int xCentered = cell.getX() * Cell.getWidth() + Cell.getWidth()/2;
		int yCentered = cell.getY() * Cell.getHeight() + Cell.getHeight()/2;
		
		gainMoney (amount, xCentered, yCentered);
	}
	public static int getMoneyValue ()
	{
		return money;
	}
	public static int getDayValue()
	{
		return day;
	}
	
	public static boolean hasStamina(int amount)
	{
		return (curStamina>=amount);
	}
	
	public static float getPercentStamina()
	{
		return (float) curStamina/ (float)maxStamina;
	}
	
	public static void expendStamina (int amount)
	{
		curStamina -= amount;
	}
	
	public static void addEarnings(int amount)
	{
		earnings += amount;
	}
	
	
	public static ItemBar getItemBar()
	{
		return itemBar;
		
	}
	//money mutator
	
	public static void renderMoneyDay(Graphics g)
	{
		g.setColor(Color.black);
		
		g.setFont(Fonts.big);
		g.drawString("Earnings: "+earnings, 50-3, 65+3);
		g.drawString("$ "+money, 50-3, 25+3);
		g.drawString("Day "+day, Main.getScreenWidth()-203,25+3);
		
		g.setColor(Color.white);
		
		
		g.setFont(Fonts.big);
		g.drawString("Earnings: "+earnings, 50, 65);
		g.drawString("$ "+money, 50, 25);
		g.drawString("Day "+day, Main.getScreenWidth()-200,25);
		
		g.resetFont();
	}
	
	public static void renderStamina(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillRect(0, Cell.getHeight()*World.HEIGHT, Main.getScreenWidth(), 10);

		g.setColor(new Color(1-getPercentStamina(), getPercentStamina(),0));
		g.fillRect(0, Cell.getHeight()*World.HEIGHT, Main.getScreenWidth()*getPercentStamina(), 10);
	
	}
	
	public void keyPressed(int key, char c)
	{
		// This code happens every time the user presses a key
		if (key == Input.KEY_E)
		{
			gc.setDefaultMouseCursor();
			sbg.enterState(Main.SHOP_ID);
		}
		else if (key == Input.KEY_SPACE)
		{
			world.nextDay();
			day++;
			MessageManager.addMessage(new BigMessage("Day "+day+" begins!", Color.white, 60));
			curStamina = maxStamina;
			if (day == 6)
			{
				MessageManager.addMessage(new AchievementMessage("Press 'E' to enter shop", Main.getScreenWidth()-Main.getScreenWidth()/2, 50, Color.cyan, Fonts.big, 600));
			}
		}
		itemBar.keyPressed(key,c);
		
	}
	
	public void mousePressed(int button, int x, int y)
	{
		// This code happens every time the user presses the mouse
		world.mousePressed(button,x,y, itemBar.getSelectedItem());
		itemBar.cleanup();
		itemBar.mousePressed(button, x,y);
		
	}
	
	public static int getMouseX()
	{
		return gc.getInput().getMouseX();
	}
	public static int getMouseY()
	{
		return gc.getInput().getMouseY();
	}
	
	


}
