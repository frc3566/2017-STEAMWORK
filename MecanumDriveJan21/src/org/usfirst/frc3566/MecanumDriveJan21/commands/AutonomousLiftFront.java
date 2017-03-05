package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566l.MecanumDriveJan21.navigation.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousLiftFront extends CommandGroup {
    
    public AutonomousLiftFront(Direction strafeDirection) {
	//addSequential(new DriveForDistance(Direction.FORWARD, 4, 0.5));
	
	addSequential(new DriveForDistance(Direction.FORWARD, 1.75, 0.3));
	addSequential(new LiftHookPlaceGear());
//	
	addSequential(new DriveForDistance(Direction.BACKWARD, 1.5, 0.3));
	//addSequential(new ResetPotentiometer());
//	addSequential(new DriveForDistance(strafeDirection, 5, 0.6));
//	if(strafeDirection==Direction.LEFT){
//		addSequential(new Rotate(Direction.RIGHT, 0.5));
//		}else if(strafeDirection==Direction.RIGHT){
//		addSequential(new Rotate(Direction.LEFT, 0.5));
//		}
//	addSequential(new DriveForDistance(Direction.FORWARD, 4, 0.5));
	
    }
}
