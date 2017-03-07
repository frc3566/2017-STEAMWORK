package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.navigation.Direction;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rotate extends Command {

	private Direction myDirection;
	private DriveTrain driveTrain;
	private double mySpeed;

	public Rotate(Direction direction, double speed) {
		requires(Robot.mecanumDriveTrain);
		driveTrain = Robot.mecanumDriveTrain;
		myDirection = direction;
		mySpeed=speed;
	}

	public Rotate(Direction direction, double Timeout, double speed) {
		this(direction, speed);
		this.setTimeout(Timeout);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (myDirection.equals(Direction.LEFT)) {
			driveTrain.rotateLeft(mySpeed);
		} else if (myDirection.equals(Direction.RIGHT)) {
			driveTrain.rotateRight(mySpeed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return this.isTimedOut();
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
