package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.navigation.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousLiftFront extends CommandGroup {

	public AutonomousLiftFront(Direction strafeDirection) {

		// for front, 78.5in/6.6ft. For side, 46in
		addSequential(new DriveForDistance(Direction.FORWARD, 3.5, 0.2));
		// finishes when timeout OR detects the target

		addSequential(new LiftHookPlaceGear());

		addSequential(new DriveForDistance(Direction.BACKWARD, 1, 0.3));

		// goes past the line for the 5 points(rotate, drive front for
		// horizontal)
		addSequential(new Rotate(strafeDirection, 0.87, 0.3));
		addSequential(new DriveForDistance(Direction.FORWARD, 5, 0.3));


	}
}
