package org.usfirst.frc.team4500.robot.subsystems;


import org.usfirst.frc.team4500.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem containing each individual module
 * Grouped together through the SwerveDrive class
 */
public class WheelModule extends Subsystem {
	
	private TalonSRX angleMotor;
	private TalonSRX speedMotor;
	
	private String id;

	/**
	 * Constructor for each individual module
	 * @param anglePort port for the angle motor
	 * @param speedPort port for the speed motor
	 * @param inverted weather to invert the speed motor
	 * @param id the identifier for the module
	 */
    public WheelModule(int anglePort, int speedPort, boolean inverted, String id) {
    	this.id = id;
    	
		angleMotor = new TalonSRX(anglePort);
		speedMotor = new TalonSRX(speedPort);
		
		angleMotor.setSelectedSensorPosition(0, 0, 0);
		angleMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		angleMotor.setSensorPhase(false);	
		angleMotor.configAllowableClosedloopError(0, 0, 0);
		angleMotor.config_kP(0, RobotMap.P, 0);
		angleMotor.config_kI(0, RobotMap.I, 0);
		angleMotor.config_kD(0, RobotMap.D, 0);
		
		speedMotor.setInverted(inverted);
    }

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new Drive_Speed());
	}
	
	/**
	 * Sets the module to the desired speed and angle
	 * @param speed for the drive motor
	 * @param angle for the angle motor 
	 */
	public void drive(double speed, double angle) {
	    speedMotor.set(ControlMode.PercentOutput, speed);
		//angleMotor.set(ControlMode.Position, angle * RobotMap.COUNTPERDEG);
		
		SmartDashboard.putNumber(id, speedMotor.getBusVoltage());
	}
}
