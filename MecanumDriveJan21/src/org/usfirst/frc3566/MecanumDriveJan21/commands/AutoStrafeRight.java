package org.usfirst.frc3566.MecanumDriveJan21.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3566.MecanumDriveJan21.*;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.MecanumDriveTrain.Direction;

/**
 *
 */
public class AutoStrafeRight extends Command {

	public AutoStrafeRight() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		new DriveForDistance(Direction.LEFT, 2, 0.35, new AutonomousLiftFront()).start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.mecanumDriveTrain.stopDriveTrain();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
