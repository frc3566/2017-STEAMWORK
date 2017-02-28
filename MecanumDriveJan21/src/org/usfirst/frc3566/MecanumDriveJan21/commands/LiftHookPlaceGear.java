package org.usfirst.frc3566.MecanumDriveJan21.commands;


import org.usfirst.frc3566l.MecanumDriveJan21.navigation.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LiftHookPlaceGear extends CommandGroup {

    public LiftHookPlaceGear() {
    	//Potentiometer Deliver 19
    	//Potentiometer Middle 82
    	
    	addSequential(new LiftHookAlign());
    	
   // 	addSequential(new GearHandlerLower());
    //	addSequential(new ResetPotentiometer());
    	
    	addSequential(new GearHandlerRaise(1.5));
    //	addSequential(new GearHandlerRaise(0.4, VisionValues.PotentiometerBottomToMiddleDistance));
    
    	addSequential(new DriveForDistance(Direction.FORWARD, 0.8, 0.2));
    //	addSequential(new ResetPotentiometer());
    	
    //	addSequential(new GearHandlerRaise(0.4, VisionValues.PotentiometerMiddleToTopDistance));
    	addSequential(new GearHandlerRaise(0.5));

    }
}
