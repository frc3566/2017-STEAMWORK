package org.usfirst.frc3566.MecanumDriveJan21.commands.test.drivetrain;

public class RotateRight extends AbstractDriveTrainCommand {
	protected void execute() {
		driveTrain.rotateRight(POWER);
	}
}
