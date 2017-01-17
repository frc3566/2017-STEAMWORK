package org.usfirst.frc3566.PneumaticJiwonDec7.subsystems;

import org.usfirst.frc3566.PneumaticJiwonDec7.OI;
import org.usfirst.frc3566.PneumaticJiwonDec7.RobotMap;
import org.usfirst.frc3566.PneumaticJiwonDec7.commands.*;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

public class runClimber extends Subsystem {

    private final CANTalon climbBOY = RobotMap.climber;
    public OI oi;
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	  //  setDefaultCommand(new climbNOW());
		
	    oi = new OI();
		
	}
	public void climbFastorSlow(double speed){
		climbBOY.set(oi.getYValue());
		
	}
    public void climbOK(){
    	climbBOY.set(.5);

    }

}