package com.aero.kmeans;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.aero.kmeans.gui.Item;

//
// Kume sinifi
//
public class Cluster
{
	//
	// Degiskenler, sabitler, sinif uyeleri
	//
	private Color clusterColor;
	private List<Item> elementList;
	private Item centerElement = null;
	
	//
	// Setter/Getter Metodlar
	//
	public void SetClusterColor(Color c)
	{
		this.clusterColor = c;
	}
	
	public Color GetClusterColor()
	{
		return this.clusterColor;
	}
	
	public void SetCenterElement(Item item)
	{
		this.centerElement = item;
	}
	
	public Item GetCenterElement()
	{
		return this.centerElement;
	}
	
	public List<Item> GetElementList()
	{
		return this.elementList;
	}
	
	//
	// Kurucu Metod - Constructor
	//
	public Cluster(Color elementColor)
	{
		this.clusterColor = elementColor;
		this.elementList = new ArrayList<Item>();
	}
	
	public void AddElement(Item item)
	{
		item.SetParentCluster(this);
		this.elementList.add(item);
	}
	
	public void DeleteElement(Item item)
	{
		this.elementList.remove(item);
	}
	
	public boolean isReserve(int x, int y)
	{
		for(Item item : this.elementList)
			if(item.GetCoordinate().x == x && item.GetCoordinate().y == y)
				return true;
		return false;
	}
	
	public void SelectRandomCenter()
	{
		Random rand = new Random();
		this.centerElement = this.elementList.get(rand.nextInt(this.elementList.size()));
		this.centerElement.Select();
		this.centerElement.repaint();
	}
	
	public Point CalculateCenter()
	{
		int x = 0;
		int y = 0;
		for(Item item : this.elementList)
		{
			x += item.GetCoordinate().x;
			y += item.GetCoordinate().y;
		}
		x = x / this.elementList.size();
		y = y / this.elementList.size();
		
		return new Point(x, y);
	}
	
	public String GetName()
	{
		if(this.clusterColor.equals(Color.BLACK))
			return "Siyah k�me";
		if(this.clusterColor.equals(Color.WHITE))
			return "Beyaz k�me";
		if(this.clusterColor.equals(Color.BLUE))
			return "Mavi k�me";
		if(this.clusterColor.equals(Color.RED))
			return "K�rm�z� k�me";
		if(this.clusterColor.equals(Color.GREEN))
			return "Ye�il k�me";
		if(this.clusterColor.equals(Color.ORANGE))
			return "Turuncu k�me";
		if(this.clusterColor.equals(Color.CYAN))
			return "Turkuaz k�me";
		if(this.clusterColor.equals(Color.MAGENTA))
			return "Magenta k�me";
		if(this.clusterColor.equals(Color.PINK))
			return "Pembe k�me";
		if(this.clusterColor.equals(Color.YELLOW))
			return "Sar� k�me";
		if(this.clusterColor.equals(Color.DARK_GRAY))
			return "KoyuGri k�me";
		if(this.clusterColor.equals(Color.LIGHT_GRAY))
			return "A��kGri k�me";
		else
			return "Bilinmeyen k�me";
	}
}
