package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.subsystems.MecanumDriveTrain.Direction;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FrontAndBack extends Command {

	double forwardTime;
	
    public FrontAndBack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	forwardTime = 0.8;
    	this.setTimeout(forwardTime);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	new DriveForDistance(Direction.FORWARD, forwardTime, 0.1).start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	new DriveForDistance(Direction.BACKWARD, forwardTime+0.2, 0.1).start();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
