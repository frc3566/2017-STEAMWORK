// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3566.MecanumDriveJan21;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3566.MecanumDriveJan21.commands.*;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.*;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.Direction;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.VisionValues;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static FishyCam liftCam;
	public static UsbCamera elevatorCam;
	public static DigitalInput gearLimitSwitchFront, gearLimitSwitchBack;
	public static NetworkTable table;
	public static Potentiometer gearPotentiometer;
	public static BallElevator ballElevator;
	public static Shooter shooter;
	public static Command autonomous;
	public static OI oi;
	public static DriveTrain mecanumDriveTrain;
	public static SendableChooser<Command> autoChooser;
	public static GearHandler gearDelivery;
	public static Climber climber;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {

		RobotMap.init();

		mecanumDriveTrain = new DriveTrain();

		// NOTE: FishyThread includes vision processing loop. DO NOT CREATE TWO
		FishyCam camA = new FishyCam(0, FishyCam.defaultStart);

		camA.setDaemon(true);
		camA.start();
		System.out.println(camA.getId());

		// camB = CameraServer.getInstance().startAutomaticCapture(1);
		gearLimitSwitchFront = new DigitalInput(0);
		gearLimitSwitchBack = new DigitalInput(1);

		gearPotentiometer = new AnalogPotentiometer(0, 360, 0);
		VisionValues.potentiometer0 = gearPotentiometer.get();

		NetworkTable.setIPAddress("cc");
		table = NetworkTable.getTable("datatable");

		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Front Lift Hook", new AutonomousLiftFront());

		/*
		 * FIXME not clear that we need all four of these commands (strafing to
		 * side targets seems simplest? stick with that?
		 */
		autoChooser.addObject("Left-Side Lift Hook", new AutonomousLiftSide(Direction.LEFT));
		autoChooser.addObject("Right-Side Lift Hook", new AutonomousLiftSide(Direction.RIGHT));
		autoChooser.addObject("Strafe Left to Side Lift Hook", new AutonomousStrafeToSideLift(Direction.LEFT));
		autoChooser.addObject("Strafe Right to Side Lift Hook", new AutonomousStrafeToSideLift(Direction.RIGHT));
		SmartDashboard.putData("Autonomous", autoChooser);

		SmartDashboard.putData("Reset Gear", new GearHandlerLower());
		SmartDashboard.putData("Deliver Gear", new GearHandlerRaise());

		gearDelivery = new GearHandler();
		ballElevator = new BallElevator();
		climber = new Climber();
		shooter = new Shooter();
		oi = new OI();
		OI.slowDownValue = 0.5;

		/**
		 * encoder1 = new Encoder(0, 1); //parameters: A channel and B channel
		 * encoder1.setMaxPeriod(.1); encoder1.setMinRate(10);
		 * encoder1.setDistancePerPulse(5); encoder1.setReverseDirection(true);
		 * encoder1.setSamplesToAverage(7);
		 **/
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		autonomous = (Command) autoChooser.getSelected();
		System.out.println("Beginning autonomous: " + autonomous.getName());
		autonomous.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomous != null)
			autonomous.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		// SmartDashboard.putNumber("encoderDistance", encoder1.getDistance());
		SmartDashboard.putNumber("Gear Handler Potentiometer", gearPotentiometer.get());
		SmartDashboard.putBoolean("Gear Handler Front Limit", gearLimitSwitchFront.get());
		SmartDashboard.putBoolean(" Gear Handler Back Limit", gearLimitSwitchBack.get());
		// SmartDashboard.putNumber("Shoot Trigger",
		// RobotMap.ballOpener0.getPosition());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	public NetworkTable getTable() {
		return table;
	}

}
