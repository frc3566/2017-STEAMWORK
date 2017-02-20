package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class moveGearDeliveryPositive extends Command {

	Command endCommand; 
	double mySpeed;
	
    public moveGearDeliveryPositive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	mySpeed=0.5;
    }

    public moveGearDeliveryPositive(double Timeout, double speed, Command end){
    	this.setTimeout(Timeout);
    	endCommand = end;
    	mySpeed = speed;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.GearDelivery.deliver(mySpeed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       // return (!Robot.gearLimitSwitchFront.get());
    	return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(endCommand!=null){
    		Timer.delay(1);
    		endCommand.start();
    	}
    	Robot.GearDelivery.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
