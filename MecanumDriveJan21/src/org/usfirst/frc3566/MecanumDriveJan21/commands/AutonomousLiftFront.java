package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566l.MecanumDriveJan21.navigation.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousLiftFront extends CommandGroup {
    public AutonomousLiftFront() {
	addSequential(new DriveForDistance(Direction.FORWARD, 2.5, 0.3));
	addSequential(new LiftHookPlaceGear());
	addSequential(new DriveForDistance(Direction.BACKWARD, 1, 0.2));
	addSequential(new GearHandlerLower());
	addSequential(new ResetPotentiometer());
    }
}
