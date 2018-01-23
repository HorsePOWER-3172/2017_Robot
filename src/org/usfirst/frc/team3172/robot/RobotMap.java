package org.usfirst.frc.team3172.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
 
public class RobotMap {
	
	public static SpeedController driveSystemLeft;
	public static SpeedController driveSystemRight;
	public static SpeedController lift;
	public static SpeedController winch;
	
	public static DigitalInput liftTop;
	public static DigitalInput liftBottom;
	
	public static RobotDrive robotDrive; //ToDo: What is the difference between robotDrive and driveSystem?
	
	public static void init() { //Example code does not have init in robotmap. 2016 code does. Following 2016 code.
		driveSystemLeft = new VictorSP(1);
		driveSystemRight = new Spark(0);
		lift = new Talon(3);
		winch = new Talon(2);
		
		liftTop = new DigitalInput(0);
		liftBottom = new DigitalInput(1);
		
		robotDrive = new RobotDrive(driveSystemLeft, driveSystemRight);
		
		robotDrive.setSafetyEnabled(true); //set to false for testing. Removes "Output not updated often enough"
		robotDrive.setExpiration(0.1);
		robotDrive.setSensitivity(0.5);
		robotDrive.setMaxOutput(1.0);
		
	}
	
}
