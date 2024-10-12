package world.entity.plant.tree;

import core.Images;


public class PineTree extends Tree{
	
	public boolean grown;
	
	public PineTree(boolean isGrown)
	{
		super();
		maturity = 10;
		if (isGrown)
		{
			daysGrown = maturity;
		}
		
		sheet = Images.pineTree;
		
		setImage();
		setHealth();
		System.out.println(curHealth);
	}
	
	
	

	

}
