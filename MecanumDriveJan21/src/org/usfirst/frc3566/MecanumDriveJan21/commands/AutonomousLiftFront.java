// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3566.MecanumDriveJan21.commands;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc3566.MecanumDriveJan21.FishyThread;
import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.VisionValues;

/**
 *
 */
public class AutonomousLiftFront extends Command {
	//this is the autonomous command for when there is a lift in front of the robot
	public AutonomousLiftFront(){
		
	}

    public boolean checkArea(){
   //checks if the avgArea of the detected targets meets the target area requirements of the Lift 
    	if(FishyThread.getArea() > VisionValues.idealVerticalTargetArea){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    protected void initialize() {
    	
    }

    protected void execute() {
    	//delay is 0 in the method below because this is called in the execute loop. 
    	//Since the motor is already receiving constant commands, no extra delay is needed
    	Robot.mecanumDriveTrain.driveTrainForward(0.2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//when detected target area is big enough, calls the command to stop
        return checkArea();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mecanumDriveTrain.stopDriveTrain();
    	Robot.GearDelivery.deliver();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.mecanumDriveTrain.stopDriveTrain();
    }
}
