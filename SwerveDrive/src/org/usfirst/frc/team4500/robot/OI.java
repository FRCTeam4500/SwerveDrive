package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.Drive_ZeroAngle;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick driveStick;
	
	Button zeroAngle;
	
	public OI() {
		driveStick = new Joystick(0);
		
		zeroAngle = new JoystickButton(driveStick, 11);
		zeroAngle.whenPressed(new Drive_ZeroAngle());
	}
	
	public double getX() {
		return driveStick.getX() >= 0.05 ? driveStick.getX() : 0;
	}
	
	public double getY() {
		return driveStick.getY() >= 0.05 ? driveStick.getY() : 0;
	}
	
	public double getZ() {
		return driveStick.getZ() >= 0.05 ? driveStick.getZ() : 0;
	}
	
}
