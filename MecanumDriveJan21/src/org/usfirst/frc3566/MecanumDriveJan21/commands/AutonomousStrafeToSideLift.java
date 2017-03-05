package org.usfirst.frc3566.MecanumDriveJan21.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.Direction;

public class AutonomousStrafeToSideLift extends CommandGroup {

    public AutonomousStrafeToSideLift() {
	this(Direction.LEFT);
    }

    public AutonomousStrafeToSideLift(Direction direction) {
	
	addSequential(new DriveForDistance(Direction.FORWARD, 4, 0.5));
//	
//	addSequential(new DriveForDistance(direction, 7, 0.4));
//	if(direction==Direction.LEFT){
//	addSequential(new Rotate(Direction.RIGHT, 0.5));
//	/**NOTE: THIS IS FLIPPED **/
//	}else if(direction==Direction.RIGHT){
//	addSequential(new Rotate(Direction.LEFT, 0.5));
//	}
//	addSequential(new AutonomousLiftFront(direction));
//	
    }
}
