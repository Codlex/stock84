package com.codlex;

import java.awt.geom.Point2D;

import robocode.ScannedRobotEvent;

public class Enemy {
	
	private String name;
	private double energy;
	private Point2D.Double position;
	private boolean isAlive;
	
	public String getName() {
		return name;
	}

	public Point2D.Double getPosition() {
		return position;
	}
	
	public double getEnergy() {
		return energy;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public Enemy(Point2D.Double myPosition, double myHeadingRadians, ScannedRobotEvent scannedRobotEvent) {
		this.name = scannedRobotEvent.getName();
		// calculate absolute tank position TODO: check %
		double absBearingRadians = (myHeadingRadians+scannedRobotEvent.getBearingRadians())%(2*Math.PI);
		double x = myPosition.getX()+Math.sin(absBearingRadians)*scannedRobotEvent.getDistance();
		double y = myPosition.getY()+Math.cos(absBearingRadians)*scannedRobotEvent.getDistance();
		this.position = new Point2D.Double(x, y);
		this.isAlive = true;
		this.energy = scannedRobotEvent.getEnergy();
	}
	
	
	@Override
	public String toString() {
		return this.getName() + " " + this.getPosition();
	}

	public void setIsAlive(boolean b) {
		this.isAlive = false;
	}
}