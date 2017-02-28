package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;


import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearHandler extends Subsystem {
	//private final CANTalon gearDelivery = RobotMap.gearDeliveryMotor3;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private final CANTalon gearHandler = RobotMap.gearHandler;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void deliver(double speed){
    	if(Robot.gearLimitSwitchFront.get())
    	gearHandler.set(speed*-1); //42 good
    }
    
    public void retract(double speed){
    	if(Robot.gearLimitSwitchBack.get())
    	gearHandler.set(speed);
    }
    
	public void stop() {
		// TODO Auto-generated method stub
		gearHandler.set(0.0);
		
	}
}

