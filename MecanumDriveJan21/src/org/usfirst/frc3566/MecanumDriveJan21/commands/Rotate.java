package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.Direction;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Rotate extends Command {

	private Direction myDirection;
	
    public Rotate(Direction direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	myDirection = direction;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(myDirection.equals(Direction.LEFT)){
    		Robot.mecanumDriveTrain.rotateLeft(0.5);;
    	}else if(myDirection.equals(Direction.RIGHT)){
    		Robot.mecanumDriveTrain.rotateRight(0.5);;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mecanumDriveTrain.stopDriveTrain();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
