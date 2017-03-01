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
	mySpeed = 0.5;
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
	return (gearHandler.isAtBackLimit() || this.isTimedOut());
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
