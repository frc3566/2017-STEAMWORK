package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.GearHandler;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ResetPotentiometer extends Command {

    private GearHandler gearHandler;
	
    public ResetPotentiometer() {
	requires(Robot.gearHandler);
	gearHandler = Robot.gearHandler;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
	gearHandler.resetPotentiometer();
    	this.setTimeout(0.1);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
