package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem containing all four modules that make up swerve drive. 
 */
public class SwerveDrive extends Subsystem {

	private final double L = 29.5;
	private final double W = 29.5;
	
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
        setDefaultCommand(new Drive());
    }
    
    public void calculateInfo(double x, double y, double z) {
    	double r = Math.sqrt((L * L) + (W * W));
    	y *= -1;
    	
    	SmartDashboard.putNumber("x", x);
 	    SmartDashboard.putNumber("y", y);
 	    SmartDashboard.putNumber("z", z);
 	    
    	double a = x - z * (L / r);
    	double b = x + z * (L / r);
    	double c = y - z * (W / r);
    	double d = y + z * (W / r);
    	
    	double brSpeed = Math.sqrt((a * a) + (c * c));
    	double blSpeed = Math.sqrt((a * a) + (d * d));
    	double frSpeed = Math.sqrt((b * b) + (c * c));
    	double flSpeed = Math.sqrt((b * b) + (d * d));
    	
    	double brAngle = (Math.atan2(a, c) * 180/Math.PI) / 360;
	    double blAngle = (Math.atan2(a, d) * 180/Math.PI) / 360;
	    double frAngle = (Math.atan2(b, c) * 180/Math.PI) / 360;
	    double flAngle = (Math.atan2(b, d) * 180/Math.PI) / 360;
	    
	    SmartDashboard.putNumber("brAngle", brAngle);
	    SmartDashboard.putNumber("blAngle", blAngle);
	    SmartDashboard.putNumber("frAngle", frAngle);
	    SmartDashboard.putNumber("flAngle", flAngle);
	    
    	backRight.drive(brSpeed, brAngle);
    	backLeft.drive(blSpeed, blAngle);
    	frontRight.drive(frSpeed, frAngle);
    	frontLeft.drive(flSpeed, flAngle);
    }
    
    public void shiftGear() {
    	for(WheelModule m : modules) {
    		m.shiftGear();
    	}
    }
}

