package world.weather;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Game;
import core.Main;

public class Weather {
	
	boolean raining;
	String weatherName;
	
	public Weather()
	{
		raining = false;
		setWeather();
	}
	
	public boolean isRaining()
	{
		return raining;
	}
	
	
	public void nextDay()
	{
		if ((Game.getDayValue()<3)||(Math.random()<0.8))
		{
			raining = false;
		}
		else
		{
			raining = true;
		}
		setWeather();
	}
	private void setWeather()
	{
		if (raining == true)
		{
			weatherName = "RAINING";
		}
		else
		{
			weatherName = "SUNNY DAY";
		}
	}
	
	public void render(Graphics g)
	{
		
		if (raining)
		{
			g.setColor(new Color(0,0,50,50));
			g.fillRect(0,0,Main.getScreenWidth(), Main.getScreenHeight());
			
		}
		
	}

}
