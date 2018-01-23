package org.usfirst.frc.team3172.robot.commands;

import org.usfirst.frc.team3172.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Unalign extends Command {

	public Unalign() {
		requires(Robot.driveSystem); // Not sure if necessary. If unalign works
										// now, it's because of this.

	}

	protected void initialize() {
		System.out.println("Pressed Unalign");
		Robot.driveSystem.unalign();
	}
	
	protected boolean isFinished() {
		return true;
	}


}
