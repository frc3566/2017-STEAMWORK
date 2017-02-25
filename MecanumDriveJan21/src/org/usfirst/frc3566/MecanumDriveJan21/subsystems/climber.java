package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;
import org.usfirst.frc3566.MecanumDriveJan21.commands.climb;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class climber extends Subsystem {
	//public static CANTalon climbBoy= RobotMap.climber;
	public static CANTalon climbclimb= RobotMap.climber;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	 setDefaultCommand(new climb());
    }
    
    public void climbOneWay(double speed) {
    	climbclimb.set(speed);
    	
    }
    public void climbTheOtherWay(double seped){
    	climbclimb.set(-1*seped);
    	
    }
    public void STOP()	{
    	climbclimb.set(0);
    }
}
