package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.BallElevator;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FuelOut extends Command {

    private BallElevator ballElevator;
    
    public FuelOut() {
	requires(Robot.ballElevator);
	ballElevator = Robot.ballElevator;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	ballElevator.ballOut();
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
    	ballElevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
