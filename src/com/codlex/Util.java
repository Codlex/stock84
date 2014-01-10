package com.codlex;

public class Util {
	
	/**
	 * Normalizes bearing to -pi to pi 
	 * @param angle to normalize
	 * @return normalized angle
	 */
	public static double normalizeBearing(double angle) {
		if (angle > Math.PI)
			angle -= 2*Math.PI;
		if (angle < -Math.PI)
			angle += 2*Math.PI;
		return angle;
	}
}
