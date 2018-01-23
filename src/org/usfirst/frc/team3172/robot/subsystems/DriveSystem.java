package org.usfirst.frc.team3172.robot.subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team3172.robot.Robot;
import org.usfirst.frc.team3172.robot.RobotMap;
import org.usfirst.frc.team3172.robot.commands.DriveCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSystem extends Subsystem {

	static boolean _eStop = false;

	static boolean leftStopped = true;
	static boolean rightStopped = true;

	static boolean _unalign = false;
	static boolean _adjust = false;
	static boolean _reverse = false;
	
	public double eTime = 0;
	
	static double adjustAmount = 2;

	static double left = 0;
	static double right = 0;

	static double maxVel = .6;
	static double minVel = -.6;
	static double range = 2;
	static double deadZone = .16;
	static double equalZone = .2;

	public void initDefaultCommand() {
		setDefaultCommand(new DriveCommand());
	}

	public static double scale(double vel, double maxVel, double minVel, double range, double deadZone) {
		double nVel = (vel / range) * (maxVel - minVel);

		if (_unalign) {
			return nVel;
		}

		if ((nVel > deadZone || nVel < (-deadZone))) {
			if (nVel < maxVel - deadZone) {
				if (nVel > (minVel + deadZone)) {
					return nVel;
				} else {
					if (!_unalign) { // remove if jumping to max or something
										// like that.
						return minVel;
					}
					return nVel;
				}
			} else {
				if (!_unalign) {
					return maxVel;
				} else {
					return nVel;
				}
			}
		} else {
			return 0;
		}
	}

	public static double link(double vel1, double vel2, double equalZone) {

		if (Math.abs(Math.abs(vel2) - Math.abs(vel1)) <= equalZone) {
			if (vel1 < 0) {
				return -(Math.abs(vel2) + Math.abs(vel1)) / 2;
			}
			return (Math.abs(vel2) + Math.abs(vel1)) / 2;
		}

		return 0;
	}

	public static void toVel(double x, double y) {
		double v = (100 - Math.abs(x)) * (y / 100) + y;
		double w = (100 - Math.abs(y)) * (x / 100) + x;
		left = (v + w) / 2;
		right = (v - w) / 2;

		left = scale(left, maxVel, minVel, range, deadZone);
		right = scale(right, maxVel, minVel, range, deadZone);
		if (!_unalign) {
			double tLeft = left;
			double tRight = right;
			if (link(left, right, equalZone) != 0) {
				tLeft = link(left, right, equalZone);
			}
			if (link(right, left, equalZone) != 0) {
				tRight = link(right, left, equalZone);
			}
			left = tLeft;
			right = tRight;
		}

	}

	public void unalign() {
		_unalign = !_unalign;
		System.out.println(_unalign);
	}

	public void adjust() {
		_adjust = !_adjust;
	}

	public void reverse() {
		if (Robot.reverseChooser.getSelected() == "On") {
			_reverse = !_reverse;
			System.out.println(_reverse);
		} else {
			_reverse = false; // In disabled while driving and controls are
								// still reversed, do not change until button is
								// pressed.
		}
	}

	public void stop() {

		RobotMap.driveSystemLeft.set(0);
		RobotMap.driveSystemRight.set(0);
		leftStopped = true;
		rightStopped = true;
	}

	public void eStop() {
		_eStop = true;
		stop();

	}

	public void restart() {
		_eStop = false;

	}

	public void drive() {
		if (_reverse) {
			if (System.currentTimeMillis() <= eTime + 150) {
				Robot.oi.getControllers().get(0).setRumble(Joystick.RumbleType.kRightRumble, 1);
			} else {
				Robot.oi.getControllers().get(0).setRumble(Joystick.RumbleType.kRightRumble, 0);
			}
		} else {
			if (System.currentTimeMillis() <= eTime + 150) {
				Robot.oi.getControllers().get(0).setRumble(Joystick.RumbleType.kLeftRumble, 1);
			} else {
				Robot.oi.getControllers().get(0).setRumble(Joystick.RumbleType.kLeftRumble, 0);
			}
		}
		
		if (!_eStop) {
			ArrayList<Joystick> Controllers = Robot.oi.getControllers();

			String driveType = Robot.driveChooser.getSelected();
			String controllerType = Robot.controllerChooser.getSelected();

			if (driveType == "Arcade" && controllerType == "Joysticks") {
				double x1 = Controllers.get(0).getRawAxis(0);
				double y1 = Controllers.get(0).getRawAxis(1);

				toVel(x1, y1);

			}

			if (driveType == "Tank" && controllerType == "Joysticks") {
				double y1 = Controllers.get(0).getRawAxis(1) * -1;
				double y2 = Controllers.get(1).getRawAxis(1) * -1;
				left = scale(y1, maxVel, minVel, range, deadZone);
				right = scale(y2, maxVel, minVel, range, deadZone);

				double lVel = link(right, left, equalZone);

				if (lVel != 0) {
					left = lVel;
					right = lVel;
				}

			}
			if (driveType == "Arcade" && controllerType == "Gamepad") {
				double x1 = Controllers.get(0).getRawAxis(4);
				double y1 = -Controllers.get(0).getRawAxis(5);

				toVel(x1, y1);

			}
			if (driveType == "Tank" && controllerType == "Gamepad") {
				double y1 = Controllers.get(0).getRawAxis(1) * -1;
				double y2 = Controllers.get(0).getRawAxis(5) * -1;
				left = scale(y1, maxVel, minVel, range, deadZone);
				right = scale(y2, maxVel, minVel, range, deadZone);
				if (!_unalign) {
					double tLeft = left;
					double tRight = right;
					if (link(left, right, equalZone) != 0) {
						tLeft = link(left, right, equalZone);
					}
					if (link(right, left, equalZone) != 0) {
						tRight = link(right, left, equalZone);
					}
					left = tLeft;
					right = tRight;
				}

			}

			right *= -1; // Right motor is backwards

			if (_reverse) {
				left *= -1;
				right *= -1;
			}

			if (_adjust && Robot.adjustChooser.getSelected().equals("Fine")) {
				left /= adjustAmount;
				right /= adjustAmount;
			} else if (!_adjust && !Robot.adjustChooser.getSelected().equals("Fine")) {
				left /= adjustAmount;
				right /= adjustAmount;
			}

			if (left == 0) {
				if (!leftStopped) {
					leftStopped = true;
					RobotMap.driveSystemLeft.set(0);
				}
			} else {
				leftStopped = false;
				RobotMap.driveSystemLeft.set(left);
			}
			if (right == 0) {
				if (!rightStopped) {
					rightStopped = true;
					RobotMap.driveSystemRight.set(0);
				}
			} else {
				rightStopped = false;
				RobotMap.driveSystemRight.set(right);
			}
		} else {
			stop();
		}
	}
}
