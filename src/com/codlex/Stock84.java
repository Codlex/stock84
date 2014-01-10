package com.codlex;
import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
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
		setTurnRadarRightRadians(2*Math.PI);

		while(true) {
			// rotate radar all the time
			setTurnRadarLeftRadians(2*Math.PI);
			doMovement();
			execute();
		}
	}
	
	private void doMovement() {
		// currently implement anti-gravity movement strategy
		AntiGravityMovementStrategy antiGravityMovementStrategy = new AntiGravityMovementStrategy(this, this.enemies);
		moveTo(getX() - antiGravityMovementStrategy.getXTranslation(), getY() - antiGravityMovementStrategy.getYTranslation());		
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

	public Double getPosition() {
		return new Point2D.Double(getX(), getY());
	}
	
	
	
	/////////////////////////////////////////////////////////////////
	// to REFACTOR:
	/**Move towards an x and y coordinate**/
	void moveTo(double x, double y) {
	    double dist = 20; 
	    double angle = Math.toDegrees(absBearing(getPosition(),new Point2D.Double(x,y)));
	    double r = turnTo(angle);
	    setAhead(dist * r);
	}


	/**Turns the shortest angle possible to come to a heading, then returns the direction the
	the bot needs to move in.**/
	int turnTo(double angle) {
	    double ang;
    	int dir;
	    ang = Util.normalizeBearing(getHeading() - angle);
	    if (ang > 90) {
	        ang -= 180;
	        dir = -1;
	    }
	    else if (ang < -90) {
	        ang += 180;
	        dir = -1;
	    }
	    else {
	        dir = 1;
	    }
	    setTurnLeft(ang);
	    return dir;
	}
	
	//gets the absolute bearing between to x,y coordinates
	public double absBearing( Point2D.Double position1, Point2D.Double position2 )
	{
		double xo = position2.getX()-position1.getX();
		double yo = position2.getY()-position1.getY();
		double h = position1.distance(position2);
		if( xo > 0 && yo > 0 )
		{
			return Math.asin( xo / h );
		}
		if( xo > 0 && yo < 0 )
		{
			return Math.PI - Math.asin( xo / h );
		}
		if( xo < 0 && yo < 0 )
		{
			return Math.PI + Math.asin( -xo / h );
		}
		if( xo < 0 && yo > 0 )
		{
			return 2.0*Math.PI - Math.asin( -xo / h );
		}
		return 0;
	}
	
	
}