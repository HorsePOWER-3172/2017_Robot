package org.usfirst.frc.team3172.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team3172.robot.Robot;
import org.usfirst.frc.team3172.robot.RobotMap;
import org.usfirst.frc.team3172.robot.commands.WinchCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class WinchSystem extends Subsystem {

	public boolean _eStop = false;
	public double eTime = 0;

	public boolean enabled = false;
	public boolean _adjust = false;

	static double adjustAmount = 2;

	static double maxVel = .7;
	static double minVel = .5;
	static double range = .5;

	public void initDefaultCommand() {
		setDefaultCommand(new WinchCommand());
	}

	public void stop() {
		RobotMap.winch.set(0);

	}

	public void eStop() {
		_eStop = true;
		stop();

	}

	public void restart() {
		_eStop = false;

	}

	public void adjust() {
		_adjust = !_adjust;
	}

	public void move() { // ToDo: Account for drive types and controllers
		if (enabled) {
			System.out.println("ENABLED");
			if (System.currentTimeMillis() <= eTime + 150) {
				System.out.println("VIB");
				Robot.oi.getControllers().get(0).setRumble(Joystick.RumbleType.kRightRumble, 1);
			} else {
				Robot.oi.getControllers().get(0).setRumble(Joystick.RumbleType.kRightRumble, 0);
			}
		} else {
			stop();
			System.out.println("DISABLED");
			if (System.currentTimeMillis() <= eTime + 150) {
				System.out.println("VIB");
				Robot.oi.getControllers().get(0).setRumble(Joystick.RumbleType.kLeftRumble, 1);
			} else {
				Robot.oi.getControllers().get(0).setRumble(Joystick.RumbleType.kLeftRumble, 0);
			}
		}
		if (!_eStop && enabled) {
			ArrayList<Joystick> Controllers = Robot.oi.getControllers();

			// String driveType = Robot.driveChooser.getSelected();
			String controllerType = Robot.controllerChooser.getSelected();

			if (controllerType == "Gamepad") {
				double up = -DriveSystem.scale(Controllers.get(0).getRawAxis(3), maxVel, minVel, range, 0);
				double down = DriveSystem.scale(Controllers.get(0).getRawAxis(2), maxVel, minVel, range, 0);

				if (_adjust && Robot.adjustChooser.getSelected().equals("Fine")) {
					up /= adjustAmount;
					down /= adjustAmount;
				} else if (!_adjust && !Robot.adjustChooser.getSelected().equals("Fine")) {
					up /= adjustAmount;
					down /= adjustAmount;
				}
				if (down == 0 && up != 0) {

					System.out.println("UP" + up);
					RobotMap.winch.set(up);
				} else if (up == 0 && down != 0) {
					System.out.println("DOWN" + down);
					RobotMap.winch.set(down);
				} else {
					stop();

				}
			}
		} else {
			stop();
		}
	}
}