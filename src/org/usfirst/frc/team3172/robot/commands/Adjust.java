package org.usfirst.frc.team3172.robot.commands;

import org.usfirst.frc.team3172.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Adjust extends Command {

	public Adjust() {
		requires(Robot.driveSystem);
		requires(Robot.liftSystem);
		requires(Robot.winchSystem);
		
	}

	protected void initialize() {
		System.out.println("Pressed Adjust");
		Robot.driveSystem.adjust();
		Robot.liftSystem.adjust();
		Robot.winchSystem.adjust();
	}
	
	protected boolean isFinished() {
		return true;
	}


}
