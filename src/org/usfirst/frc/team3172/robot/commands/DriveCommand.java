package org.usfirst.frc.team3172.robot.commands;

import org.usfirst.frc.team3172.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveCommand extends Command {
	
	public DriveCommand() {
		requires(Robot.driveSystem);
		
	}
	
	protected void initialize() {
		
	}

	protected void execute() {
		
		Robot.driveSystem.drive();
		
	}
	
	protected boolean isFinished() {
		return false;
		
	}
	
	protected void end() {
		Robot.driveSystem.stop();
		
	}
	
	protected void interrupted() {
		Robot.driveSystem.stop();
		
	}
	
	
}
