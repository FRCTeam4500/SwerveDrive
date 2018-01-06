package org.usfirst.frc.team4500.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WheelModule extends Subsystem {
	
	private TalonSRX angleMotor;
	private TalonSRX speedMotor;
	private Solenoid gearSwitch;
	
	private boolean gearMode = true;

    public WheelModule(int anglePort, int speedPort, int solPort, double P, double I, double D, boolean reverse) {
		angleMotor = new TalonSRX(anglePort);
		speedMotor = new TalonSRX(speedPort);
		gearSwitch = new Solenoid(solPort);
		
		/*
		 * 4096 units per rotation
		 */
		
		
		angleMotor.setSelectedSensorPosition(0, 0, 0);
		angleMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		// This must be be tested on weather to set to true or false
		angleMotor.setSensorPhase(false);
		// First argument is profile or something else?
		angleMotor.configAllowableClosedloopError(0, 0, 0);
		angleMotor.config_kP(0, P, 0);
		angleMotor.config_kI(0, I, 0);
		angleMotor.config_kD(0, D, 0);
		
		
		/*OLD CODE angleMotor.setPulseWidthPosition(0);
		angleMotor.setEncPosition(0);
		angleMotor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		angleMotor.reverseSensor(false);
		angleMotor.configEncoderCodesPerRev(5851);
		angleMotor.setAllowableClosedLoopErr(0);
		angleMotor.setProfile(0);
		angleMotor.setPID(P, I, D);
		angleMotor.changeControlMode(TalonControlMode.Position);*/

// OLD UNUSED CODE
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
		angle *= 1.4285;
		angleMotor.set(ControlMode.Position, angle);
	    
		/* OLD CODE SmartDashboard.putBoolean("gear", gearMode);
		SmartDashboard.putNumber("Debug angle", angle);
	    SmartDashboard.putNumber("Debug position", angleMotor.getPosition());
	    SmartDashboard.putNumber("Debug setpoint", angleMotor.getSetpoint());
	    SmartDashboard.putNumber("Debug error", angleMotor.getError());
	    SmartDashboard.putNumber("Debug encoder", angleMotor.getEncPosition());*/	
	}
	
	public void shiftGear() {
		gearSwitch.set(gearMode);
		gearMode = !gearMode;
	}
	
	public void setSetpoint(double setpoint) {
		angleMotor.set(ControlMode.Position, setpoint / 360);
	}
}
