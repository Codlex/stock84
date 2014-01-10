package com.codlex;
import java.awt.Color;

import robocode.AdvancedRobot;


public class Stock84 extends AdvancedRobot {
	
	
	
	/**
	 * Main run method. 
	 */
	@Override
	public void run() {	
		initRoboCode();
		while(true) {
			// brain code goes here
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
	
}
