package org.usfirst.frc.team4500.robot.subsystems;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WheelModule extends Subsystem {
	
	private CANTalon angleMotor;
	private CANTalon speedMotor;
	private Solenoid gearSwitch;
	
	private boolean gearMode = true;

    public WheelModule(int anglePort, int speedPort, int solPort, double P, double I, double D, boolean reverse) {
		angleMotor = new CANTalon(anglePort);
		speedMotor = new CANTalon(speedPort);
		gearSwitch = new Solenoid(solPort);
		
		angleMotor.setPulseWidthPosition(0);
		angleMotor.setEncPosition(0);
		angleMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		angleMotor.reverseSensor(false);
		angleMotor.configEncoderCodesPerRev(5851);
		angleMotor.setAllowableClosedLoopErr(0);
		angleMotor.setProfile(0);
		angleMotor.setPID(P, I, D);
		angleMotor.changeControlMode(TalonControlMode.Position);
		
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
		angleMotor.set(angle*1.4285);
	    
		SmartDashboard.putBoolean("gear", gearMode);
		SmartDashboard.putNumber("Debug angle", angle);
	    SmartDashboard.putNumber("Debug position", angleMotor.getPosition());
	    SmartDashboard.putNumber("Debug setpoint", angleMotor.getSetpoint());
	    SmartDashboard.putNumber("Debug error", angleMotor.getError());
	    SmartDashboard.putNumber("Debug encoder", angleMotor.getEncPosition());	
	}
	
	public void shiftGear() {
		gearSwitch.set(gearMode);
		gearMode = !gearMode;
	}
	
	public void setSetpoint(double setpoint) {
		angleMotor.set(setpoint / 360);
	}
}
