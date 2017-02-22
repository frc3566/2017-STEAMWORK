package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class shooterTrigger extends Subsystem {
	public static Servo ballShoot = RobotMap.ballOpener0;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void servoPositionUno(){
    	ballShoot.set(0.76);
    }
    public void servoPositionDos(){
    	ballShoot.set(.9);
    }
    }

