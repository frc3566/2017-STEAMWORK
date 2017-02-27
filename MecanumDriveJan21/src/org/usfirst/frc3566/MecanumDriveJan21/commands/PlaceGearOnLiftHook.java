package org.usfirst.frc3566.MecanumDriveJan21.commands;

import org.usfirst.frc3566.MecanumDriveJan21.VisionValues;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.MecanumDriveTrain.Direction;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGearOnLiftHook extends CommandGroup {

    public PlaceGearOnLiftHook() {
    	//Potentiometer Deliver 19
    	//Potentiometer Middle 82
    	
        // Add Commands here:
    	addSequential(new AlignToLiftHook());
    	addSequential(new moveGearNegative());
    	addSequential(new ResetPotentiometer());
    	addSequential(new moveGearDeliveryPositive(0.4, VisionValues.PotentiometerBottomToMiddleDistance));
    	addSequential(new DriveForDistance(Direction.FORWARD, 0.8, 0.2));
    	addSequential(new ResetPotentiometer());
    	addSequential(new moveGearDeliveryPositive(0.4, VisionValues.PotentiometerMiddleToTopDistance));
    	addSequential(new DriveForDistance(Direction.BACKWARD, 1, 0.2));
    	addSequential(new moveGearNegative());
    	addSequential(new ResetPotentiometer());
    	
    	/*move gear delivery positive sometimes does not go all the way up, but commond will go all the way through
    	
    	*/
    	//addSequential(new FrontAndBack());
    	
    	
    	
    	
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
