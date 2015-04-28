package com.aero.kmeans;

import java.awt.Color;
import java.util.Random;

import com.aero.kmeans.gui.Item;

public abstract class ClusterGenerator
{
	public static Cluster Generate(int itemCount, Color color, int hMargin, int vMargin)
	{
		Cluster cluster = new Cluster(color);
		Random rand = new Random();
		for (int i = 0; i < itemCount; i++)
		{
			cluster.AddElement(new Item(rand.nextInt(hMargin) + 1, rand.nextInt(vMargin) + 1));
		}
		
		return cluster;
	}
	
	public static Cluster Generate(int itemCount, Color color, int hMarginStart, int hMarginFinish, int vMarginStart, int vMarginFinish)
	{
		int area = (hMarginFinish - hMarginStart) * (vMarginFinish - vMarginStart);
		if(itemCount > area*0.8)
			itemCount = (int) (area*0.8);
		
		if(hMarginStart > hMarginFinish)
		{
			int temp = hMarginStart;
			hMarginStart = hMarginFinish;
			hMarginFinish = temp;
		}
		
		if(vMarginStart > vMarginFinish)
		{
			int temp = vMarginStart;
			vMarginStart = vMarginFinish;
			vMarginFinish = temp;
		}
		
		Cluster cluster = new Cluster(color);
		Random rand = new Random();
		int i = 0;
		while (i < itemCount)
		{
			int x = rand.nextInt(hMarginFinish - hMarginStart) + hMarginStart;
			int y = rand.nextInt(vMarginFinish - vMarginStart) + vMarginStart;
			if(cluster.isReserve(x, y))
				continue;
			
			cluster.AddElement(new Item(x, y));
			//System.out.println(cluster.GetName() + " kümesine (" + x + ", " + y + ") eklendi.");
			i++;
		}
		
		return cluster;
	}
}
