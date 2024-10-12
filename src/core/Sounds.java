package core;

import org.newdawn.slick.Sound;
import org.newdawn.slick.SlickException;


public class Sounds {
	
	public static Sound hoe;
	public static Sound harvest;
	public static Sound seeds;
	public static Sound waterLap;
	public static Sound waterScoop;
	public static Sound axe;
	public static Sound treeFall;
	public static Sound pick;
	public static Sound rockBreak;
	
	public static Sound cancel;
	public static Sound select;
	public static Sound unselect;
	public static Sound coins;
	public static Sound yes;
	public static Sound nextDay;
	
	
	public static void loadSounds()
	{
		try
		{
			hoe = new Sound("res2/sound/gameplay/hoe.wav");
			harvest = new Sound("res2/sound/gameplay/harvest.wav");
			seeds = new Sound("res2/sound/gameplay/seeds.wav");
			waterLap = new Sound("res2/sound/gameplay/waterLap.wav");
			waterScoop = new Sound("res2/sound/gameplay/waterScoop.wav");
			axe = new Sound("res2/sound/gameplay/axe.wav");
			treeFall = new Sound("res2/sound/gameplay/treeFall.wav");
			pick = new Sound("res2/sound/gameplay/pick.wav");
			rockBreak = new Sound("res2/sound/gameplay/rockBreak.wav");
			
			cancel = new Sound ("res2/sound/interface/cancel.wav");
			select = new Sound ("res2/sound/interface/select.wav");
			unselect = new Sound ("res2/sound/interface/unselect.wav");
			coins = new Sound ("res2/sound/interface/coins.wav");
			yes = new Sound ("res2/sound/interface/yes.wav");

			
		}
		catch (SlickException e)
		{
			e.printStackTrace();
		}
	}
	

}
