package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.commands.Drive_Speed;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Subsystem containing the information for a individual module.
 */
public class WheelModule extends PIDSubsystem {
	
	private CANTalon angleMotor;
	private CANTalon speedMotor;

    public WheelModule(int anglePort, int speedPort) {
    	super("WheelModule", 1, 0, 0);
    	angleMotor = new CANTalon(anglePort);
    	speedMotor = new CANTalon(speedPort);
    	getPIDController().setContinuous(true);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Drive_Speed());
    }

    protected double returnPIDInput() {
        return angleMotor.getPosition();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	angleMotor.pidWrite(output);
    }
    
    public void drive(double speed) {
    	speedMotor.set(speed);
    }
    
    public int isFinished() {
    	if(getSetpoint() - getPosition() < 0.1) {
    		return 1;
    	} else {
    		return 0;
    	}
    }
}
