package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.DriveTrain;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.Direction;

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
    private DriveTrain driveTrain;

    /* FIXME what units is the distanceToDrive given in? */
    public DriveForDistance(Direction direction, double distanceToDrive, double s) {
	requires(Robot.mecanumDriveTrain);
	driveTrain = Robot.mecanumDriveTrain;
	myDirection = direction;
	myDistance = distanceToDrive;
	speed = s;
	drivetrain = Robot.mecanumDriveTrain;
	this.setTimeout(distanceToDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	/*
	 * FIXME we need to get an encoder set up on this so we can actually
	 * drive a distance!
	 */
	// myEncoder = Robot.encoder1;
	// myEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	switch (myDirection) {
	    case FORWARD:
		drivetrain.forward(speed);
		break;
	    case BACKWARD:
		drivetrain.driveTrainBackward(speed);
		break;
	    case LEFT:
		drivetrain.strafeLeft(speed);
		break;
	    case RIGHT:
		drivetrain.strafeRight(speed);
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
	drivetrain.stopDriveTrain();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
