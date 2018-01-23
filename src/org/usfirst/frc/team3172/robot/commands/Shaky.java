package org.usfirst.frc.team3172.robot.commands;

import org.usfirst.frc.team3172.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Shaky extends Command {
	
	public Shaky() {
		requires(Robot.liftSystem); //Not sure if necessary. If unalign works now, it's because of this.
		
	}
	
	protected void initialize() {
		System.out.println("Pressed Shaky");
		Robot.liftSystem.shaky();
	}
	
	protected boolean isFinished() {
		return true;
	}

}
