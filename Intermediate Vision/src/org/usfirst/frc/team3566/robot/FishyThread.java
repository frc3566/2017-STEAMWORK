package org.usfirst.frc.team3566.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionThread;
import visionPac.GripPipelineJan10;

public class FishyThread extends Thread {

	public static int FPStotal = 24, defaultStart = FPStotal/2;
	//total should be smaller than 24 to make sure each cam starts at <= 12
	private int myFPS;
	private UsbCamera camera;
	private CvSink cvSink;
	private CvSource outputStream;
	private Mat mat;
	private GripPipelineJan10 pipeline;
	private final Object imgLock = new Object();
	
	public FishyThread(int portNumber, int startFPS) {
		// Get the UsbCamera from CameraServer
		camera = CameraServer.getInstance().startAutomaticCapture(portNumber);
		// Set the resolution
		camera.setResolution(640, 480);
		myFPS = startFPS;
		camera.setFPS(myFPS);

		// Get a CvSink. This will capture Mats from the camera
		cvSink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard

		outputStream = CameraServer.getInstance().putVideo("Rectangle " + portNumber, 640, 480);

		// Mats are very memory expensive. Lets reuse this Mat.
		mat = new Mat();

		// create example of gripPipeline class
		pipeline = new GripPipelineJan10();
		
		// This cannot be 'true'. The program will never exit if it is. This
		// lets the robot stop this thread when restarting robot code or
		// deploying.

	}

	@Override
	public void run() {
		 while (!Thread.interrupted()) {
			// updates the fps
			camera.setFPS(myFPS);
			System.out.println(myFPS);
			// Tell the CvSink to grab a frame from the camera and put it
			// in the source mat. If there is an error notify the output.
			if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());
				// skip the rest of the current iteration
				// continue;
			}
			pipeline.process(mat); // processes our mat through grip generated
									// code
			//Imgproc  .boundingRect(pipeline.filterContoursOutput().get(0));
			// Put a rectangle on the image
			//Imgproc.rectangle(mat, new Point(100, 100), new Point(500, 400), new Scalar(255, 255, 255), 5);
			// Give the output stream a new image to display
			outputStream.putFrame(mat);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				System.out.println(e);
			}
		} 
	}

	public void setCamFPSvalue(int fps) {
		//if (fps <= FPStotal) {
			myFPS = fps;
	//	} else {
		//	System.out.println("fps total is "+FPStotal+". Do not exceed the value");
		//}
	}

}
