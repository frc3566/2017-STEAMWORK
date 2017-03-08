package org.usfirst.frc3566.MecanumDriveJan21.commands.test.drivetrain;

public class RotateLeft extends AbstractDriveTrainCommand {
	protected void execute() {
		driveTrain.rotateLeft(POWER);
	}
}
