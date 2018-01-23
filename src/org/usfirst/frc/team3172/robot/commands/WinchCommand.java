package org.usfirst.frc.team3172.robot.commands;

import org.usfirst.frc.team3172.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class WinchCommand extends Command {

	public WinchCommand() {
		requires(Robot.winchSystem);

	}

	protected void initialize() {

	}

	protected void execute() {
			Robot.winchSystem.move();

	}

	protected boolean isFinished() {
		return false;

	}

	protected void end() {
		Robot.winchSystem.stop();

	}

	protected void interrupted() {
		Robot.winchSystem.stop();

	}

}
