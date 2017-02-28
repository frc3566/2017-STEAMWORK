package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.FishyCam;
import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.DriveTrain.Direction;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousLiftSide extends Command {

	private Direction leftOrRightTarget; // 1 = left; 2 = right
	private boolean ready, done;
	// this boolean shows if the auto command is aware of the targets

	public AutonomousLiftSide(Direction side) {
		leftOrRightTarget = side;
		ready = false;
		done = false;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		// make the robot drive forward for 14 feet to be right next to the
		// target!!!
		new DriveForDistance(Direction.FORWARD, 2, 0.2).start(); // direction,
																	// distance,
																	// speed
		Timer.delay(2);
		ready = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// execute has to happen AFTER initialize is done. Check if it does.
		if (ready) {
			/*
			 * if no targets in view yet, rotate a little bit and then check
			 * again
			 */
			if (!FishyCam.isTargetsDetected()) {
				if (leftOrRightTarget == Direction.LEFT) {
					Robot.mecanumDriveTrain.rotateRight(0.4);
				} else if (leftOrRightTarget == Direction.RIGHT) {
					Robot.mecanumDriveTrain.rotateLeft(0.4);
				}
			} else {
				// get lined up and deliver that gear!
				new AutonomousLiftFront().start();
			}
		} else {
			// do nothing (wait!)
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
