package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FuelIn extends Command {

	
	
	public FuelIn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.fuels.ballIn();
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
    	Robot.fuels.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
