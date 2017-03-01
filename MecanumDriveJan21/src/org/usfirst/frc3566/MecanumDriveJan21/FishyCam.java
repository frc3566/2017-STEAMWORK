package org.usfirst.frc3566.MecanumDriveJan21;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.Bearing;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.Orientation;
import org.usfirst.frc3566l.MecanumDriveJan21.navigation.VisionValues;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

/**
 * Thread the camera processing to allow for synchronous decision-making based
 * on the feed.
 */
public class FishyCam extends Thread {

	public static int FPStotal = 24, defaultStart = FPStotal / 2;

	// total should be smaller than 24 to make sure each cam starts at <= 12
	private int myFPS;
	private UsbCamera camera;
	private CvSink cvSink;
	private CvSource outputStream;
	private Mat mat;
	private GripPipeline pipeline;
	private static Orientation orientation;
	private static boolean targetsDetected;
	private static double horizonSlope, centerX, centerY, averageArea;

	/**
	 * A general "bad value" for coordinates
	 */
	public static final int INVALID = -1;

	/**
	 * Constructor
	 * 
	 * @param portNumber
	 *            Which USB port are we searching for the camera?
	 * @param startFPS
	 *            How many FPS should we start with (smaller is better, for
	 *            memory/processing purposes)?
	 */
	public FishyCam(int portNumber, int startFPS) {
		// Get the UsbCamera from CameraServer
		camera = CameraServer.getInstance().startAutomaticCapture(portNumber);
		// Set the resolution
		camera.setResolution(640, 480);

		myFPS = startFPS;

		// Get a CvSink. This will capture Mats from the camera
		cvSink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard

		outputStream = CameraServer.getInstance().putVideo("HSL " + portNumber, 640, 480);

		// Mats are very memory expensive. Lets reuse this Mat.
		mat = new Mat();

		// create example of gripPipeline class
		pipeline = new GripPipeline();

		// This cannot be 'true'. The program will never exit if it is. This
		// lets the robot stop this thread when restarting robot code or
		// deploying.

	}

	/**
	 * The main method for this thread -- handles all vision data collection
	 */
	@Override
	public void run() {
		/* reuse variables to ease on memory use... maybe */
		MatOfPoint contour1, contour2;
		Rect rect1, rect2, left, right, top, bottom;

		while (!Thread.interrupted()) {
			// updates the fps
			camera.setFPS(myFPS);

			/*
			 * Tell the CvSink to grab a frame from the camera and put it in the
			 * source mat. If there is an error notify the output.
			 */
			if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());

				// skip the rest of the current iteration
				continue;
			}

			pipeline.process(mat); // puts mat through pipeline

			/*
			 * Give the output stream a new image to display
			 */
			/*
			 * FIXME **don't** necessarily need this in competition b/cc it
			 * slows down the dashboard
			 */
			outputStream.putFrame(pipeline.hslThresholdOutput());

			/*
			 * the loop finds out the first, second and third biggest contours
			 * in the filtered results
			 */
			int count = 0;
			double largestArea = INVALID, secondLargestArea = INVALID;
			int largestIndex = INVALID, secondLargestIndex = INVALID;
			for (MatOfPoint m : pipeline.filterContoursOutput()) {
				double temp = Imgproc.contourArea(m);
				if (temp >= largestArea) {
					secondLargestArea = largestArea;
					largestArea = temp;
					secondLargestIndex = largestIndex;
					largestIndex = count;
				} else if (temp >= secondLargestArea) {
					secondLargestArea = temp;
					secondLargestIndex = count;
				}
				count++;
			}

