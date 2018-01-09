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
 *
 */
public class WheelModule extends Subsystem {
	
	private TalonSRX angleMotor;
	private TalonSRX speedMotor;

    public WheelModule(int anglePort, int speedPort) {
		angleMotor = new TalonSRX(anglePort);
		speedMotor = new TalonSRX(speedPort);
		
		angleMotor.setSelectedSensorPosition(0, 0, 0);
		angleMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		angleMotor.setSensorPhase(false);	
		angleMotor.configAllowableClosedloopError(0, 0, 0);
		angleMotor.config_kP(0, RobotMap.P, 0);
		angleMotor.config_kI(0, RobotMap.I, 0);
		angleMotor.config_kD(0, RobotMap.D, 0);
    }

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new Drive_Speed());
	}
	
	public void drive(double speed, double angle) {
	    //speedMotor.set(speed);
		angleMotor.set(ControlMode.Position, angle * RobotMap.COUNTPERDEG);
	}
}
