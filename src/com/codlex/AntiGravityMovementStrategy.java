package com.codlex;

import java.awt.geom.Point2D;
import java.util.Map;
import java.util.Set;

public class AntiGravityMovementStrategy {
	
	double xTranslation = 0;
	double yTranslation = 0;
	
	public AntiGravityMovementStrategy(Stock84 tank, Map<String, Enemy> enemies) {
		// process enemies
		processEnemies(tank.getPosition(), enemies);
		// handle walls
		processWalls(tank.getPosition(), tank.getBattleFieldHeight(), tank.getBattleFieldWidth());
		// add middle random point of antigravity
		processMiddlePoint();
	}

	private void processMiddlePoint() {
		// TODO: implement this
		
	}

	private void processWalls(Point2D.Double position, double battleFieldHeight, double battleFieldWidth) {
		// we use absolute value here because we know how to handle which wall side
		double wallPower = 1000;
		
		//              -----i--
		//              |    | |
		//              i----t-i
		//              |    | |
		//              -----i--
		//  idea is to take horizontal and vertical intersection with walls so that we keep away from the wall
		
		Point2D.Double rightWallInterestingPoint = new Point2D.Double(battleFieldWidth, position.getY());
	    this.xTranslation += wallPower/Math.pow(position.distance(rightWallInterestingPoint), 2);
	    Point2D.Double leftWallInterestingPoint = new Point2D.Double(0, position.getY());
	    this.xTranslation -= wallPower/Math.pow(position.distance(leftWallInterestingPoint), 2);
	    Point2D.Double bottomWallInterestingPoint = new Point2D.Double(position.getX(), battleFieldHeight);
	    this.yTranslation += wallPower/Math.pow(position.distance(bottomWallInterestingPoint), 2);
	    Point2D.Double topWallInterestingPoint = new Point2D.Double(position.getX(), 0);
	    this.yTranslation -= wallPower/Math.pow(position.distance(topWallInterestingPoint), 2);
		
	}

	private void processEnemies(Point2D.Double myPosition, Map<String, Enemy> enemies) {
		// negative value means that we should avoid enemy tanks
		double enemyPower = -1000;
		// gravity exponent shows us growth of importance of given point
		double enemyGravityExponent = 2;
		for (Enemy enemy : enemies.values()) {
			if (enemy.isAlive()) {
		        double force = enemyPower/Math.pow(myPosition.distance(enemy.getPosition()), enemyGravityExponent);
		        //Find the bearing from the point to us
		        double angle = Util.normalizeBearing(Math.atan2(myPosition.getY() - enemy.getPosition().getY(), 
		        													        myPosition.getX() - enemy.getPosition().getX())); 
		        // take every direction separately
		        this.xTranslation += Math.sin(angle) * force;
		        this.yTranslation += Math.cos(angle) * force;
			}
		}
		
	}

	public double getXTranslation() {
		System.out.println("X translation: " + this.xTranslation);
		return this.xTranslation;
	}

	public double getYTranslation() {

		System.out.println("Y translation: " + this.yTranslation);
		return this.yTranslation;
	}
}
