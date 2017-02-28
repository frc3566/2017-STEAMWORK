package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ShooterTrigger extends Subsystem {
	public static Servo ballShoot = RobotMap.servo;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    
    public void servoPositionUno(){
    	ballShoot.set(0.3);
    }
    public void servoPositionDos(){
    	ballShoot.set(1.3);
    }
    }

