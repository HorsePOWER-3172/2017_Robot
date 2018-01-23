package org.usfirst.frc.team3172.robot.commands;

import org.usfirst.frc.team3172.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class eStop extends Command {

	public eStop() {
		requires(Robot.driveSystem);
		requires(Robot.liftSystem);
		requires(Robot.winchSystem);
		
	}

	protected void initialize() {
		System.out.println("Pressed eStop");
		Robot.driveSystem.eStop();
		Robot.liftSystem.eStop();
		Robot.winchSystem.eStop();
	}
	
	protected boolean isFinished() {
		return true;
	}


}
