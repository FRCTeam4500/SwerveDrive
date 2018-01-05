package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem containing all four modules that make up swerve drive. 
 */
public class SwerveDrive extends Subsystem {

	public final double L = 29.5;
	public final double W = 29.5;
	
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
    	
    	double brAngle = Math.atan2 (a, c) * 180/Math.PI;
	    double blAngle = Math.atan2 (a, d) * 180/Math.PI;
	    double frAngle = Math.atan2 (b, c) * 180/Math.PI;
	    double flAngle = Math.atan2 (b, d) * 180/Math.PI;
	    
	    brAngle *= RobotMap.countPerDeg;
	    blAngle *= RobotMap.countPerDeg;
	    frAngle *= RobotMap.countPerDeg;
	    flAngle *= RobotMap.countPerDeg;
	    
	    brAngle /= 360;
	    blAngle /= 360;
	    frAngle /= 360;
	    flAngle /= 360;
	    
	    SmartDashboard.putNumber("brAngle", brAngle);
	    SmartDashboard.putNumber("blAngle", blAngle);
	    SmartDashboard.putNumber("frAngle", frAngle);
	    SmartDashboard.putNumber("flAngle", flAngle);
	    
    	//backRight.drive(brSpeed, brAngle);
    	//backLeft.drive(blSpeed, blAngle);
    	frontRight.drive(frSpeed, frAngle);
    	//frontLeft.drive(flSpeed, flAngle);
    }
    
    public void zeroAngle() {
    	backRight.zero();
    	backLeft.zero();
    	frontRight.zero();
    	frontLeft.zero();
    }
    
    /*public void calculateAngles(double x1, double y1, double x2) {
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
	    
	    brAngle *= RobotMap.countPerDeg;
	    blAngle *= RobotMap.countPerDeg;
	    frAngle *= RobotMap.countPerDeg;
	    flAngle *= RobotMap.countPerDeg;
	    
	    SmartDashboard.putNumber("brAngle", brAngle);
	    SmartDashboard.putNumber("blAngle", blAngle);
	    SmartDashboard.putNumber("frAngle", frAngle);
	    SmartDashboard.putNumber("flAngle", flAngle);
    }*/
    
    public boolean getFinished() {
    	int finished = 0;
    	for(int i = 0; i < modules.length; i++) {
    		//finished += modules[i].isFinished();
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

