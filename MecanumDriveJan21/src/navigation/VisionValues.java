package navigation;

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
	public static double maxHorizontalBearing = 640 / 2 + 20, minHorizontalBearing = 640 / 2 - 20,
			minHeightLiftHookVisionTargets = 185, minHeightHighGoalVisionTarget = 480/2 - 20, maxHeightHighGoalVisionTarget = 480/2 + 20;

	/**
	 * acceptable leveling range for vision target "horizon"
	 */
	public static double maxHorizonSlope = 0.05, minHorizonSlope = -0.05;

	public static double potentiometer0 = -1;
	public static double PotentiometerBottomToMiddleDistance = 51, PotentiometerMiddleToTopDistance = 63;
}
