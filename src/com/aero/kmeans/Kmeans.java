package com.aero.kmeans;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.aero.kmeans.gui.Item;

//
// K-means Algoritmasi
//
public class Kmeans
{
	//
	// Degiskenler, sabitler, sinif uyeleri
	//
	private Cluster[] clusters;
	
	//
	// Kurucu Metod - Constructor
	//
	public Kmeans(Cluster[] clusters)
	{
		this.clusters = clusters;
		for(Cluster cluster : this.clusters)
			cluster.SelectRandomCenter();
	}
	
	//
	// Iterasyon
	//
	public void Iterate()
	{
		for(Cluster cluster : this.clusters)
		{
			cluster.GetCenterElement().SetCoordinate(cluster.CalculateCenter());
		}
	}
	
	//
	// Kumelerin (renklerin) guncellenmesi
	//
	public void UpdateColors()
	{
		List<Item> allElements = new ArrayList<Item>();
		for(Cluster cluster : this.clusters)
			allElements.addAll(cluster.GetElementList());
		
		List<Item> centers = new ArrayList<Item>();
		for(Cluster cluster : this.clusters)
			centers.add(cluster.GetCenterElement());
		
		for(Item item : allElements)
		{
			for(Item center : centers)
			{
				if(isNearly(item, center.GetCoordinate()))
				{
					item.GetParentCluster().DeleteElement(item);
					item.SetParentCluster(center.GetParentCluster());
					item.GetParentCluster().AddElement(item);
				}
			}			
		}
	}
	
	private boolean isNearly(Item item, Point p)
	{
		// Elemanin kendi merkezine olan uzakligi
		Point selfCenter = item.GetParentCluster().GetCenterElement().GetCoordinate();
		double selfDistance = Math.pow((item.GetCoordinate().x - selfCenter.x), 2) + Math.pow((item.GetCoordinate().y - selfCenter.y), 2);
		
		// Elemanin parametre noktaya olan uzakligi
		double pDistance = Math.pow((item.GetCoordinate().x - p.x), 2) + Math.pow((item.GetCoordinate().y - p.y), 2);
		
		// Eger parametre olarak verilen nokta kendi merkezinden daha yakinsa true don;
		return (pDistance < selfDistance);
	}
}
