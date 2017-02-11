package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.MecanumDriveTrain;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForDistance extends Command {

	private Encoder myEncoder;
	private double myDistance;
	private double speed;
	private char myDirection;
	private MecanumDriveTrain drivetrain;
	
    public DriveForDistance(char direction, double distanceToDrive, double s) {
    	//Directions: f, b, l, r
    	myDirection = direction;
    	myDistance = distanceToDrive;
    	speed = s;
    	drivetrain = Robot.mecanumDriveTrain;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	myEncoder = Robot.encoder1;
    	myEncoder.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch (myDirection){
    	case 'f': drivetrain.driveTrainForward(speed);
    	break;
    	case 'b': drivetrain.driveTrainBackward(speed);
    	break;
    	case 'l': drivetrain.driveTrainSidewayLeft(speed);
    	break;
    	case 'r': drivetrain.driveTrainSidewayRight(speed);
    	break;
    	default: System.out.println("no direction recognized");
    	break;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(myDistance) >= Math.abs(myEncoder.getDistance()));
    }

    // Called once after isFinished returns true
    protected void end() {
    	drivetrain.stopDriveTrain();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
