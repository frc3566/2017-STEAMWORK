package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.DriveTrain;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.DriveTrain.Direction;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForDistance extends Command {

	// private Encoder myEncoder;
	private double myDistance;
	private double speed;
	private Direction myDirection;
	private DriveTrain drivetrain;
	private Command myEnd;

	public DriveForDistance(Direction direction, double distanceToDrive, double s) {
		myDirection = direction;
		myDistance = distanceToDrive;
		speed = s;
		drivetrain = Robot.mecanumDriveTrain;
		this.setTimeout(distanceToDrive);
	}

	public DriveForDistance(Direction direction, double distanceToDrive, double s, Command endCommand) {
		// Directions: f, b, l, r
		myDirection = direction;
		myDistance = distanceToDrive;
		speed = s;
		drivetrain = Robot.mecanumDriveTrain;
		this.setTimeout(distanceToDrive);
		myEnd = endCommand;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// myEncoder = Robot.encoder1;
		// myEncoder.reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		switch (myDirection) {
		case FORWARD:
			drivetrain.driveTrainForward(speed);
			break;
		case BACKWARD:
			drivetrain.driveTrainBackward(speed);
			break;
		case LEFT:
			drivetrain.driveTrainSidewayLeft(speed);
			break;
		case RIGHT:
			drivetrain.driveTrainSidewayRight(speed);
			break;
		default:
			System.out.println("no direction recognized");
			break;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return this.isTimedOut();
		// return (Math.abs(myDistance) >= Math.abs(myEncoder.getDistance()));
	}

	// Called once after isFinished returns true
	protected void end() {
		if (myEnd != null) {
			myEnd.start();
		}
		drivetrain.stopDriveTrain();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
