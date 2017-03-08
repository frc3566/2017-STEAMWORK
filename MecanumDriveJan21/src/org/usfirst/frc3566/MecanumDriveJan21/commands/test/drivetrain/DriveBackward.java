package org.usfirst.frc3566.MecanumDriveJan21.commands.test.drivetrain;

public class DriveBackward extends AbstractDriveTrainCommand {
	protected void execute() {
		driveTrain.driveTrainBackward(POWER);
	}
}
