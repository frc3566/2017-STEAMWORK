package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.subsystems.MecanumDriveTrain.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousLiftFront extends CommandGroup {
	public AutonomousLiftFront() {
		addSequential(new DriveForDistance(Direction.FORWARD, 5.0, 1.0));
		addSequential(new PlaceGearOnLiftHook());
	}
}
