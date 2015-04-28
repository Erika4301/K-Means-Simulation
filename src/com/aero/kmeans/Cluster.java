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
			return "Siyah küme";
		if(this.clusterColor.equals(Color.WHITE))
			return "Beyaz küme";
		if(this.clusterColor.equals(Color.BLUE))
			return "Mavi küme";
		if(this.clusterColor.equals(Color.RED))
			return "Kýrmýzý küme";
		if(this.clusterColor.equals(Color.GREEN))
			return "Yeþil küme";
		if(this.clusterColor.equals(Color.ORANGE))
			return "Turuncu küme";
		if(this.clusterColor.equals(Color.CYAN))
			return "Turkuaz küme";
		if(this.clusterColor.equals(Color.MAGENTA))
			return "Magenta küme";
		if(this.clusterColor.equals(Color.PINK))
			return "Pembe küme";
		if(this.clusterColor.equals(Color.YELLOW))
			return "Sarý küme";
		if(this.clusterColor.equals(Color.DARK_GRAY))
			return "KoyuGri küme";
		if(this.clusterColor.equals(Color.LIGHT_GRAY))
			return "AçýkGri küme";
		else
			return "Bilinmeyen küme";
	}
}
