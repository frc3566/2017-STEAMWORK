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

import com.ctre.CANTalon;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static CANTalon mecanumDriveTrainTalonSRX6; //frontright
    public static CANTalon mecanumDriveTrainTalonSRX2; //rearright
    public static CANTalon mecanumDriveTrainTalonSRX7; //frontleft
    public static CANTalon mecanumDriveTrainTalonSRX5; //rearleft
    public static CANTalon gearDeliveryMotor4;
    public static CANTalon fuelPickerUpper8;
    public static CANTalon ballShooter1;
    public static CANTalon mailbox; 
    public static RobotDrive mecanumDriveTrainRobotDrive;
    
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static void init() {
    	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        mecanumDriveTrainTalonSRX6 = new CANTalon(6); //r1 is 6 r2 is 1
        LiveWindow.addActuator("MecanumDriveTrain", "FrontRight 6",  mecanumDriveTrainTalonSRX6);
        mecanumDriveTrainTalonSRX6.setInverted(true);
        
        mecanumDriveTrainTalonSRX2 = new CANTalon(2); //r1 is 2 r2 is 0
        LiveWindow.addActuator("MecanumDriveTrain", "RearRight 2", mecanumDriveTrainTalonSRX2);
        mecanumDriveTrainTalonSRX2.setInverted(true);
        
        mecanumDriveTrainTalonSRX7 = new CANTalon(7); //r1 is 7 r2 is 3
        LiveWindow.addActuator("MecanumDriveTrain", "FrontLeft 7", mecanumDriveTrainTalonSRX7);
        mecanumDriveTrainTalonSRX7.setInverted(false);
        
        mecanumDriveTrainTalonSRX5 = new CANTalon(5);  // r 1 is 5 r2 is 2 
        LiveWindow.addActuator("MecanumDriveTrain", "RearLeft 5", mecanumDriveTrainTalonSRX5);
        mecanumDriveTrainTalonSRX5.setInverted(false);
        
        mecanumDriveTrainRobotDrive = new RobotDrive(mecanumDriveTrainTalonSRX7, mecanumDriveTrainTalonSRX5,
              mecanumDriveTrainTalonSRX6, mecanumDriveTrainTalonSRX2);
        
       // gearDeliveryMotor4 = new CANTalon(4);
        
       // fuelPickerUpper8 = new CANTalon(8);
        
       // ballShooter1 = new CANTalon(1);
        
       // mailbox = new CANTalon(9);
        
        mecanumDriveTrainRobotDrive.setSafetyEnabled(true);
        mecanumDriveTrainRobotDrive.setExpiration(0.1);
        mecanumDriveTrainRobotDrive.setSensitivity(0.5);
        mecanumDriveTrainRobotDrive.setMaxOutput(1.0);

       
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
