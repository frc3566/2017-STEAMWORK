package org.usfirst.frc3566.MecanumDriveJan21.commands;


import edu.wpi.first.wpilibj.command.CommandGroup;
import navigation.Direction;

public class AutonomousLiftFront extends CommandGroup {
	public AutonomousLiftFront() {
		addSequential(new DriveForDistance(Direction.FORWARD, 2.5, 0.3));
		addSequential(new PlaceGearOnLiftHook());
	 	addSequential(new DriveForDistance(Direction.BACKWARD, 1, 0.2));
    	addSequential(new GearHandlerLower());
    	addSequential(new ResetPotentiometer());
	}
}
