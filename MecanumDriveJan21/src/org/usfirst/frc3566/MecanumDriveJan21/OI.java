// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3566.MecanumDriveJan21;

import org.usfirst.frc3566.MecanumDriveJan21.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc3566.MecanumDriveJan21.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick driveTrainJoystick;
    public Joystick miscellaneousJoystick;
    public static double slowDownValue;
    public static JoystickButton hopeMailMoves;
    public static JoystickButton hopeMailMovesOpp;
    public static JoystickButton gearPositive;
    public static JoystickButton gearNegative;
    public static JoystickButton ballIn;
    public static JoystickButton ballOut;
    public static JoystickButton climbUP;
    public static JoystickButton climbDOWN;
    public static JoystickButton shoot, suck;
    public static JoystickButton trigger1, trigger2, trigger3;
    public static JoystickButton sidewayL, sidewayR;
    
    // END AUTOGENERATED CODE, SOURCE=ROBOT0BUILDER ID=DECLARATIONS

    @SuppressWarnings("deprecation")
	public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    	driveTrainJoystick = new Joystick(0);
        miscellaneousJoystick = new Joystick(1);
        
    //    hopeMailMoves = new JoystickButton(joystick1, 1);
    //    hopeMailMoves.whileHeld(new moveMailBox(true));

        sidewayL = new JoystickButton(driveTrainJoystick, 5);
        sidewayL.whileHeld(new Sideway('l'));
        
        sidewayR = new JoystickButton(driveTrainJoystick, 6);
        sidewayR.whileHeld(new Sideway('r'));
        
        gearPositive = new JoystickButton(miscellaneousJoystick, 3);
        gearPositive.whileHeld(new moveGearDeliveryPositive());
        gearPositive.whenReleased(new stopGear());
        
        gearNegative = new JoystickButton(miscellaneousJoystick, 4);
        gearNegative.whileHeld(new moveGearNegative());
        gearNegative.whenReleased(new stopGear());
        
        ballIn = new JoystickButton(miscellaneousJoystick, 1);
        ballIn.whileHeld(new FuelIn());
        
        shoot = new JoystickButton(miscellaneousJoystick, 8);
        shoot.whileHeld(new shoots());
        
        suck = new JoystickButton(miscellaneousJoystick, 7);
        suck.whileHeld(new suck());
        
        ballOut = new JoystickButton(miscellaneousJoystick, 2);
        ballOut.whileHeld(new FuelOut());
        
        climbUP= new JoystickButton(miscellaneousJoystick, 5);
        climbUP.whileHeld(new climbPositive());
        
        climbDOWN = new JoystickButton (miscellaneousJoystick, 6);
        climbDOWN.whileHeld(new climbNegative());
        
        trigger1 = new JoystickButton(miscellaneousJoystick, 9);
        trigger1.whileHeld(new ballTrigger());
        

        trigger2 = new JoystickButton(miscellaneousJoystick, 10);
        trigger2.whileHeld(new ballTriggerPos2());
        

      //  hopeMailMovesOpp = new JoystickButton(joystick1, 2);
       //// hopeMailMovesOpp.whileHeld(new moveMailBox(false));
        
        
        
        
        // SmartDashboard Buttons
        SmartDashboard.putData("AutoLiftFront", new AutonomousLiftFront());
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getJoystick1() {
        return driveTrainJoystick;
    }

    public double getStraight(){
    	//on large robot logitech dual action, this axis is the y axis, axis 1
    	return driveTrainJoystick.getRawAxis(1) * -1;
    }
    
    public double getSideWays(){
    	return driveTrainJoystick.getRawAxis(0) * -1;
    }
    
    public double getRotation(){
    	return driveTrainJoystick.getRawAxis(2) * slowDownValue;
    }
    
    
    
    
    
    
}

