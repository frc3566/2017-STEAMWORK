package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.GearHandler;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.VisionValues;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearHandlerRaise extends Command {
    private GearHandler gearHandler;
    private double mySpeed;
    private double distance = -1;
    private boolean finished = false;

    public GearHandlerRaise() {
	requires(Robot.gearHandler);
	gearHandler = Robot.gearHandler;
	mySpeed = 0.5;
    }

    public GearHandlerRaise(double speed, double PotentiometerDistance) {
	mySpeed = speed;
	distance = PotentiometerDistance;
	this.setTimeout(5);
    }

    public GearHandlerRaise(double Timeout) {
	this.setTimeout(Timeout);
	mySpeed = 0.5;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	if (distance != -1) {
	    if (Math.abs(gearHandler.getPoteniometer() - VisionValues.GH_POT_ZERO - distance) > 2) {
		gearHandler.deliver(mySpeed);
	    } else {
		finished = true;
	    }
	} else {
	    Robot.gearHandler.deliver(mySpeed);
	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	// return (!Robot.gearLimitSwitchFront.get());
	return (finished || this.isTimedOut() || (!gearHandler.isAtFrontLimit()));
    }

    // Called once after isFinished returns true
    protected void end() {
	gearHandler.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
