package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.commands.Drive_Speed;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

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
		
		angleMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		angleMotor.reverseSensor(false);
		angleMotor.changeControlMode(TalonControlMode.Position);
		angleMotor.setPID(1, 0, 0);
		angleMotor.setAllowableClosedLoopErr(16);
    }

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new Drive_Speed());
	}
	
	public void drive(double speed) {
	    speedMotor.set(speed);
	}
	
	public void driveAngle(double angle) {
		
	}
}
