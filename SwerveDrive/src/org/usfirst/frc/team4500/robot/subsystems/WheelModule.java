package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WheelModule extends Subsystem {

	private Talon angleMotor;
	private Talon speedMotor;
	private PIDController pidController;
	
	public WheelModule(int angleMotor, int speedMotor, int encoder) {
    	this.angleMotor = new Talon(angleMotor);
    	this.speedMotor = new Talon(speedMotor);
    	pidController = new PIDController(1, 0, 0, new AnalogInput(encoder), this.angleMotor);
    	
    	pidController.setOutputRange(-1, 1);
    	pidController.setContinuous();
    	pidController.enable();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double speed, double angle) {
    	speedMotor.set(speed);
    	
    	double setpoint = angle * (RobotMap.MAX_VOLTS * 0.5) + (RobotMap.MAX_VOLTS * 0.5);
    	if (setpoint < 0) {
    		setpoint = RobotMap.MAX_VOLTS + setpoint;
    	}
    	if (setpoint > RobotMap.MAX_VOLTS) {
    		setpoint = setpoint - RobotMap.MAX_VOLTS;
    	}
    	
    	pidController.setSetpoint(setpoint);
    }
}

