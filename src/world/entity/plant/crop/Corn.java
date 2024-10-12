package world.entity.plant.crop;


import core.Images;


public class Corn extends Crop{
	
	public Corn()
	{
		sheet = Images.corn;
		maturity = 7;
		setImage();
		
		value =5;
	}

}
