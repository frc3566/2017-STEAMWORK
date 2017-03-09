package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.navigation.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousStrafeToSideLift extends CommandGroup {

	public AutonomousStrafeToSideLift(Direction direction) {
		// drive to base line (78.5in) //assuming 40 in away from center
	    //****Baseline is actually 7ft 9.25in away from wall!!
	    //starting horizontally 15 inches from the middle of side driver station (closer to center)
		addSequential(new DriveForDistance(Direction.FORWARD, 5.5, 0.2));

		// rotate 60 degrees to the opposite angle
		if (direction == Direction.LEFT) {
			addSequential(new Rotate(Direction.RIGHT, 1.1, 0.3));
		} else if (direction == Direction.RIGHT) {
			addSequential(new Rotate(Direction.LEFT, 1.1, 0.3));
		}

		//addSequential(new DriveForDistance(Direction.FORWARD, 1, 0.2));
		// for front, 78.5in/6.6ft. For side, 46in
				addSequential(new DriveForDistance(Direction.FORWARD, 2.2, 0.2));
				// finishes when timeout OR detects the target

				addSequential(new LiftHookPlaceGear());

				addSequential(new DriveForDistance(Direction.BACKWARD, 1, 0.3));

				addSequential(new GearHandlerLower(1, 1));
				// goes past the line for the 5 points(rotate, drive front for
				// horizontal)
				addSequential(new Rotate(direction, 1, 0.3));
				addSequential(new DriveForDistance(Direction.FORWARD, 5, 0.3));
	}
}
