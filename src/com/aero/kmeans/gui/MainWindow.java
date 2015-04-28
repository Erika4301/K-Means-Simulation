package com.aero.kmeans.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.aero.kmeans.Cluster;
import com.aero.kmeans.ClusterGenerator;
import com.aero.kmeans.Kmeans;

//
// Ana pencere
//
public class MainWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	//
	// Degiskenler, sabitler, sinif uyeleri
	//
	private Kmeans kMeans;
	private JPanel mainPanel;
	private JPanel headerPanel;
	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel footerPanel;
	private Graphic graphic;
	private JPanel buttonsPanel;
	private JButton iterateButton;
	private JButton updateColorsButton;
	private String[] scenarioList = {"Senaryo 1", "Senaryo 2", "Senaryo 3", "Senaryo 4" };
	private JComboBox scenariosComboBox;
	
	private final int HEADER_PANEL_HEIGHT = 60;
	private final int LEFT_PANEL_WIDTH = 20;
	private final int RIGHT_PANEL_WIDTH = 200;
	private final int FOOTER_PANEL_HEIGHT = 20;
	
	//
	// Kurucu Metod - Constructor
	//
	public MainWindow()
	{
		// Pencere ayarlarinin yapilmasi
		this.setTitle("K-means Simulatörü");
		int fullWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int fullHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		// Dimension maxDimension = new Dimension(fullWidth, fullHeight);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLayout(new BorderLayout());
		this.setLocation((fullWidth - this.getWidth()) / 2, (fullHeight - this.getHeight()) / 2);
		
		// GUI bilesenlerinin yuklenmesi
		this.InitializeComponents();
		this.scenariosComboBox.setSelectedIndex(0);
		
		// Son ayarlar
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	//
	// GUI bilesenlerini uretir ve hazirlar
	//
	private void InitializeComponents()
	{
		this.SetMainPanel();
		this.SetHeaderPanel();
		this.SetLeftPanel();
		this.SetRightPanel();
		this.SetFooterPanel();
		this.SetButtons();
	}
	
	private void SetMainPanel()
	{
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(null);
		this.add(this.mainPanel, BorderLayout.CENTER);
		
		this.Setgraphic();
	}
	
	private void SetHeaderPanel()
	{
		this.headerPanel = new JPanel();
		this.headerPanel.setLayout(null);
		this.headerPanel.setPreferredSize(new Dimension(this.headerPanel.getWidth(), HEADER_PANEL_HEIGHT));
		this.headerPanel.setBackground(Color.LIGHT_GRAY);
		this.add(this.headerPanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("K-means Algoritmasý Simülasyonu");
		titleLabel.setBounds(20, 5, 250, 30);
		this.headerPanel.add(titleLabel);
		JLabel authorsLabel = new JLabel("Ahmet Ertuðrul Özcan");
		authorsLabel.setBounds(20, 25, 250, 30);
		this.headerPanel.add(authorsLabel);
	}
	
	private void SetLeftPanel()
	{
		this.leftPanel = new JPanel();
		this.leftPanel.setPreferredSize(new Dimension(LEFT_PANEL_WIDTH, this.leftPanel.getHeight()));
		this.add(this.leftPanel, BorderLayout.WEST);
	}
	
	private void SetRightPanel()
	{
		this.rightPanel = new JPanel();
		this.rightPanel.setLayout(null);
		this.rightPanel.setPreferredSize(new Dimension(RIGHT_PANEL_WIDTH, this.rightPanel.getHeight()));
		this.add(this.rightPanel, BorderLayout.EAST);
	}
	
	private void SetFooterPanel()
	{
		this.footerPanel = new JPanel();
		this.footerPanel.setPreferredSize(new Dimension(this.footerPanel.getWidth(), FOOTER_PANEL_HEIGHT));
		this.footerPanel.setBackground(Color.LIGHT_GRAY);
		this.add(this.footerPanel, BorderLayout.SOUTH);
	}
	
	private void Setgraphic()
	{
		this.graphic = new Graphic();
		this.graphic.setBounds(0, 20, 1100, 580);
		this.mainPanel.add(this.graphic);
	}
	
	public void SetButtons()
	{
		this.buttonsPanel = new JPanel();
		this.buttonsPanel.setLayout(new GridLayout(5, 1));
		
		this.scenariosComboBox = new JComboBox(scenarioList);
		
		this.scenariosComboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println(scenariosComboBox.getSelectedItem().toString() + " seçildi.");

				graphic.removeAll();
				graphic.repaint();
				
				switch(scenariosComboBox.getSelectedIndex())
				{
					case 0 : TrySampleScenario1();
					break;
					
					case 1 : TrySampleScenario2();
					break;
					
					case 2 : TrySampleScenario3();
					break;
					
					case 3 : TrySampleScenario4();
					break;
					
					default : TrySampleScenario1();
				}
			}
		});
		
		//this.scenariosComboBox.setBounds(this.graphic.getWidth() + 20, 120, 120, 30);
		this.buttonsPanel.add(this.scenariosComboBox);
		
		this.iterateButton = new JButton("Ýterasyon");
		//this.iterateButton.setBounds(this.graphic.getWidth() + 20, 10, 120, 30);
		this.iterateButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				kMeans.Iterate();
				graphic.repaint();
			}
		});
		this.buttonsPanel.add(this.iterateButton);
		
		this.updateColorsButton = new JButton("Renkleri güncelle");
		//this.updateColorsButton.setBounds(this.graphic.getWidth() + 20, 45, 120, 30);
		this.updateColorsButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				kMeans.UpdateColors();
				graphic.repaint();
			}
		});
		this.buttonsPanel.add(this.updateColorsButton);
		
		this.buttonsPanel.setBounds(0, 20, RIGHT_PANEL_WIDTH - 30, 120);
		this.rightPanel.add(this.buttonsPanel);
	}
	
	public void TrySampleScenario1()
	{
		Cluster redCluster = ClusterGenerator.Generate(60, Color.RED, 10, 30, 15, 30);
		Cluster greenCluster = ClusterGenerator.Generate(70, Color.GREEN, 15, 40, 15, 25);
		Cluster blueCluster = ClusterGenerator.Generate(50, Color.BLUE, 25, 45, 15, 35);
		Cluster magentaCluster = ClusterGenerator.Generate(40, Color.MAGENTA, 10, 25, 25, 40);
		
		this.graphic.AddCluster(redCluster);
		this.graphic.AddCluster(greenCluster);
		this.graphic.AddCluster(blueCluster);
		this.graphic.AddCluster(magentaCluster);
		
		Cluster[] clusters = { redCluster, greenCluster, blueCluster, magentaCluster };
		this.kMeans = new Kmeans(clusters);
	}
	
	public void TrySampleScenario2()
	{
		Cluster redCluster = ClusterGenerator.Generate(90, Color.RED, 5, 40, 5, 30);
		Cluster greenCluster = ClusterGenerator.Generate(120, Color.GREEN, 5, 30, 15, 45);
		Cluster blueCluster = ClusterGenerator.Generate(150, Color.BLUE, 25, 45, 15, 35);
		
		this.graphic.AddCluster(redCluster);
		this.graphic.AddCluster(greenCluster);
		this.graphic.AddCluster(blueCluster);
		
		Cluster[] clusters = { redCluster, greenCluster, blueCluster };
		this.kMeans = new Kmeans(clusters);
	}
	
	public void TrySampleScenario3()
	{
		Cluster redCluster = ClusterGenerator.Generate(150, Color.RED, 5, 45, 0, 40);
		Cluster blueCluster = ClusterGenerator.Generate(150, Color.BLUE, 5, 45, 15, 50);
		
		this.graphic.AddCluster(redCluster);
		this.graphic.AddCluster(blueCluster);
		
		Cluster[] clusters = { redCluster, blueCluster };
		this.kMeans = new Kmeans(clusters);
	}
	
	public void TrySampleScenario4()
	{
		Cluster redCluster = ClusterGenerator.Generate(150, Color.RED, 5, 45, 0, 50);
		Cluster blueCluster = ClusterGenerator.Generate(150, Color.BLUE, 5, 45, 0, 50);
		Cluster greenCluster = ClusterGenerator.Generate(150, Color.GREEN, 5, 45, 0, 40);
		Cluster magentaCluster = ClusterGenerator.Generate(150, Color.MAGENTA, 5, 45, 5, 50);
		
		this.graphic.AddCluster(redCluster);
		this.graphic.AddCluster(blueCluster);
		this.graphic.AddCluster(greenCluster);
		this.graphic.AddCluster(magentaCluster);
		
		Cluster[] clusters = { redCluster, blueCluster, greenCluster, magentaCluster };
		this.kMeans = new Kmeans(clusters);
	}
}
