package org.usfirst.frc.team4500.robot.subsystems;

import org.usfirst.frc.team4500.robot.RobotMap;
import org.usfirst.frc.team4500.robot.commands.Drive;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem containing all four modules that make up swerve.
 */
public class SwerveDrive extends Subsystem {

	private final double L = 29.5;
	private final double W = 29.5;
	
	private WheelModule backRight;
	private WheelModule backLeft;
	private WheelModule frontRight;
	private WheelModule frontLeft;
	
	private DoubleSolenoid gearSwitch;
	private boolean gearMode = true;
	
	/**
	 * Takes four WheelModules as parameters
	 * @param backRight
	 * @param backLeft
	 * @param frontRight
	 * @param frontLeft
	 */
	public SwerveDrive(WheelModule backRight, WheelModule backLeft, WheelModule frontRight, WheelModule frontLeft) {
    	this.backRight = backRight;
    	this.backLeft = backLeft;
    	this.frontRight = frontRight;
    	this.frontLeft = frontLeft;
    	
    	gearSwitch = new DoubleSolenoid(RobotMap.SOLPORTONE, RobotMap.SOLPORTTWO);
    }

    public void initDefaultCommand() {
        setDefaultCommand(new Drive());
    }
    
    /**
     * Calculates the four speeds and angles needed for the eight motors
     * @param x value of the joystick (strafe)
     * @param y value of the joystick (forward)
     * @param z value of the joystick (rotation)
     */
    public void calculateInfo(double x, double y, double z) {
    	double r = Math.sqrt((L * L) + (W * W));
    	y *= -1;
    	
    	SmartDashboard.putNumber("x", x);
 	    SmartDashboard.putNumber("y", y);
 	    SmartDashboard.putNumber("z", z);
 	    
    	double a = x - z * (L / r) + 0;
    	double b = x + z * (L / r);
    	double c = y - z * (W / r) + 0;
    	double d = y + z * (W / r);
    	
    	double brSpeed = Math.sqrt((a * a) + (c * c));
    	double blSpeed = Math.sqrt((a * a) + (d * d));
    	double frSpeed = Math.sqrt((b * b) + (c * c));
    	double flSpeed = Math.sqrt((b * b) + (d * d));
    	
    	double max = brSpeed;
    	if(brSpeed > max) { max = brSpeed; }
    	if(blSpeed > max) { max = blSpeed; }
    	if(frSpeed > max) { max = frSpeed; }
    	if(flSpeed > max) { max = flSpeed; }
    	
    	if(max > 1) {
    		brSpeed /= max;
    		blSpeed /= max;
    		frSpeed /= max;
    		flSpeed /= max;
    	}
    	
    	double brAngle = (Math.atan2(a, c) * 180/Math.PI);
	    double blAngle = (Math.atan2(a, d) * 180/Math.PI);
	    double frAngle = (Math.atan2(b, c) * 180/Math.PI);
	    double flAngle = (Math.atan2(b, d) * 180/Math.PI);
	    
	    SmartDashboard.putNumber("brSpeed", brSpeed);
	    SmartDashboard.putNumber("blSpeed", blSpeed);
	    SmartDashboard.putNumber("frSpeed", frSpeed);
	    SmartDashboard.putNumber("flSpeed", flSpeed);
	    
    	backRight.drive(brSpeed, brAngle);
    	backLeft.drive(blSpeed, blAngle);
    	frontRight.drive(frSpeed, frAngle);
    	frontLeft.drive(flSpeed, flAngle);
    }
    
    /**
     * Shifts the gear of the drive motors
     */
    public void shiftGear() {
		if(gearMode) {
			gearSwitch.set(Value.kForward);
			gearMode = !gearMode;
		} else {
			gearSwitch.set(Value.kReverse);
			gearMode = !gearMode;
		}
	}
}

