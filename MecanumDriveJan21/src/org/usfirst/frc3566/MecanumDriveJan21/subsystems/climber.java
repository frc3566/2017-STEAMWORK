package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class climber extends Subsystem {
	public static CANTalon climbBoy= RobotMap.climber;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void climbOneWay() {
    	climbBoy.set(1);
    	
    }
    public void climbTheOtherWay(){
    	climbBoy.set(-1);
    	
    }
    public void STOP()	{
    	climbBoy.set(0);
    }
}
