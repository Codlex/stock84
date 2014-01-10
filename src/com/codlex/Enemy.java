package com.codlex;

import java.awt.geom.Point2D;

import robocode.ScannedRobotEvent;

public class Enemy implements Comparable<Enemy> {
	
	private String name;
	private Point2D.Double position;
	private boolean isAlive;
	
	public String getName() {
		return name;
	}

	public Point2D.Double getPosition() {
		return position;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public Enemy(Point2D.Double myPosition, double myHeadingRadians, ScannedRobotEvent scannedRobotEvent) {
		this.name = scannedRobotEvent.getName();
		// calculate absolute tank position
		double absBearingRadians = (myHeadingRadians+scannedRobotEvent.getBearingRadians())%(2*Math.PI);
		double x = myPosition.getX()+Math.sin(absBearingRadians)*scannedRobotEvent.getDistance();
		double y = myPosition.getY()+Math.cos(absBearingRadians)*scannedRobotEvent.getDistance();
		this.position = new Point2D.Double(x, y);
		this.isAlive = true;
	}
	
	/**
	 * Implemented so that data structures differ bots by their names.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Enemy)) {
			return false;
		}
		Enemy enemy = (Enemy) obj;
		return this.name.equals(enemy.name); 
	}

	/**
	 * Implemented so that data structures differ bots by their names.
	 */
	@Override
	public int compareTo(Enemy enemy) {
		return this.name.compareTo(enemy.name);
	}
	
	/**
	 * Implemented so that data structures differ bots by their names.
	 */
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
}