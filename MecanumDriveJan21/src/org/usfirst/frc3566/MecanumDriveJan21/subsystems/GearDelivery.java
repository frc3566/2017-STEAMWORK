package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;


import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearDelivery extends Subsystem {
	//private final CANTalon gearDelivery = RobotMap.gearDeliveryMotor3;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private final CANTalon gearDelivery = RobotMap.gearDelivery1;
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void deliver(double speed){
    	if(Robot.gearLimitSwitchFront.get()&&Robot.gearPotentiometer.get()>7)
    	gearDelivery.set(speed*-1); //42 good
    }
    
    public void retract(double speed){
    	if(Robot.gearLimitSwitchBack.get()&&Robot.gearPotentiometer.get()<19)
    	gearDelivery.set(speed);
    }
    
	public void stop() {
		// TODO Auto-generated method stub
		gearDelivery.set(0.0);
		
	}
}

