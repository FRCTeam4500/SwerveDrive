package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.commands.Group_Drive;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem containing all four modules that make up swerve drive. 
 */
public class SwerveDrive extends Subsystem {

	public final double L = 1;
	public final double W = 1;
	
	private WheelModule backRight;
	private WheelModule backLeft;
	private WheelModule frontRight;
	private WheelModule frontLeft;
	
	private WheelModule[] modules = {backRight, backLeft, frontRight, frontLeft};
	
	public SwerveDrive(WheelModule backRight, WheelModule backLeft, WheelModule frontRight, WheelModule frontLeft) {
    	this.backRight = backRight;
    	this.backLeft = backLeft;
    	this.frontRight = frontRight;
    	this.frontLeft = frontLeft;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Group_Drive());
    }
    
    public void calculateSpeeds(double x1, double y1, double x2) {
    	double r = Math.sqrt((L * L) + (W * W));
    	y1 *= -1;
    	
    	double a = x1 - x2 * (L / r);
    	double b = x1 + x2 * (L / r);
    	double c = y1 - x2 * (W / r);
    	double d = y1 + x2 * (W / r);
    	
    	double brSpeed = Math.sqrt((a * a) + (d * d));
    	double blSpeed = Math.sqrt((a * a) + (c * c));
    	double frSpeed = Math.sqrt((b * b) + (c * c));
    	double flSpeed = Math.sqrt((b * b) + (c * c));
	    
    	modules[0].drive(brSpeed);
    	modules[1].drive(blSpeed);
    	modules[2].drive(frSpeed);
    	modules[3].drive(flSpeed);
    }
    
    public void calculateAngles(double x1, double y1, double x2) {
    	double r = Math.sqrt((L * L) + (W * W));
    	y1 *= -1;
    	
    	double a = x1 - x2 * (L / r);
    	double b = x1 + x2 * (L / r);
    	double c = y1 - x2 * (W / r);
    	double d = y1 + x2 * (W / r);
    	
    	double brAngle = Math.atan2 (a, d) / Math.PI;
	    double blAngle = Math.atan2 (a, c) / Math.PI;
	    double frAngle = Math.atan2 (b, d) / Math.PI;
	    double flAngle = Math.atan2 (b, c) / Math.PI;
	    
	    modules[0].setSetpoint(brAngle);
	    modules[1].setSetpoint(blAngle);
	    modules[2].setSetpoint(frAngle);
	    modules[3].setSetpoint(flAngle);
    }
    
    public boolean getFinished() {
    	int finished = 0;
    	for(int i = 0; i < modules.length; i++) {
    		finished += modules[i].isFinished();
    	}
    	if(finished == 4) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public WheelModule[] getModule() {
    	return modules;
    }
}

