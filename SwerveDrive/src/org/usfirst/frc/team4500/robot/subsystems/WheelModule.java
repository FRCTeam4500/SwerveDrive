package org.usfirst.frc.team4500.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		angleMotor.setPID(0.25, 0, 0);
		angleMotor.setAllowableClosedLoopErr(8);
		angleMotor.setEncPosition(0);
    }

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new Drive_Speed());
	}
	
	public void drive(double speed, double angle) {
	    //speedMotor.set(speed);
	    angleMotor.set(angle);
	    //SmartDashboard.putNumber(Integer.toString(angleMotor.getDeviceID()), angleMotor.getEncPosition());
	}
	
	public void driveAngle(double angle) {
		
	}
}
