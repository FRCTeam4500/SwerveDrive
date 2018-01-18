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
}
