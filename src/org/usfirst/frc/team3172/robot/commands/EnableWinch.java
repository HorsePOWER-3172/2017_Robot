package org.usfirst.frc.team3172.robot.commands;

import org.usfirst.frc.team3172.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class EnableWinch extends Command {
	
	public EnableWinch() {
		requires(Robot.winchSystem); //Not sure if necessary. If unalign works now, it's because of this.
		
	}
	
	protected void initialize() {
		System.out.println("Pressed Winch");
		Robot.winchSystem.enabled = !Robot.winchSystem.enabled;
		Robot.winchSystem.eTime = System.currentTimeMillis();
	}
	
	protected boolean isFinished() {
		return true;
	}

}
