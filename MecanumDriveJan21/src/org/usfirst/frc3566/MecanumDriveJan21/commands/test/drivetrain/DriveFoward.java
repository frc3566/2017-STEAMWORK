package org.usfirst.frc3566.MecanumDriveJan21.commands.test.drivetrain;

public class DriveFoward extends AbstractDriveTrainCommand {
	protected void execute() {
		driveTrain.forward(POWER);
	}
}