			/*
			 * if 2 major visionTargets in view...We're seeing the targets!! for
			 * now, we don't know if they are vertical or horizontal yet
			 */
			targetsDetected = largestArea > VisionValues.TargetAreaThreshold
					&& secondLargestArea > VisionValues.TargetAreaThreshold;
			if (targetsDetected) {

				/*
				 * get the two largest contours
				 */
				contour1 = pipeline.filterContoursOutput().get(largestIndex);
				contour2 = pipeline.filterContoursOutput().get(secondLargestIndex);

				/*
				 * create bounding rects around contours and determine left and
				 * right, top and bottom targets
				 */
				rect1 = Imgproc.boundingRect(contour1);
				rect2 = Imgproc.boundingRect(contour2);
				if (rect1.x < rect2.x) {
					left = rect1;
					right = rect2;
				} else {
					left = rect2;
					right = rect1;
				}
				if (rect1.y < rect2.y) {
					top = rect1;
					bottom = rect2;
				} else {
					top = rect2;
					bottom = rect1;
				}
				rect1 = null;
				rect2 = null;

				/**
				 * Calculate slope along the bottom of the bounding rects
				 * (should be close to flat if we are headed dead ahead at the
				 * target
				 * 
				 * A positive slope indicates that we are closer on the left and
				 * should therefore strafe right AND turn left
				 * 
				 * A negative slope indicates that we are close on the right and
				 * should therefore strafe left AND turn right
				 * 
				 * NOTA BENE: we use negative rise to calculate slope so we get
				 * a meaningful slope on a standard cartesian first quadrant,
				 * rather than the upside-down cartesian first quadrant in the
				 * video
				 */
				horizonSlope = (double) -((right.y + right.height) - (left.y + left.height))
						/ (double) ((right.x + right.width) - left.x);

				/*
				 * checks if targets are vertical or horizontal
				 */
				orientation = (right.x > left.x + left.width ? Orientation.LIFT_HOOK : Orientation.HIGH_GOAL);

				/*
				 * calculate the center point of the two targets
				 */
				centerX = (left.x + right.x + right.width) / 2;
				centerY = (top.y + bottom.y + bottom.height) / 2;

				/*
				 * Calculate average area of targets (for proximity sensing)
				 */
				averageArea = (largestArea + secondLargestArea) / 2;

			}
			Robot.table.putValue("Targets Detected", isTargetsDetected());
			Robot.table.putValue("Average Detected Area", getArea());
			Robot.table.putValue("Center X", getCenterX());
			Robot.table.putValue("Center Y", getCenterY());
			Robot.table.putValue("Orientation", getOrientation().toString());
			Robot.table.putValue("Horizon Slope", getHorizonSlope());
			Robot.table.putValue("Bearing to Target", getBearingToTarget().toString());
		}
	}

	/**
	 * Adjust the frames-per-second of the camera (fewer makes us take up less
	 * memory/processing, but makes us less accurate)
	 * 
	 * @param fps
	 */
	public void setFPS(int fps) {
		myFPS = fps;
	}

	/**
	 * @return X-coordinate of the average center of the vision target, if
	 *         vision targets are visible. `FishyCam.INVALID` if no vision
	 *         targets are visible.
	 */
	public static double getCenterX() {
		if (targetsDetected) {
			return centerX;
		}
		return INVALID;
	}

	/**
	 * @return Y-coordinate of the average center of the vision target, if
	 *         vision targets are visible. `FishyCam.INVALID` if no vision
	 *         targets are visible.
	 */
	public static double getCenterY() {
		if (targetsDetected) {
			return centerY;
		}
		return INVALID;
	}

	/**
	 * @return Orientation of the vision target (vertical for lift target,
	 *         horizontal for high goal target), if vision targets are visible.
	 *         `FishyCam.Orientation.NA` if no vision targets are visible.
	 */
	public static Orientation getOrientation() {
		if (targetsDetected) {
			return orientation;
		}
		return Orientation.NA;
	}

	/**
	 * @return Average area of the vision targets (for comparison to ideal
	 *         values in VisionValues to determine range to target), if vision
	 *         targets are visible. `FishyCam.INVALID` if no vision targets are
	 *         visible.
	 * @see org.usfirst.frc3566l.MecanumDriveJan21.navigation.VisionValues
	 */
	public static double getArea() {
		if (targetsDetected) {
			return averageArea;
		}
		return INVALID;
	}

	/**
	 * @return `TRUE` if and only if vision targets are in camera view, `FALSE`
	 *         otherwise
	 */
	public static boolean isTargetsDetected() {
		return targetsDetected;
	}

	/**
	 * @return Slope of the "horizon" along the bottom of the vision targets
	 *         (connecting the bottom left corner to the bottom right corner of
	 *         their bounding rects), , if vision targets are visible.
	 *         `FishyCam.INVALID` if no vision targets are visible. A positive
	 *         slope indicates that the left side is closer, and the robot
	 *         should therefore turn to the left AND strafe right to fix its
	 *         orientation. Conversely, a negative slope indicates that the
	 *         right side of the target is closer and the robot should turn
	 *         right AND strafe left to bring the target to center.
	 */
	public static double getHorizonSlope() {
		if (targetsDetected) {
			return horizonSlope;
		}
		return INVALID;
	}

	/**
	 * @return The bearing of the target relative to the robot -- that is, if
	 *         the target is to the left, `FishyCam.Bearing.LEFT`, or if the
	 *         target is above the desired location, `FishyCam.Bearing.UP`.
	 *         Bearings are returned based on the detected orientation of the
	 *         vision targets. If no vision target is in sight,
	 *         `FishyCam.Bearing.NA`.
	 */
	public static Bearing getBearingToTarget() {
		/*
		 * Nota bene: we use the getOrientation() method here to protect against
		 * the possibility that there might not be any vision targets in sight
		 * (and because we're lazy and don't want to bother with fancy checks
		 * for _that_ here.)
		 */
		if (getOrientation() == Orientation.LIFT_HOOK) {
			if (centerX < VisionValues.VISION_MIN_HORIZ_BEARING) {
				return Bearing.LEFT;
			} else if (centerX > VisionValues.VISION_MAX_HORIZ_BEARING) {
				return Bearing.RIGHT;
			}
			return Bearing.CENTER;
		} else if (getOrientation() == Orientation.HIGH_GOAL) {
			if (centerY < VisionValues.VISION_HIGH_GOAL_MIN_HEIGHT) {
				return Bearing.UP;
			} else if (centerY > VisionValues.VISION_HIGH_GOAL_MAX_HEIGHT) {
				return Bearing.DOWN;
			}
			return Bearing.CENTER;
		}
		return Bearing.NA;
	}
}