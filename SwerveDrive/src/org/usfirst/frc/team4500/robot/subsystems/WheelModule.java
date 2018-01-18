package org.usfirst.frc.team4500.robot.subsystems;


import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.util.Util;

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
	
	private double lastAngle = 0;

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

		int absolutePosition = angleMotor.getSelectedSensorPosition(RobotMap.TIMEOUT);
		SmartDashboard.putNumber("absOne", absolutePosition);
		angleMotor.setSelectedSensorPosition(absolutePosition, 0, RobotMap.TIMEOUT);
		angleMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		angleMotor.setSensorPhase(false);	
		angleMotor.configPeakOutputForward(7/12.0, RobotMap.TIMEOUT);
		angleMotor.configPeakOutputReverse(-7/12.0, RobotMap.TIMEOUT);
		angleMotor.configAllowableClosedloopError(0, 0, RobotMap.TIMEOUT);
		angleMotor.config_kP(0, RobotMap.P, RobotMap.TIMEOUT);
		angleMotor.config_kI(0, RobotMap.I, RobotMap.TIMEOUT);
		angleMotor.config_kD(0, RobotMap.D, RobotMap.TIMEOUT);
		angleMotor.config_kF(0, RobotMap.F, RobotMap.TIMEOUT);
		//40960
		angleMotor.configMotionCruiseVelocity(20480*2, RobotMap.TIMEOUT);
		angleMotor.configMotionAcceleration(20480*2, RobotMap.TIMEOUT);
		
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
		SmartDashboard.putNumber("A: PulseWidthPos", angleMotor.getSensorCollection().getPulseWidthPosition() / 16.2539); 
		SmartDashboard.putNumber("A: SelectedSensorPosition", angleMotor.getSelectedSensorPosition(0) / 16.2539); 
		SmartDashboard.putNumber("A: lastAngle", lastAngle); 
	    //speedMotor.set(ControlMode.PercentOutput, speed);
	    //angleMotor.set(ControlMode.Position, angle);
		double spOne = lastAngle - angle;
		double spTwo = lastAngle - Util.convertDeg(angle);
		double dist = lastAngle - spTwo;
		if(Math.abs(dist) <= Math.abs(spOne)) {
			angle = dist;
		}
		lastAngle = angle;
		angle *= RobotMap.COUNTPERDEG;
		angleMotor.set(ControlMode.MotionMagic, angle);
	}
	
	public void setZero() {
		angleMotor.setSelectedSensorPosition(0, 0, 0);
	}
}
