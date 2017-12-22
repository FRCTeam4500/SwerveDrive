package org.usfirst.frc.team4500.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//public final static double MAX_VOLTS = 4.95;
	
	public final static int brSpeedMotor = 4;
	public final static int brAngleMotor = 8;
	//public final static int brEncoder = 0;
	
	public final static int blSpeedMotor = 1;
	public final static int blAngleMotor = 5;
	//public final static int blEncoder = 0;
	
	public final static int frSpeedMotor = 3;
	public final static int frAngleMotor = 7;
	//public final static int frEncoder = 0;
	
	public final static int flSpeedMotor = 2;
	public final static int flAngleMotor = 6;
	//public final static int flEncoder = 0;
	
	public final static double countPerDeg = 16.2539;
}