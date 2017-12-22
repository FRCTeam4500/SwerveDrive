package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.commands.Drive_Speed;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WheelModule extends Subsystem {
	
	private CANTalon angleMotor;
	private CANTalon speedMotor;

    public WheelModule(int anglePort, int speedPort) {
    	angleMotor = new CANTalon(anglePort);
    	speedMotor = new CANTalon(speedPort);
    }

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive_Speed());
	}
	
	public void drive(double speed) {
	    speedMotor.set(speed);
	 }
}

