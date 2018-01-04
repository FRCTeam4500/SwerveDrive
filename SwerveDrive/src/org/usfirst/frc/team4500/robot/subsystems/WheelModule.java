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
		
		int absPos = angleMotor.getPulseWidthPosition();
		angleMotor.setEncPosition(absPos);
		angleMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		angleMotor.reverseSensor(false);
		angleMotor.configEncoderCodesPerRev(5851);
		angleMotor.setAllowableClosedLoopErr(0);
		angleMotor.setProfile(0);
		angleMotor.setPID(P, I, D);
		angleMotor.changeControlMode(TalonControlMode.Position);
		/*angleMotor.setForwardSoftLimit(5);
		angleMotor.setReverseSoftLimit(-5);
		angleMotor.enableForwardSoftLimit(true);
		angleMotor.enableReverseSoftLimit(true);*/
		
//		angleMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
//		angleMotor.reverseSensor(reverse); // false
//		angleMotor.changeControlMode(TalonControlMode.Position);
//		angleMotor.setPID(P, I, D);
//		angleMotor.setAllowableClosedLoopErr(0);
//		angleMotor.setPulseWidthPosition(0);
//		angleMotor.configEncoderCodesPerRev(5851);
//		angleMotor.setForwardSoftLimit(5);
//		angleMotor.setReverseSoftLimit(-5);
//		angleMotor.enableForwardSoftLimit(true);
//		angleMotor.enableControl();
    }

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new Drive_Speed());
	}
	
	public void drive(double speed, double angle) {
	    //speedMotor.set(speed);
		//angleMotor.setSetpoint(1400);
	    
		SmartDashboard.putNumber("Debug angle", angle);
	    SmartDashboard.putNumber("Debug position", angleMotor.getPosition());
	    SmartDashboard.putNumber("Debug setpoint", angleMotor.getSetpoint());
	    SmartDashboard.putNumber("Debug error", angleMotor.getError());
	    SmartDashboard.putNumber("Debug encoder", angleMotor.getEncPosition());
		
		//SmartDashboard.putNumber(Integer.toString(angleMotor.getDeviceID()), angleMotor.getEncPosition());
	}
	
	public void zero() {
		angleMotor.setPulseWidthPosition(0);
	}
	
	public void setSetpoint(double setpoint) {
		angleMotor.set(setpoint);
	}
	
	public void driveAngle(double angle) {
		
	}
}
