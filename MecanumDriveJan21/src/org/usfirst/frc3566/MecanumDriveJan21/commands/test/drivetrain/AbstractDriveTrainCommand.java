package org.usfirst.frc3566.MecanumDriveJan21.commands.test.drivetrain;

import org.usfirst.frc3566.MecanumDriveJan21.Robot;
import org.usfirst.frc3566.MecanumDriveJan21.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public abstract class AbstractDriveTrainCommand extends Command {

	protected static final double POWER = 0.2;
	
	protected DriveTrain driveTrain;
	
	public AbstractDriveTrainCommand() {
		requires(Robot.mecanumDriveTrain);
		driveTrain = Robot.mecanumDriveTrain;
	}

	protected void initialize() {
		System.out.println(this.getName());
	}
	
	protected void interrupted() {
		end();
	}
	
	protected void end() {
		driveTrain.stopDriveTrain();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
}
