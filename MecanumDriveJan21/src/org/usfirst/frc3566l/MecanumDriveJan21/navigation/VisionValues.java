package org.usfirst.frc3566l.MecanumDriveJan21.navigation;

public class VisionValues {

    /************************************************************************
     ************************************************************************
     * 
     * Declare CONSTANTS here (as variables, sigh) and then INITIALIZE them in
     * the init() method -- this allows us to maintain active code for two robot
     * editions simultaneously!
     * 
     ************************************************************************
     ***********************************************************************/

    /*
     * FIXME some day these will all be private variables with accessor methods
     */

    public static double VISION_MIN_AREA, VISION_MAX_HORIZ_BEARING, VISION_MIN_HORIZ_BEARING,
	    VISION_LIFT_HOOK_MIN_HEIGHT, VISION_HIGH_GOAL_MIN_HEIGHT, VISION_HIGH_GOAL_MAX_HEIGHT,
	    VISION_MAX_HORIZON_SLOPE, VISION_MIN_HORIZON_SLOPE;

    public static double GH_POT_ZERO, GH_POT_BOTTOM_TO_MIDDLE_DISTANCE, GH_POT_MIDDLE_TO_TOP_DISTANCE;

    public static double BALL_TRIGGER_CLOSED, BALL_TRIGGER_OPEN, SHOOTER_OPTIMAL_SPEED, SHOOTER_MIN_SPEED;

    public static void init(int edition) {
	/*
	 * Default to R1 values, but override with R2 if necessary
	 */

	/********************************************************************
	 * Vision Values
	 *******************************************************************/
	VISION_MIN_AREA = 200; /*
			        * basic filtering ImgProcArea values for both
			        * vertical and horizontal targets access through
			        * FishyThread for vision targeting aid.
			        */

	/*
	 * acceptable centering range for gear delivery.
	 */
	VISION_MAX_HORIZ_BEARING = 640 / 2 + 20;
	VISION_MIN_HORIZ_BEARING = 640 / 2 - 20;
	VISION_LIFT_HOOK_MIN_HEIGHT = 185;
	VISION_HIGH_GOAL_MIN_HEIGHT = 480 / 2 - 20;
	VISION_HIGH_GOAL_MAX_HEIGHT = 480 / 2 + 20;
	/*
	 * acceptable leveling range for vision target "horizon"
	 */
	VISION_MAX_HORIZON_SLOPE = 0.05;
	VISION_MIN_HORIZON_SLOPE = -0.05;

	/********************************************************************
	 * Gear Handler Values
	 *******************************************************************/
	GH_POT_ZERO = -1;
	GH_POT_BOTTOM_TO_MIDDLE_DISTANCE = 51;
	GH_POT_MIDDLE_TO_TOP_DISTANCE = 63;

	/********************************************************************
	 * Shooter Values
	 *******************************************************************/
	/*
	 * Servo positions
	 */
	BALL_TRIGGER_CLOSED = 0.9;
	BALL_TRIGGER_OPEN = 0.5;
	SHOOTER_OPTIMAL_SPEED = 1800.0; /*
					 * Speed necessary to consistently
					 * launcht the ball into the high goal
					 */
	SHOOTER_MIN_SPEED = SHOOTER_OPTIMAL_SPEED
		* 0.9; /*
		        * Speed necessary to allow the trigger to open and drop
		        * balls into the shooter
		        */

	/* update values for R2 as needed here */
	if (edition == 2) {
	    // right now... we know nothing!
	}
    }
}
