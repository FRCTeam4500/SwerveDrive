package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.commands.Drive_Speed;
import org.usfirst.frc.team4500.robot.commands.Drive_Angle;

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
	private double[] speeds = {0, 0, 0, 0};
    private double[] angles = {0, 0, 0, 0};
	
	public SwerveDrive(WheelModule backRight, WheelModule backLeft, WheelModule frontRight, WheelModule frontLeft) {
    	this.backRight = backRight;
    	this.backLeft = backLeft;
    	this.frontRight = frontRight;
    	this.frontLeft = frontLeft;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Drive_Angle());
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
	    
    	speeds[0] = brSpeed;
    	speeds[1] = blSpeed;
    	speeds[2] = frSpeed;
    	speeds[3] = flSpeed;
	    for(int i = 0; i < modules.length; i++) {;
	    	modules[i].drive(speeds[i]);
	    }
	    
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
	    
	    angles[0] = brAngle;
	    angles[1] = blAngle;
	    angles[2] = frAngle;
	    angles[3] = flAngle;
	    for(int i = 0; i < modules.length; i++) {
	    	modules[i].setSetpoint(angles[i]);
	    }
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

