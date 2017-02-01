package org.usfirst.frc3566.MecanumDriveJan21;

public class VisionValues {
	
    
    /**test this on network table and see what's the ideal baseline area
	for differentiating actual vision targets and unwanted interfering contours
	NOTE: this value is for basic filtering in grip, not differentiating actual targets
	The value would be around 100-300, instead of the imgProcCalculated area, which is usually 3000+
	**/
	public static double GripAreaThreshold = 150.0;
	/**
	 * basic filtering ImgProcArea values for both vertical and horizontal targets
	 */
	public static double TargetAreaThreshold = 2000;
	/**
	 * the ideal area of vertical targets (avg) for the robot to stop and deliver the gear
	 */
	public static double idealVerticalTargetArea = 3000;
	 
	
}
