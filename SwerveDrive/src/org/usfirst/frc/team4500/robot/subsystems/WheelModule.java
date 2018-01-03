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

    public WheelModule(int anglePort, int speedPort, double P, double I, double D, boolean reverse) {
		angleMotor = new CANTalon(anglePort);
		speedMotor = new CANTalon(speedPort);
		
		angleMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		angleMotor.reverseSensor(reverse); // false
		angleMotor.changeControlMode(TalonControlMode.Position);
		angleMotor.setPID(P, I, D);
		//angleMotor.setAllowableClosedLoopErr(8);
		angleMotor.setEncPosition(0);
		angleMotor.configEncoderCodesPerRev(5851);
		//angleMotor.setForwardSoftLimit(1);
		//angleMotor.setReverseSoftLimit(-1);
		angleMotor.enableControl();
    }

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new Drive_Speed());
	}
	
	public void drive(double speed, double angle) {
	    //speedMotor.set(speed);
		angleMotor.set(angle);
	    
		SmartDashboard.putNumber("Debug angle", angle);
	    SmartDashboard.putNumber("Debug position", angleMotor.getPosition());
	    SmartDashboard.putNumber("Debug setpoint", angleMotor.getSetpoint());
	    SmartDashboard.putNumber("Debug error", angleMotor.getError());
	    SmartDashboard.putNumber("Debug encoder", angleMotor.getEncPosition());
		
		//SmartDashboard.putNumber(Integer.toString(angleMotor.getDeviceID()), angleMotor.getEncPosition());
	}
	
	public void zero() {
		angleMotor.setEncPosition(0);
	}
	
	public void driveAngle(double angle) {
		
	}
}
