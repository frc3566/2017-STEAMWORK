package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.VisionValues;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Shooter extends PIDSubsystem {
    private CANTalon shooter = RobotMap.shooter;
    private Servo shooterTrigger = RobotMap.ballTrigger;
    private Encoder shooterEncoder = RobotMap.shooterEncoder;

    public Shooter() {
	// leaving this here for when it really is PID
	/*
	 * PID settings are... confusing. Think of them this way:
	 * 
	 * P (proportional) -- how much should corrections be dependent on how
	 * erroneous the current input is relative to the desired setpoint?
	 * 
	 * I (integral) -- how much should the corrections be dependent on how
	 * long the current input has been erronesous relative to the desired
	 * setpoint?
	 * 
	 * D (derivative) -- how much should the corrections be dependent on how
	 * much the current input has become erroneous, relative to the desired
	 * setpoint?
	 */
	super(1, 0.0, 0.0, 0.01);
	setAbsoluteTolerance(0.1);
	getPIDController().setContinuous(false);
	shooterEncoder.setPIDSourceType(PIDSourceType.kRate);
    }

    protected double returnPIDInput() {
	return shooterEncoder.getRate();
    }

    protected void usePIDOutput(double output) {
	shooter.pidWrite(output);
    }

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
    }

    public void engage() {
	setSetpoint(VisionValues.SHOOTER_OPTIMAL_SPEED);
	enable();
    }

    public void disengage() {
	disable();
	shooter.set(0.0);
	closeTrigger();
    }

    public void openTrigger() {
	if (isReady()) {
	    shooterTrigger.set(VisionValues.BALL_TRIGGER_OPEN);
	}
    }

    public void closeTrigger() {
	shooterTrigger.set(VisionValues.BALL_TRIGGER_CLOSED);
    }

    public boolean isReady() {
	return shooterEncoder.getRate() > VisionValues.SHOOTER_MIN_SPEED;
    }
}
