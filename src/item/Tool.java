package item;

import world.entity.Entity;

abstract public class Tool extends Item {
	public Tool()
	{
		unique = true;
	}
	
	public Entity makeEntity()
	{
		return null;
	}

	
}
