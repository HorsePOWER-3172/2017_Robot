package org.usfirst.frc.team3172.robot;

import org.usfirst.frc.team3172.robot.subsystems.DriveSystem;
import org.usfirst.frc.team3172.robot.subsystems.LiftSystem;
import org.usfirst.frc.team3172.robot.subsystems.WinchSystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	public static OI oi;
	
	public static final DriveSystem driveSystem = new DriveSystem();
	public static final WinchSystem winchSystem = new WinchSystem();
	public static final LiftSystem liftSystem = new LiftSystem();
	
	
	public static SendableChooser<String> driveChooser = new SendableChooser<>();
	public static SendableChooser<String> controllerChooser = new SendableChooser<>();
	public static SendableChooser<String> reverseChooser = new SendableChooser<>();
	public static SendableChooser<String> adjustChooser = new SendableChooser<>();
	
	
	@Override
	public void robotInit() {
		RobotMap.init();
		
		driveChooser.addDefault("(Default) Tank", "Tank");
		driveChooser.addObject("Arcade", "Arcade");
		
		controllerChooser.addDefault("(Default) Gamepad", "Gamepad");
		controllerChooser.addObject("Joysticks", "Joysticks");
		
		reverseChooser.addDefault("(Default) On", "On");
		reverseChooser.addObject("Off", "Off");
		
		adjustChooser.addDefault("(Default) Fine", "Fine");
		adjustChooser.addObject("Fast", "Fast");
		
		
		
		SmartDashboard.putData("Drive Scheme", driveChooser);
		SmartDashboard.putData("Controller Type", controllerChooser);
		SmartDashboard.putData("Reverse Button", reverseChooser);
		SmartDashboard.putData("Adjust Action", adjustChooser);
		
		oi = new OI();
		oi.genControllers(); //Unnecessary? 
		
		
	}
	
	
	@Override
	public void disabledInit() {
		
	}
	
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		
	}
	
	
	@Override
	public void autonomousInit() {
		
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	
	@Override
	public void teleopInit() {
		oi.genControllers();
		Robot.driveSystem.restart();
		Robot.liftSystem.restart();
		Robot.winchSystem.restart();
		
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	
	
}
