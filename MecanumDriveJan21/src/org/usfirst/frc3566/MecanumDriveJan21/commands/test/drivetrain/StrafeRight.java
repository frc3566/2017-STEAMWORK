package org.usfirst.frc3566.MecanumDriveJan21.commands.test.drivetrain;

public class StrafeRight extends AbstractDriveTrainCommand {
	protected void execute() {
		driveTrain.strafeRight(POWER * 2.0);
	}
}
