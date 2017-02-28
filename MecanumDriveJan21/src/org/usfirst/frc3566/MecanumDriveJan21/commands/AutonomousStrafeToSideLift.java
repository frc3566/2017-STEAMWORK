package org.usfirst.frc3566.MecanumDriveJan21.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.Direction;

public class AutonomousStrafeToSideLift extends CommandGroup {

	public AutonomousStrafeToSideLift() {
		this(Direction.LEFT);
	}

	public AutonomousStrafeToSideLift(Direction direction) {
		addSequential(new DriveForDistance(direction, 2, 0.35));
		addSequential(new AutonomousLiftFront());
	
//		addSequential(new DriveForDistance(Direction.FORWARD, 3, 0.3));
//		addSequential(new LiftHookPlaceGear());
//	 	addSequential(new DriveForDistance(Direction.BACKWARD, 1, 0.2));
//    	addSequential(new GearHandlerLower());
//    	addSequential(new ResetPotentiometer());
	}
}
