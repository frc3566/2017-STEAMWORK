package org.usfirst.frc3566.MecanumDriveJan21.commands.test.drivetrain;

public class StrafeLeft extends AbstractDriveTrainCommand {
	protected void execute() {
		driveTrain.strafeLeft(POWER * 2.0);
	}
}
