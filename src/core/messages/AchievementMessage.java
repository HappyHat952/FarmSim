package core.messages;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;

public class AchievementMessage extends Message{

	public AchievementMessage(String text, float x, float y, Color color, Font font, int duration) {
		super(text, x, y, color, font, duration);
	}
	
	public void render(Graphics g)
	{
		if(fading)
		{
			color = new Color(color.getRed(), color.getGreen(), color.getBlue(), getPercentTimeLeft());
		}
		g.setColor(color);
		g.setFont(font);
		
		int width = font.getWidth(text);
		g.fillRect(x, y, width, 50);
		
		g.setColor(Color.black);
		g.drawString(text, x, y);
		
	}
	

}
