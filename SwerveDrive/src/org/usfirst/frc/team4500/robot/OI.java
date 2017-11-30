package org.usfirst.frc.team4500.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick driveStick;
	
	public OI() {
		driveStick = new Joystick(0);
	}
	
	public double getAxis1() {
		return driveStick.getRawAxis(1);
	}
	
	public double getAxis0() {
		return driveStick.getRawAxis(0);
	}
	
	public double getAxis4() {
		return driveStick.getRawAxis(4);
	}
}
