package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Mailbox extends Subsystem {
	private final CANTalon mailbox = RobotMap.mailbox;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void positiveMailbox(){
    	mailbox.set(0.5);
    }
    
    public void negativeMailbox(){
    	mailbox.set(-0.5);
    }
    
}

