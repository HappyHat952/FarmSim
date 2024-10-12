package item;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import core.Fonts;
import core.Main;

public class Stack {

	private ArrayList<Item> stack;
	private int num;
	private String name;

	public Stack(Item i) {
		stack = new ArrayList<>();
		stack.add(i);
		num = 1;
		name = i.getName();
	}

	//mutator
	public void addItem(Item i) {
		if (i.getName() == name) {
			stack.add(i);
			num++;
		}
	}

	public void render(Graphics g, int i) {
		g.setFont(Fonts.small);
		stack.get(0).render(g, i);
		g.setFont(Fonts.big);
		g.setColor(Color.yellow);
		g.drawString("" + num, i * 96 + 80, Main.getScreenHeight() - 60);
		g.setColor(Color.white);
	}

	public void expireItems() {
		for (int i = 0; i < stack.size(); i++) {
			if (stack.get(i).getExpired()) {
				stack.remove(i);
				i--;
				num--;
			}
		}
	}

	//accessor
	public String getName() {
		return name;
	}

	public Item getItem() {
		return stack.get(0);
	}

	public boolean getExpired() {
		if (stack.size() <= 0) {
			return true;
		}
		return false;
	}
}

