package com.codlex;

import java.awt.geom.Point2D;
import java.util.Set;

public class AntiGravityMovementStrategy {
	
	double xTranslation = 0;
	double yTranslation = 0;
	public AntiGravityMovementStrategy(Stock84 tank, Set<Enemy> enemies) {
		// process enemies
		processEnemies(tank.getPosition(), enemies);
		// add middle random point
		// handle walls
	}

	private void processEnemies(Point2D.Double myPosition, Set<Enemy> enemies) {
		// negative value means that we should avoid enemy tanks
		double enemyPower = -1000;
		// gravity exponent shows us growth of importance of given point
		double enemyGravityExponent = 2;
		for (Enemy enemy : enemies) {
			if (enemy.isAlive()) {
		        double force = enemyPower/Math.pow(myPosition.distance(enemy.getPosition()), enemyGravityExponent);
		        //Find the bearing from the point to us
		        double angle = Util.normalizeBearing(Math.PI/2 - Math.atan2(myPosition.getY() - enemy.getPosition().getY(), 
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
