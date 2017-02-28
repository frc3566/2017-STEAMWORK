package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearHandlerLower extends Command {

	Command endCommand;
	double mySpeed;
	
    public GearHandlerLower() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	mySpeed = 0.5;
    }

    public GearHandlerLower(double speed, Command end){
    	endCommand = end;
    	mySpeed = speed;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	Robot.gearDelivery.retract(mySpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (!Robot.gearLimitSwitchBack.get() || this.isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(endCommand!=null){
    		endCommand.start();
    	}
    	Robot.gearDelivery.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}