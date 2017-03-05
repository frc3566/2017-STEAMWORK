package org.usfirst.frc3566.MecanumDriveJan21.subsystems;

import org.usfirst.frc3566.MecanumDriveJan21.RobotMap;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.VisionValues;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class GearHandler extends Subsystem {

    private final CANTalon gearHandler = RobotMap.gearHandler;
    private final DigitalInput gearLimitSwitchFront = RobotMap.gearLimitSwitchFront,
	    gearLimitSwitchBack = RobotMap.gearLimitSwitchBack;
    private final Potentiometer gearHandlerPotentiometer = RobotMap.gearHandlerPotentiometer;

    public GearHandler() {
	resetPotentiometer();
    }

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
    }

    public void deliver(double speed) {
	if (gearLimitSwitchFront.get()) gearHandler.set(speed ); // 42
								     // good
    }

    public void retract(double speed) {
	if (gearLimitSwitchBack.get()) gearHandler.set(speed*-1);
    }

    public void stop() {
	gearHandler.set(0.0);
    }

    public void resetPotentiometer() {
	VisionValues.GH_POT_ZERO = gearHandlerPotentiometer.get();
    }
    
    public boolean isAtFrontLimit() {
	return gearLimitSwitchFront.get();
    }
    
    public boolean isAtBackLimit() {
	/*
	 * Hmm. Seems like this is default open. Whatevs.
	 */
	return gearLimitSwitchBack.get();
    }
    
    public double getPoteniometer() {
	return gearHandlerPotentiometer.get();
    }
}
