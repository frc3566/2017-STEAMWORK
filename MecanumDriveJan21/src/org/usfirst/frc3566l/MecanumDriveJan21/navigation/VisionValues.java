package org.usfirst.frc3566l.MecanumDriveJan21.navigation;

public class VisionValues {

    /**
     * test this on network table and see what's the ideal baseline area for
     * differentiating actual vision targets and unwanted interfering contours
     * NOTE: this value is for basic filtering in grip, not differentiating
     * actual targets The value would be around 100-300, instead of the
     * imgProcCalculated area, which is usually 3000+ access through
     * GripPipeline for basic filtering.
     **/
    public static double GripAreaThreshold = 150.0;
    /**
     * basic filtering ImgProcArea values for both vertical and horizontal
     * targets access through FishyThread for vision targeting aid.
     */
    public static double TargetAreaThreshold = 200;
    /**
     * the ideal area of vertical targets (average) for the robot to stop and
     * deliver the gear access through the autonomous command/control class for
     * robot behavior control
     */
    public static double idealVerticalTargetArea = 13000;
    public static double idealHorizontalTargetArea = 100000000;

    /**
     * acceptable centering range for gear delivery.
     */
    public static double VISION_MAX_HORIZ_BEARING, VISION_MIN_HORIZ_BEARING, VISION_LIFT_HOOK_MIN_HEIGHT,
	    VISION_HIGH_GOAL_MIN_HEIGHT, VISION_HIGH_GOAL_MAX_HEIGHT;

    /**
     * acceptable leveling range for vision target "horizon"
     */
    public static double VISION_MAX_HORIZON_SLOPE, VISION_MIN_HORIZON_SLOPE;

    public static double GH_POT_ZERO, GH_POT_BOTTOM_TO_MIDDLE_DISTANCE, GH_POT_MIDDLE_TO_TOP_DISTANCE;

    public static double BALL_TRIGGER_CLOSED, BALL_TRIGGER_OPEN, SHOOTER_OPTIMAL_SPEED, SHOOTER_MIN_SPEED;

    public static void init(int edition) {
	/********************************************************************
	 * Default to R1 values, but override with R2 if necessary
	 *******************************************************************/

	/* Vision Values */
	VISION_MAX_HORIZ_BEARING = 640 / 2 + 20;
	VISION_MIN_HORIZ_BEARING = 640 / 2 - 20;
	VISION_LIFT_HOOK_MIN_HEIGHT = 185;
	VISION_HIGH_GOAL_MIN_HEIGHT = 480 / 2 - 20;
	VISION_HIGH_GOAL_MAX_HEIGHT = 480 / 2 + 20;
	VISION_MAX_HORIZON_SLOPE = 0.05;
	VISION_MIN_HORIZON_SLOPE = -0.05;

	/* Gear Handler values */
	GH_POT_ZERO = -1;
	GH_POT_BOTTOM_TO_MIDDLE_DISTANCE = 51;
	GH_POT_MIDDLE_TO_TOP_DISTANCE = 63;

	/* Shooter values */
	BALL_TRIGGER_CLOSED = 0.9;
	BALL_TRIGGER_OPEN = 0.5;
	SHOOTER_OPTIMAL_SPEED = 1800.0;
	SHOOTER_MIN_SPEED = SHOOTER_OPTIMAL_SPEED * 0.9;

	if (edition == 2) {

	}
    }
}
