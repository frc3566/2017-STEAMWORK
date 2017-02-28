package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	private CANTalon shoot = RobotMap.shooter;
	private Servo shooterTrigger = RobotMap.shooterTrigger;

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());

	}

	public void shoot() {
		shoot.set(1);

	}

	public void suck() {
		// shoot.set(-1);
	}

	public void noShoot() {
		shoot.set(0);
	}

	public void servoPositionUno() {
		shooterTrigger.set(0.3);
	}

	public void servoPositionDos() {
		shooterTrigger.set(1.3);
	}

}
