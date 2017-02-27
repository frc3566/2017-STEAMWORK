package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;
import org.usfirst.frc3566.MecanumDriveJan21.commands.Climb;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */

public class Climber extends Subsystem {
	public static CANTalon climber = RobotMap.climber;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Climb());
	}

	public void ascend(double speed) {
		climber.set(speed);

	}

	public void descend(double speed) {
		climber.set(-1 * speed);

	}

	public void stop() {
		climber.set(0);
	}
}
