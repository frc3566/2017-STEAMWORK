package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.VisionValues;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousLiftSide extends Command {
	
	private int leftOrRightTarget; //1 = left; 2 = right
	private boolean targetsDetected, done;
	//this boolean shows if the auto command is aware of the targets
	
    public AutonomousLiftSide(int side) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	leftOrRightTarget = side;
    	targetsDetected = false;
    	done = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//make the robot drive forward for 14 feet to be right next to the target!!!
    	Robot.mecanumDriveTrain.driveTrainForward(0.2, 5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//execute has to happen AFTER initialize is done. Check if it does.
    	if (!targetsDetected){
    		//if no targets in view yet, rotate a little bit and then check again
    		if(leftOrRightTarget == 1){
    	    	Robot.mecanumDriveTrain.rotateRight(0.1, 0);
    	    	}else if(leftOrRightTarget == 2){
    	        	Robot.mecanumDriveTrain.rotateLeft(0.1, 0);
    	    	}
    		targetsDetected = Robot.camA.checkIfTargetsDetected();
    	}else{
    		//once targets detected, center the targets
    		if(Robot.camA.checkIfXCenterInRange()){
    			/**
    			 * Gear Delivery!!
    			 */
    			done = true;
    		}else{
    			/**
    			 * if it's not in range, either xCenter is bigger than max or smaller than min
    			 */
    			if(Robot.camA.getXCenter()>VisionValues.inRangeXmaxV){
    			Robot.mecanumDriveTrain.rotateLeft(0.05, 0); 
    		}else if(Robot.camA.getXCenter()<VisionValues.inRangeXminV){
    			Robot.mecanumDriveTrain.rotateRight(0.05, 0);
    		}
    	}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
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
