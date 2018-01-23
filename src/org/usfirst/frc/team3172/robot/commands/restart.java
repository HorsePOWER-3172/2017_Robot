package org.usfirst.frc.team3172.robot.commands;

import org.usfirst.frc.team3172.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class restart extends Command {

	public restart() {
		requires(Robot.driveSystem);
		requires(Robot.liftSystem);
		requires(Robot.winchSystem);

	}

	protected void initialize() {
		System.out.println("Pressed restart");
		Robot.driveSystem.restart();
		Robot.liftSystem.restart();
		Robot.winchSystem.restart();
	}
	
	protected boolean isFinished() {
		return true;
	}


}
