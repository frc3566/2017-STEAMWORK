package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.FishyCam;
import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.navigation.Direction;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForDistance extends Command {

	// private Encoder myEncoder;
	private double myDistance;
	private double speed;
	private Direction myDirection;
	private DriveTrain driveTrain;
	private boolean targeting;

	/* FIXME what units is the distanceToDrive given in? */
	public DriveForDistance(Direction direction, double distanceToDrive, double speed) {
		requires(Robot.mecanumDriveTrain);
		driveTrain = Robot.mecanumDriveTrain;
		myDirection = direction;
		myDistance = distanceToDrive;
		this.speed = speed;
		this.setTimeout(distanceToDrive);
	}

	public DriveForDistance(Direction direction, double distanceToDrive, double speed, boolean isTargeting) {
		this(direction, distanceToDrive, speed);
		targeting = isTargeting;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		/*
		 * FIXME we need to get an encoder set up on this so we can actually
		 * drive a distance!
		 */
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (myDirection) {
		case FORWARD:
			driveTrain.forward(speed);
			break;
		case BACKWARD:
			driveTrain.driveTrainBackward(speed);
			break;
		case LEFT:
			driveTrain.strafeLeft(speed);
			break;
		case RIGHT:
			driveTrain.strafeRight(speed);
			break;
		default:
			System.out.println("no direction recognized");
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (targeting) {
			return (this.isTimedOut() || FishyCam.isTargetsDetected());
		} else {
			return (this.isTimedOut());
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		driveTrain.stopDriveTrain();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
