package org.usfirst.frc.team4500.robot.util;

public class Util {
	
	/**
	 * Converts an angle in the range [0,+-180] to [0,360]
	 * @param angle angle calculated through the formulas for swerve in the range 0 to +-180
	 * @return angle in the range 0 to 360
	 */
	public static double convertDeg(double angle) {
		return (angle + 360) % 360;
	}
	
	/**
	 * Gets the quadrant an angle is in to figure out if the formula needs to be adjusted
	 * [0,90] = 1
	 * [91,180] = 2
	 * [-90,-180] = 3
	 * [-1, -90] = 4
	 * @param angle angle calculated through the formulas for swerve
	 * @return the quadrant that the angle is in
	 */
	public static int getQuardrant(double angle) {
		int quad = 0;
		if (angle >= 0 && angle <= 90) {
			quad = 1;
		} else if (angle >= 91 && angle <= 180) {
			quad = 2;
		} else if (angle >= -180 && angle <= -90) {
			quad = 3;
		} else if (angle >= -90 && angle <= -1) {
			quad = 4;
		}
		return quad;
	}

}
