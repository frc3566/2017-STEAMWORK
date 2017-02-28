package org.usfirst.frc3566.MecanumDriveJan21.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import navigation.Direction;

/**
 *
 */
public class AutonomousLiftSide extends CommandGroup {

	public AutonomousLiftSide(Direction side) {
		/*
		 * make the robot drive forward for 14 feet to be right next to the
		 * target!!!
		 */
		addSequential(new DriveForDistance(Direction.FORWARD, 2, 0.2));
		switch (side) {
		case LEFT:
			addSequential(new Rotate(Direction.RIGHT));
			break;
		case RIGHT:
			addSequential(new Rotate(Direction.LEFT));
			break;
		default:
			// do nothing
		}
		addSequential(new PlaceGearOnLiftHook());
	}
}
