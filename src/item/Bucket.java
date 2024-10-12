package item;

import core.Images;

public class Bucket extends Tool{
	
	int capacity;
	final int MAX_CAPACITY;
	
	public Bucket()
	{
		MAX_CAPACITY = 8;
		capacity = 0;
		image = Images.bucketEmpty;
		name = "bucket";
		cost = 4;
	}
	
	public void fill()
	{
		image = Images.bucket;
		capacity = MAX_CAPACITY;
	}
	
	public boolean isFilled()
	{
		return (capacity>0);
	}
	public float getPercentFilled()
	{
		return (float)capacity/(float)MAX_CAPACITY;
	}
	
	public void use()
	{
		capacity --;
		if (capacity == 0)
		{
			image = Images.bucketEmpty;
		}
	}

}
