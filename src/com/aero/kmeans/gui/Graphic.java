package com.aero.kmeans.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import com.aero.kmeans.Cluster;

//
// Grafik cizici
//
public class Graphic extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public static Graphic self;

	//
	// Degiskenler, sabitler, sinif uyeleri
	//
	public final int edgeSpace = 40;
	public int topX = 50;
	public int topY = 50;
	
	private final Color BACKGROUND_COLOR = Color.BLACK;
	private final Color AXIS_COLOR = Color.WHITE;
	private final Color GRID_COLOR = new Color(255, 255, 255, 30);
	private final Color LINE_COLOR = Color.GREEN;
	
	//
	// Kurucu Metod - Constructor
	//
	public Graphic()
	{
		self = this;
		
		this.setLayout(null);
		this.setBackground(BACKGROUND_COLOR);
		this.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	//
	// Grafik cizimi icin Paint metodu Override edilir
	//
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		this.DrawAxises(g);
		this.DrawLineNumbers(g);
		this.DrawGrid(g);
	}
	
	//
	// Koordinat duzlemlerinin ciziminden sorumlu metod
	//
	private void DrawAxises(Graphics g)
	{
		g.setColor(AXIS_COLOR);
		
		// X duzlemi
		g.drawLine(edgeSpace, this.getHeight() - edgeSpace, this.getWidth() - edgeSpace, this.getHeight() - edgeSpace);
		
		// Y duzlemi
		g.drawLine(edgeSpace, edgeSpace, edgeSpace, this.getHeight() - edgeSpace);
	}
	
	//
	// Koordinat duzlemlerine numaralarin ciziminden sorumlu metod
	//
	private void DrawLineNumbers(Graphics g)
	{
		g.setColor(AXIS_COLOR);
		
		// X duzlemine 
		double gapX = (this.getWidth() - 2 * edgeSpace) / 10;
		for(int i = 0; i <= 10; i++)
			g.drawString("" + i * (topX / 10), (int)(edgeSpace + i * gapX) - 5 - i*2, this.getHeight() - 15);
		
		// Y duzlemine
		double gapY = (this.getHeight() - 2 * edgeSpace) / 10;
		for(int i = 1; i < 10; i++)
			g.drawString("" + i * (topY / 10), 15, (int)(this.getHeight() - edgeSpace - i*gapY + 5 + i*4));
	}
	
	//
	// Izgaranin ciziminden sorumlu metod
	//
	private void DrawGrid(Graphics g)
	{
		g.setColor(GRID_COLOR);
		
		// gap : iki cizgi arasi bosluk
		
		// X duzlemine (Dikey cizgiler)
		double gapX = (this.getWidth() - 2 * edgeSpace) / topX;
		for(int i = 1; i <= topX; i++)
			g.drawLine(edgeSpace + (int)(i*gapX), this.getHeight() - edgeSpace, edgeSpace + (int)(i*gapX), edgeSpace);
		
		// Y duzlemine (Yatay cizgiler)
		double gapY = (this.getHeight() - 2 * edgeSpace) / topY;
		for(int i = 0; i < topY; i++)
			g.drawLine(edgeSpace, (int)(this.getHeight() - edgeSpace - i*gapY), this.getWidth() - edgeSpace, (int)(this.getHeight() - edgeSpace - i*gapY));		
	}
	
	private void AddItem(Item item)
	{
		// Duzeltme miktarlari
		int adjustmentX = -4;
		int adjustmentY = -3;
		double gapX = (this.getWidth() - 2 * edgeSpace) / topX;
		double gapY = (this.getHeight() - 2 * edgeSpace) / topY;
		
		item.setToolTipText(item.GetClusterName() + " (" + item.GetCoordinate().x + ", " + item.GetCoordinate().y + ")");
		
		int pixelX = edgeSpace + (int)(item.GetCoordinate().y * gapX) + adjustmentX;
		int pixelY = this.getHeight() - edgeSpace - (int)(item.GetCoordinate().x * gapY) + adjustmentY;
		
		item.setBounds(pixelX, pixelY, item.THICKNESS, item.THICKNESS);
		this.add(item);
		
		this.repaint();
	}
	
	public void AddCluster(Cluster cluster)
	{
		for(Item item : cluster.GetElementList())
			this.AddItem(item);
	}
}
