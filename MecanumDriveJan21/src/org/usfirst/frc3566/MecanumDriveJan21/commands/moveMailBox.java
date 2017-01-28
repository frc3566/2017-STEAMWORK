package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class moveMailBox extends Command {

	private boolean myDirection;
	
    public moveMailBox(boolean sign) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.mailbox);
    	myDirection = sign;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(myDirection){
    		Robot.mailbox.positiveMailbox();
    	}else if(!myDirection){
    		Robot.mailbox.negativeMailbox();
    	}
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
