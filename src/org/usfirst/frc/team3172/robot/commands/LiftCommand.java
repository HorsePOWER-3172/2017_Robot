package org.usfirst.frc.team3172.robot.commands;

import org.usfirst.frc.team3172.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LiftCommand extends Command {

	public LiftCommand() {
		requires(Robot.liftSystem);

	}

	protected void initialize() {

	}

	protected void execute() {
		if (!Robot.winchSystem.enabled) {
			Robot.liftSystem.move();
		} else {
			Robot.liftSystem.stop();
		}

	}

	protected boolean isFinished() {
		return false;

	}

	protected void end() {
		Robot.liftSystem.stop();

	}

	protected void interrupted() {
		Robot.liftSystem.stop();

	}

}
