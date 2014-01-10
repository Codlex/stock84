package com.codlex;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;


public class Stock84 extends AdvancedRobot {
	
	// enemy map 
	TreeSet<Enemy> enemies = new TreeSet<Enemy>();
	
	/**
	 * Main run method. 
	 */
	@Override
	public void run() {	
		initRoboCode();
		while(true) {
			
			execute();
		}
	}
	
	/**
	 * Basic BOT settings for RoboCode specific stuff. 
	 */
	private void initRoboCode() {
		setColors(Color.BLACK,Color.BLACK,Color.PINK);
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
	}

	
	
	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		Enemy enemy = new Enemy(new Point2D.Double(getX(), getY()), getHeadingRadians(), event);
		// remove old enemy entity if present
		this.enemies.remove(enemy);
		// add new enemy entity
		this.enemies.add(enemy);
	}
}