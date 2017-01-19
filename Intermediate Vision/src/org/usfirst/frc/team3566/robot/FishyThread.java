package org.usfirst.frc.team3566.robot;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.vision.VisionThread;
import visionPac.GripPipelineJan10;

public class FishyThread extends Thread {

	public static int FPStotal = 24, defaultStart = FPStotal/2;
	/**test this on network table and see what's the ideal distinction value
	for differentiating actual vision targets and unwanted interfering contours
	**/
	public static int VisionTargetImgProcAreaThreshold = 20000;
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

		outputStream = CameraServer.getInstance().putVideo("HSL " + portNumber, 640, 480);

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
			// Tell the CvSink to grab a frame from the camera and put it
			// in the source mat. If there is an error notify the output.
			if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());
				// skip the rest of the current iteration
				// continue;
			}
			
			pipeline.process(mat); // puts mat through pipeline

			// Give the output stream a new image to display
			outputStream.putFrame(pipeline.hslThresholdOutput());
			//puts rectangle around the first contour if contours are found
			 if (!pipeline.filterContoursOutput().isEmpty()) {
				 Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
			 }
			 int count = 0; double max1 =0, max2=0 , max3 =0; 
			 int maxNum1=-1, maxNum2=-1;
			 for(MatOfPoint m: pipeline.filterContoursOutput()){
				double temp = Imgproc.contourArea(m);
				if(temp>=max1)
				{
					max3=max2;
					max2=max1;
					max1=temp;
					maxNum2=maxNum1;
					maxNum1=count;
				}else if(temp>=max2){
					max3=max2;
					max2=temp;
					maxNum2=count;
				}else if(temp>=max3){
					max3=temp;
				}
				count++;
			 }
			 Robot.table.putValue("Max area 1", max1);
			 Robot.table.putValue("Max area 2", max2);
			 Robot.table.putValue("Max area 3", max3);
			 Robot.table.putValue("TotalContour#", count);
			 if(max1>20000 && max2>20000){
				pipeline.filterContoursOutput().get(maxNum1);
				//	Imgproc.max 
				 
			//	 Robot.table.putValue("1stTargetCoord", );
			//	 Robot.table.putValue("2ndTargetCoord", value);
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
