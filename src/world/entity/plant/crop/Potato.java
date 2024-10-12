package world.entity.plant.crop;

import org.newdawn.slick.Color;

import core.Images;

public class Potato extends Crop{
	
	public Potato()
	{
		sheet = Images.potato;
		maturity = 4;
		setImage();
		
		value = 2;
	}

}
