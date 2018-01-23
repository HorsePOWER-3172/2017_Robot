package org.usfirst.frc.team3172.robot;

import java.util.ArrayList;

import org.usfirst.frc.team3172.robot.commands.Adjust;
import org.usfirst.frc.team3172.robot.commands.EnableWinch;
import org.usfirst.frc.team3172.robot.commands.Reverse;
import org.usfirst.frc.team3172.robot.commands.Shaky;
import org.usfirst.frc.team3172.robot.commands.Unalign;
import org.usfirst.frc.team3172.robot.commands.eStop;
import org.usfirst.frc.team3172.robot.commands.restart;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public Joystick controller;
	public Joystick controller2;

	public JoystickButton unalign;
	public JoystickButton adjust;
	public JoystickButton reverse;
	public JoystickButton winchEnable;
	public JoystickButton shaky;
	public JoystickButton eStop;
	public JoystickButton restart;

	ArrayList<Joystick> controllers;

	public OI() {
		
		genControllers();
		
	}

	public void genControllers() {
		controllers = new ArrayList<Joystick>();
		controller = new Joystick(0);
		controllers.add(controller);

		if (Robot.controllerChooser.getSelected().equals("Joysticks")) {
			controller2 = new Joystick(1);
			controllers.add(controller2);
		}
		unalign = new JoystickButton(controller, 5);
		unalign.whenPressed(new Unalign());
		unalign.whenReleased(new Unalign());
		
		adjust = new JoystickButton(controller, 6);
		adjust.whenPressed(new Adjust());
		adjust.whenReleased(new Adjust());

		reverse = new JoystickButton(controller, 1);
		reverse.whenPressed(new Reverse());
		
		shaky = new JoystickButton(controller, 2);
		shaky.whenPressed(new Shaky());

		winchEnable = new JoystickButton(controller, 3);
		winchEnable.whenPressed(new EnableWinch());
		
		eStop = new JoystickButton(controller, 7);
		eStop.whenPressed(new eStop());
		
		restart = new JoystickButton(controller, 8);
		restart.whenPressed(new restart());
	}

	public ArrayList<Joystick> getControllers() {
		return controllers;
	}
}
