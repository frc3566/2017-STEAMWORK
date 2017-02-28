// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;

import org.usfirst.frc3566.MecanumDriveJan21.commands.*;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {

	private final CANTalon frontLeft = RobotMap.frontLeft;
	private final CANTalon rearLeft = RobotMap.rearLeft;
	private final CANTalon frontRight = RobotMap.frontRight;
	private final CANTalon rearRight = RobotMap.rearRight;
	private final RobotDrive robotDrive = RobotMap.mecanumDriveTrainRobotDrive;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
		setDefaultCommand(new DriveWithJoystick());
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	}

	public void stopDriveTrain() {
		frontLeft.set(0);
		frontRight.set(0);
		rearLeft.set(0);
		rearRight.set(0);
	}

	public void driveTrainForward(double speed) {
		robotDrive.mecanumDrive_Cartesian(0, -1 * speed, 0, 0);
	}

	public void driveTrainBackward(double speed) {
		robotDrive.mecanumDrive_Cartesian(0, speed, 0, 0);
	}

	// TODO Need Check
	public void driveTrainSidewayRight(double speed) {
		robotDrive.mecanumDrive_Cartesian(speed, 0, 0, 0);
	}

	public void driveTrainSidewayLeft(double speed) {
		robotDrive.mecanumDrive_Cartesian(speed * -1, 0, 0, 0);
	}

	// TODO test
	public void rotateLeft(double speed) {
		robotDrive.mecanumDrive_Cartesian(0, 0, speed, 0);
	}

	// TODO test
	public void rotateRight(double speed) {
		robotDrive.mecanumDrive_Cartesian(0, 0, speed * -1, 0);
	}
}
