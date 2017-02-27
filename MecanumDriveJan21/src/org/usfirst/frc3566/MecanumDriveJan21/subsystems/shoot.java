package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shoot extends Subsystem {
	private  CANTalon shoot;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	shoot = RobotMap.ballShooter1;
    	shoot.set(0);
    }
    public void shoot(){
    	shoot.set(1);
    	
    }
    public void suck(){
    	//shoot.set(-1);
    }
    public void noShoot(){
    	//shoot.set(0);
    }
}

