package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.navigation.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftHookPlaceGear extends CommandGroup {

	public LiftHookPlaceGear() {
		addSequential(new LiftHookAlign());
		addSequential(new GearHandlerRaise(1.5));
		addSequential(new Wait(0.5));
		addSequential(new DriveForDistance(Direction.FORWARD, 0.3, 0.8));
		addSequential(new GearHandlerLower(1.5));
	}
}
