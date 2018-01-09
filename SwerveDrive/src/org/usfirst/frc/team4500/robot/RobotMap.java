package org.usfirst.frc.team4500.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public final static int BRSPEEDMOTOR = 4;
	public final static int BRANGLEMOTOR = 8;
	
	public final static int BLSPEEDMOTOR = 1;
	public final static int BLANGLEMOTOR = 5;
	
	public final static int FRSPEEDMOTOR = 3;
	public final static int FRANGLEMOTOR = 7;
	
	public final static int FLSPEEDMOTOR = 2;
	public final static int FLANGLEMOTOR = 6;
	
	public final static int COMPRESSOR = 0;
	public final static int SOLPORTONE = 0;
	public final static int SOLPORTTWO = 1;
	
	public final static double P = 1.2;
	public final static double I = 0.0072;
	public final static double D = 15;
	
	public final static double COUNTPERDEG = 16.2539;
}