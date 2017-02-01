package org.usfirst.frc3566.MecanumDriveJan21;

public class VisionValues {
	
    
    /**test this on network table and see what's the ideal baseline area
	for differentiating actual vision targets and unwanted interfering contours
	NOTE: this value is for basic filtering in grip, not differentiating actual targets
	The value would be around 100-300, instead of the imgProcCalculated area, which is usually 3000+
	access through GripPipeline for basic filtering.
	**/
	public static double GripAreaThreshold = 150.0;
	/**
	 * basic filtering ImgProcArea values for both vertical and horizontal targets
	 * access through FishyThread for vision targeting aid. 
	 */
	public static double TargetAreaThreshold = 2000;
	/**
	 * the ideal area of vertical targets (avg) for the robot to stop and deliver the gear
	 * acess through the autonomous command/control class for robot behavior control
	 */
	public static double idealVerticalTargetArea = 5000;
	public static double idealHorizontalTargetArea = 100000000;
	
	/**
	 * acceptable centering range for gear delivery.
	 */
	public static double inRangeXmaxV = 640/2 + 20, inRangeXminV = 640/2 - 20;
	public static double inRangeYmaxV = 480/2 + 10, inRangeYminV = 480/2 - 10;
	
}
