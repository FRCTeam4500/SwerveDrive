package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SwerveDrive extends Subsystem {

	public final double L = 1;
	public final double W = 1;
	
	private WheelModule backRight;
	private WheelModule backLeft;
	private WheelModule frontRight;
	private WheelModule frontLeft;
	
	public SwerveDrive(WheelModule backRight, WheelModule backLeft, WheelModule frontRight, WheelModule frontLeft) {
    	this.backRight = backRight;
    	this.backLeft = backLeft;
    	this.frontRight = frontRight;
    	this.frontLeft = frontLeft;
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }
    
    public void drive(double x1, double y1, double x2) {
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
    	
    	double brAngle = Math.atan2 (a, d) / Math.PI;
	    double blAngle = Math.atan2 (a, c) / Math.PI;
	    double frAngle = Math.atan2 (b, d) / Math.PI;
	    double flAngle = Math.atan2 (b, c) / Math.PI;
	    
	    backRight.drive (brSpeed, brAngle);
	    backLeft.drive (blSpeed, blAngle);
	    frontRight.drive (frSpeed, frAngle);
	    frontLeft.drive (flSpeed, flAngle);    
    }
}

