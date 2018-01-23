package org.usfirst.frc.team3172.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team3172.robot.Robot;
import org.usfirst.frc.team3172.robot.RobotMap;
import org.usfirst.frc.team3172.robot.commands.LiftCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSystem extends Subsystem {

	static boolean _eStop = false;
	
	static double maxVel = .7;
	static double minVel = .5;
	static double range = .5;

	static double adjustAmount = 2;
	static boolean _adjust = false;

	static double shakeSpeed = .5;
	static double shakeTime = 1000;
	static double shakeInterval = 50;

	public void initDefaultCommand() {
		setDefaultCommand(new LiftCommand());
	}

	public void stop() {
		RobotMap.lift.stopMotor();

	}

	public void adjust() {
		_adjust = !_adjust;

	}

	public void eStop() {
		_eStop = true;
		stop();

	}

	public void restart() {
		_eStop = false;

	}

	public void shaky() {
		if (!_eStop) {
			double startTime = System.currentTimeMillis();
			boolean up = false;
			double dirTime = System.currentTimeMillis();
			while (System.currentTimeMillis() < startTime + shakeTime) {
				double speed = shakeSpeed;
				if (_adjust && Robot.adjustChooser.getSelected().equals("Fine")) {
					speed /= adjustAmount;
				} else if (!_adjust && !Robot.adjustChooser.getSelected().equals("Fine")) {
					speed /= adjustAmount;
				}
				if (up && !RobotMap.liftTop.get()) {
					RobotMap.lift.set(speed);
				} else if (!RobotMap.liftBottom.get()){
					RobotMap.lift.set(-speed);
				} else { //If it hits switch, switch the dir.
					up = !up;
					dirTime = System.currentTimeMillis();
					RobotMap.lift.set(0);
				}

				if (System.currentTimeMillis() >= dirTime + shakeInterval) {
					up = !up;
					dirTime = System.currentTimeMillis();
					RobotMap.lift.set(0);
				}
			}
		}

	}

	public void move() { // ToDo: Account for drive types and controllers
		if (!_eStop) {
			ArrayList<Joystick> Controllers = Robot.oi.getControllers();

			// String driveType = Robot.driveChooser.getSelected();
			String controllerType = Robot.controllerChooser.getSelected();

			if (controllerType == "Gamepad") {
				System.out.println("RAW " + Controllers.get(0).getRawAxis(3) + ", " + Controllers.get(0).getRawAxis(2));
				double up = -DriveSystem.scale(Controllers.get(0).getRawAxis(3), maxVel, minVel, range, 0);
				double down = DriveSystem.scale(Controllers.get(0).getRawAxis(2), maxVel, minVel, range, 0);
				System.out.println("SCALED " + up + ", " + down);
				if (_adjust && Robot.adjustChooser.getSelected().equals("Fine")) {
					up /= adjustAmount;
					down /= adjustAmount;
				} else if (!_adjust && !Robot.adjustChooser.getSelected().equals("Fine")) {
					up /= adjustAmount;
					down /= adjustAmount;
				}
				
				System.out.println("ADJUSTED " + up + ", " + down + "\n");

				if (down == 0 && up != 0 && !RobotMap.liftBottom.get()) {
					//System.out.println("UP" + up);
					RobotMap.lift.set(up);
				} else if (up == 0 && down != 0 && !RobotMap.liftTop.get()) {
					//System.out.println("DOWN" + down);
					RobotMap.lift.set(down);
				} else {
					stop();

				}
			}
		} else {
			stop();
			
		}
	}
}
