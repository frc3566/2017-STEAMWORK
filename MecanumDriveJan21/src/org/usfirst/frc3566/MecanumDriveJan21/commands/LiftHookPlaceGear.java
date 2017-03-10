package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.navigation.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LiftHookPlaceGear extends CommandGroup {

	public LiftHookPlaceGear(boolean Vision) {
		if(Vision){
		addSequential(new LiftHookAlign());
		}
		//addSequential(new GearMiddle());
		addSequential(new GearHandlerRaise(1));
		addSequential(new Wait(0.5));
		addSequential(new DriveForDistance(Direction.FORWARD, 0.2, 0.9));
		addSequential(new DriveForDistance(Direction.FORWARD, 0.3, 0.4));
		addSequential(new GearHandlerRaise(1));
	}
}
