package org.usfirst.frc.team4500.robot;

import org.usfirst.frc.team4500.robot.commands.Drive_SetSetpoint;

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
	Button zero, ninety, oneEighty;
	
	public OI() {
		driveStick = new Joystick(0);
		
		zero = new JoystickButton(driveStick, 7);
		ninety = new JoystickButton(driveStick, 8);
		oneEighty = new JoystickButton(driveStick, 9);
		
		zero.whenPressed(new Drive_SetSetpoint(0));
		ninety.whenPressed(new Drive_SetSetpoint(90));
		oneEighty.whenPressed(new Drive_SetSetpoint(180));
	}
	
	public double getX() {
		return driveStick.getX() >= 0.05 || driveStick.getX() <= -0.05 ? driveStick.getX() : 0;
	}
	
	public double getY() {
		return driveStick.getY() >= 0.05 || driveStick.getY() <= -0.05 ? driveStick.getY() : 0;
	}
	
	public double getZ() {
		return driveStick.getZ() >= 0.05 || driveStick.getZ() <= -0.05 ? driveStick.getZ() : 0;
	}
	
}
