package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;


import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearDelivery extends Subsystem {
	private final CANTalon gearDelivery = RobotMap.gearDeliveryMotor3;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void pleaseMoveGearPositive(){
    	gearDelivery.set(0.5);
    	
    }
    public void pleaseMoveGearNegative(){
    	gearDelivery.set(-0.5);
    }
	public void stopPlease() {
		// TODO Auto-generated method stub
		gearDelivery.set(0.0);
		
	}
}

