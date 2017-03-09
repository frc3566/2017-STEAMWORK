package org.usfirst.frc3566.MecanumDriveJan21.navigation;

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

	public static double VISION_MIN_AREA, VISION_LIFTHOOK_CENTER_X, VISION_LIFTHOOK_CENTER_X_ERROR,
			VISION_LIFTHOOK_CENTER_Y, VISION_LIFTHOOK_CENTER_Y_ERROR, VISION_HIGHGOAL_CENTER_X,
			VISION_LIFTHOOK_HORIZON_SLOPE, VISION_HIGHGOAL_CENTER_X_ERROR, VISION_HIGHGOAL_CENTER_Y,
			VISION_HIGHGOAL_CENTER_Y_ERROR;

	public static double GH_POT_ZERO, GH_POT_BOTTOM_TO_MIDDLE_DISTANCE, GH_POT_MIDDLE_TO_TOP_DISTANCE;

	public static double BALL_TRIGGER_CLOSED, BALL_TRIGGER_OPEN, SHOOTER_OPTIMAL_SPEED, SHOOTER_MIN_SPEED;

	public static void init(int edition) {
		/*
		 * Default to R1 values, but override with R2 if necessary
		 */

		/********************************************************************
		 * Vision Values
		 *******************************************************************/
		/*
		 * basic filtering ImgProcArea values for both vertical and horizontal
		 * targets access through FishyThread for vision targeting aid.
		 */VISION_MIN_AREA = 200;

		/*
		 * acceptable centering range for gear delivery.
		 */
		VISION_LIFTHOOK_CENTER_X = 320;
		VISION_LIFTHOOK_CENTER_Y = 10;
		VISION_LIFTHOOK_CENTER_X_ERROR = 10;
		VISION_LIFTHOOK_CENTER_Y_ERROR = VISION_LIFTHOOK_CENTER_X_ERROR;

		/*
		 * acceptable +/- error for vision target "horizon"
		 */
		VISION_LIFTHOOK_HORIZON_SLOPE = 0.05;

		/*
		 * acceptable centering range for high goal
		 */
		VISION_HIGHGOAL_CENTER_X = VISION_LIFTHOOK_CENTER_X;
		VISION_HIGHGOAL_CENTER_Y = 480 / 2;
		VISION_HIGHGOAL_CENTER_X_ERROR = VISION_LIFTHOOK_CENTER_X_ERROR;
		VISION_HIGHGOAL_CENTER_Y_ERROR = VISION_LIFTHOOK_CENTER_Y_ERROR;

		/********************************************************************
		 * Gear Handler Values
		 *******************************************************************/
		GH_POT_ZERO = -1;
		GH_POT_BOTTOM_TO_MIDDLE_DISTANCE = 51;
		GH_POT_MIDDLE_TO_TOP_DISTANCE = 63;
		// gear value when at top 0.36178053841190183
		// gear value when at bottom 104.52675755101072

		// 70 degrees is good value to shoot

		/********************************************************************
		 * Shooter Values
		 *******************************************************************/
		/*
		 * Servo positions
		 */
		BALL_TRIGGER_CLOSED = 0.9;
		BALL_TRIGGER_OPEN = 0.5;

		/*
		 * Speed necessary to consistently launch the ball into the high goal
		 */
		SHOOTER_OPTIMAL_SPEED = 550.0;

		/*
		 * Speed necessary to allow the trigger to open and drop balls into the
		 * shooter
		 */
		SHOOTER_MIN_SPEED = SHOOTER_OPTIMAL_SPEED * 0.9;

		/* update values for R2 as needed here */
		if (edition == 2) {
			/*
			 * Vision Values
			 */
			VISION_LIFTHOOK_CENTER_X = 350;
			VISION_LIFTHOOK_CENTER_Y = 215;

			/*
			 * Shooter Values
			 */
			BALL_TRIGGER_CLOSED = 0.8;
			BALL_TRIGGER_OPEN = 0.6;
			SHOOTER_OPTIMAL_SPEED = 1500.0;
		}
	}
}
