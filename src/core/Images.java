package core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;


public class Images {
	
	public static Image dirt;
	public static Image grass;
	public static Image gray;
	public static Image drySoil;
	public static Image wetSoil;
	public static Image water;
	
	public static Image rock;
	
	public static Image fernSeed;
	public static Image cornSeed;
	public static Image potatoSeed;
	public static Image bucket;
	public static Image hoe;
	public static Image pick;
	public static Image axe;
	public static Image bucketEmpty;
	
	public static Image sprinklerItem;
	public static Image sprinklerEntity;
	
	public static SpriteSheet corn;
	public static SpriteSheet potato;
	public static SpriteSheet pineTree;
	public static SpriteSheet fern;
	public static SpriteSheet fence;
	
	public static Image title;
	public static Image shop;
	
	public static Image blankCursor;
	
	public static void loadImages()
	{
		try
		{
			dirt = new Image("res2/images/terrain/dirt.png");
			grass = new Image("res2/images/terrain/grass.png");
			gray = new Image("res2/images/terrain/gray.png");
			drySoil = new Image("res2/images/terrain/drySoil.png");
			wetSoil = new Image("res2/images/terrain/wetSoil.png");
			water = new Image("res2/images/terrain/water.png");
			
			rock = new Image ("res2/images/nature/rock.png");
			
			fernSeed = new Image("res2/images/items/fernSeed.png");
			cornSeed = new Image("res2/images/items/cornSeed.png");
			potatoSeed = new Image("res2/images/items/potatoSeed.png");
			bucket = new Image ("res2/images/items/bucketFull.png");
			bucketEmpty = new Image("res2/images/items/bucketEmpty.png");
			hoe = new Image ("res2/images/items/hoe.png");
			pick = new Image ("res2/images/items/pick.png");
			axe = new Image ("res2/images/items/axe.png");
			
			sprinklerItem = new Image("res2/images/items/sprinklerBasicItem.png");
			sprinklerEntity = new Image("res2/images/items/sprinklerBasicEntity.png");
			
			corn = new SpriteSheet("res2/images/crops/corn.png", 32,48,0);
			potato = new SpriteSheet("res2/images/crops/potato.png", 32, 48, 0);
			pineTree = new SpriteSheet("res2/images/nature/treePine.png",32,48,0);
			fern = new SpriteSheet("res2/images/crops/fern4.png",32,48,0);
			fence = new SpriteSheet("res2/images/structures/Fence.png",32,48,0);
			
			
			title = new Image("res/title.png");
			shop = new Image("res/shop.png");
			
			title = title.getScaledCopy(10);
			shop = shop.getScaledCopy(10);
			
			blankCursor = new Image("res/CircleCursor.png");
			
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}

}
