// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3566.MecanumDriveJan21.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc3566.MecanumDriveJan21.OI;
import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;

/**
 *
 */
public class DriveWithJoystick extends Command {

	private RobotDrive myDrive;
	private OI oi;
	private int version;

	public DriveWithJoystick() {
		requires(Robot.mecanumDriveTrain);
		myDrive = RobotMap.mecanumDriveTrainRobotDrive;
		oi = Robot.oi;
		version = RobotMap.ROBOT_VERSION;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		myDrive.mecanumDrive_Cartesian(0.0, 0.0, 0.0, 0.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		/*
		 * Um, dudes… WTF? I'm leaving this as it is (reversing the joystick
		 * input on R2), but I note that you reversed all of the directions in
		 * the DriveTrain subsystem in R2 as well, which MIGHT account for why
		 * you need to send the joystick command in reverse as well. I'll bet
		 * this is a double-negative that could be eliminated.
		 * 
		 * -- SB 3/6
		 */
		myDrive.mecanumDrive_Cartesian(oi.driveTrainJoystick.getRawAxis(0), oi.driveTrainJoystick.getRawAxis(1) * (version == 1 ? 1 : -1),
				oi.getRotation(), 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
