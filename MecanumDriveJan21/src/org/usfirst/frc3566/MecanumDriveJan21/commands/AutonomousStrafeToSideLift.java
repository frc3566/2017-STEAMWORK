package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.navigation.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousStrafeToSideLift extends CommandGroup {

	public AutonomousStrafeToSideLift(Direction direction) {
		// drive to base line (78.5in) //assuming 40 in away from center
	    //****Baseline is actually 7ft 9.25in away from wall!!
		addSequential(new DriveForDistance(Direction.FORWARD, 5, 0.2));

		// rotate 60 degrees to the opposite angle
		if (direction == Direction.LEFT) {
			addSequential(new Rotate(Direction.RIGHT, 0.85, 0.3));
		} else if (direction == Direction.RIGHT) {
			addSequential(new Rotate(Direction.LEFT, 0.85, 0.3));
		}

		//addSequential(new DriveForDistance(Direction.FORWARD, 1, 0.2));
		//addSequential(new AutonomousLiftFront());
	}
}
