package org.usfirst.frc3566.MecanumDriveJan21;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

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

	public static enum Orientation {
		VERTICAL, HORIZONTAL, NA
	};

	public static final int INVALID = -1;

	public FishyCam(int portNumber, int startFPS) {
		// Get the UsbCamera from CameraServer
		camera = CameraServer.getInstance().startAutomaticCapture(portNumber);
		// Set the resolution
		camera.setResolution(640, 480);

		myFPS = startFPS;
		// camera.setFPS(myFPS);

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

	private double fuzzyLogic(double newCoordinate, double oldCoordinate) {
		return newCoordinate;
	}

	@Override
	public void run() {
		/* reuse variables to ease on memory use... maybe */
		MatOfPoint contour1, contour2;
		Rect rect1, rect2, left, right, top, bottom;
		Point bottomLeft, bottomRight;

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
			 * 
			 * **don't** necessarily need this in competition bc it slows down
			 * the dashboard
			 */
			outputStream.putFrame(pipeline.hslThresholdOutput());

			/*
			 * the loop finds out the first, second and third biggest contours
			 * in the filtered results
			 */
			/*
			 * TODO check if in contoursOutput the contours are already sorted
			 * by size
			 */
			int count = 0;
			double largestArea = 0, secondLargestArea = 0;
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
				 */
				horizonSlope = (double) ((right.y + right.height) - (left.y + left.height)) / (double) ((right.x + right.width) - left.x);

				/*
				 * checks if targets are vertical or horizontal
				 */
				orientation = (right.x > left.x + left.width ? Orientation.VERTICAL : Orientation.HORIZONTAL);

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
		}
	}

	public void setCamFPSvalue(int fps) {
		myFPS = fps;
	}

	public double getCenterX() {
		if (targetsDetected) {
			return centerX;
		}
		return INVALID;
	}

	public double getCenterY() {
		if (targetsDetected) {
			return centerY;
		}
		return INVALID;
	}

	public Orientation getOrientation() {
		if (targetsDetected) {
			return orientation;
		}
		return Orientation.NA;
	}

	public static double getArea() {
		if (targetsDetected) {
			return averageArea;
		}
		return INVALID;
	}

	public static boolean isTargetsDetected() {
		return targetsDetected;
	}

	public static double getHorizonSlope() {
		if (targetsDetected) {
			return horizonSlope;
		}
		return INVALID;
	}
	
	public static boolean isCenterXInRange() {
		if (orientation == Orientation.VERTICAL) {
			return (centerX > VisionValues.inRangeXminV && centerX < VisionValues.inRangeXmaxV);
		} else if (orientation == Orientation.HORIZONTAL) {
			// TODO work on this if we want high goal in autonomous
			return false;
		} else {
			return false;
		}
	}
}