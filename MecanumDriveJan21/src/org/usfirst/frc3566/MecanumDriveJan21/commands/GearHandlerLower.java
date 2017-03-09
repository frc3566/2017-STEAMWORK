package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.GearHandler;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearHandlerLower extends Command {

	private GearHandler gearHandler;
	private double mySpeed;

	public GearHandlerLower() {
		requires(Robot.gearHandler);
		gearHandler = Robot.gearHandler;
		mySpeed = 0.7;
	}

	public GearHandlerLower(double timeout, double speed) {
		requires(Robot.gearHandler);
		gearHandler = Robot.gearHandler;
		mySpeed = speed;
		this.setTimeout(timeout);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		gearHandler.retract(mySpeed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		/*
		 * This after Granite State, this was set to run until
		 * !gearHandler.isAtBackLimit() and the GearHandler.isAtBackLimit()
		 * method had flipped the value that it was returning -- so, rather than
		 * having a double negative, I have just removed both negatives.
		 * 
		 * -- SB 3/6
		 */
		return (this.isTimedOut() || gearHandler.isAtBackLimit());
	}

	// Called once after isFinished returns true
	protected void end() {
		gearHandler.stop();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
