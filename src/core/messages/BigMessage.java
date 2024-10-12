package core.messages;

import org.newdawn.slick.Color;

import core.Fonts;
import core.Main;

public class BigMessage extends Message{
	
	public BigMessage(String text, Color color, int duration)
	{
		
		super(text, Main.getScreenWidth()/2, Main.getScreenHeight()-194, color, Fonts.big, duration);
		fading = true;
		centerText();
	
	}
	
	
	

}
