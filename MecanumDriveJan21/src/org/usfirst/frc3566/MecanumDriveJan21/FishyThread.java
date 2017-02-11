package org.usfirst.frc3566.MecanumDriveJan21;


import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class FishyThread extends Thread {

	public static int FPStotal = 24, defaultStart = FPStotal/2;
	
	//total should be smaller than 24 to make sure each cam starts at <= 12
	private int myFPS;
	private UsbCamera camera;
	private CvSink cvSink;
	private CvSource outputStream;
	private Mat mat;
	private GripPipelineJan25 pipeline;
	private static double detectedVerticalTargetXCenter, detectedVerticalTargetYCenter,
	detectedHorizontalTargetXCenter, detectedHorizontalTargetYCenter;
	private static char VHcheck;
	private static double avgDetectedTargetArea;
	private static boolean targetsDetected;
	
	public FishyThread(int portNumber, int startFPS) {
		// Get the UsbCamera from CameraServer
		camera = CameraServer.getInstance().startAutomaticCapture(portNumber);
		// Set the resolution
		camera.setResolution(640, 480);
		
		myFPS = startFPS;
		//camera.setFPS(myFPS);

		// Get a CvSink. This will capture Mats from the camera
		cvSink = CameraServer.getInstance().getVideo();
		// Setup a CvSource. This will send images back to the Dashboard

		outputStream = CameraServer.getInstance().putVideo("HSL " + portNumber, 640, 480);

		// Mats are very memory expensive. Lets reuse this Mat.
		mat = new Mat();

		// create example of gripPipeline class
		pipeline = new GripPipelineJan25();
		
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
			//**doesn't necessarily need this in competition bc it slows down the dashboard
			outputStream.putFrame(pipeline.hslThresholdOutput());
			
			 int count = 0; double max1 = 0, max2= 0 , max3 = 0; 
			 int maxNum1 = -1, maxNum2 = -1;
	//the loop finds out the first, second and third biggest contours in the filtered results
			 //TODO check if in contoursOutput the contours are already sorted by size
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
			 
			 
		//if 2 major visionTargets in view...We're seeing the targets!!
			 //for now, we don't know if they are vertical or horizontal yet
			 	if(max1 > VisionValues.TargetAreaThreshold && 
					 max2 > VisionValues.TargetAreaThreshold){
				 
			 	targetsDetected = true;
			 	
				MatOfPoint temp1 = pipeline.filterContoursOutput().get(maxNum1);
				MatOfPoint temp2 = pipeline.filterContoursOutput().get(maxNum2);
				
				//create bounding rects around contours
				Rect r1 = Imgproc.boundingRect(temp1);
				Rect r2 = Imgproc.boundingRect(temp2);
				
				int r1X = r1.x, r1Y=r1.y, r2X = r2.x, r2Y = r2.y, r1Width = r1.width,
						r1Height = r1.height, r2Width = r2.width, r2Height = r2.height;
				 Robot.table.putValue("1stTargetX", r1X);
				 Robot.table.putValue("1stTargetY", r1Y);
				 Robot.table.putValue("2ndTargetX", r2X);
				 Robot.table.putValue("2ndTargetY", r2Y);
				 
				 avgDetectedTargetArea = (max1+max2)/2;
				
				 //checks if targets are vertical or horizontal
				 VHcheck = (r1X>r2X)? //checks which r is at left
							//if r2 at left (r1x>r2x)
					(	(r1X>(r2X+r2Width))? //checks if the two r are touching
							'V': 'H'	) //if not vertical, f touching then horizontal
					//if r1 at left (r1x<r2x)
					: (	(r2X>(r1X+r1Width))? //checks if the two r are touching
							'V': 'H' ); 
				 
					 if(VHcheck=='V'){
						 
			detectedVerticalTargetXCenter = (r1X>r2X)? //checks which r is at left
				((r1X+r2X+r2Width)/2) : ((r1X+r2X+r1Width)/2); 
				 Robot.table.putValue("CalculatedXCenter", detectedVerticalTargetXCenter);
				 
			detectedVerticalTargetYCenter = ((r1Y+r1Height/2) + (r2Y+r2Height/2))/2;
				Robot.table.putValue("CalculatedYCenter", detectedVerticalTargetYCenter);
				
				 detectedHorizontalTargetXCenter = -1;
				 detectedHorizontalTargetYCenter = -1;
					 }else if(VHcheck=='H'){
						 
			detectedHorizontalTargetXCenter = ((r1X+r1Width/2) + (r2X+r2Width/2))/2;
				Robot.table.putValue("CalculatedXCenter", detectedHorizontalTargetXCenter);
				
			detectedHorizontalTargetYCenter = (r1Y>r2Y)? //checks which r is at top
							((r1Y+r2Y+r2Height)/2) : ((r1Y+r2Y+r1Height)/2);
				Robot.table.putValue("CalculatedYCenter", detectedHorizontalTargetYCenter);
				
				detectedVerticalTargetXCenter = -1;
				 detectedVerticalTargetYCenter = -1;
					 }
					 
			 }else{
				 //if didn't pass basic target line, will not recognize targets
				 targetsDetected = false;
				 Robot.table.putValue("1stTargetX", "NA");
				 Robot.table.putValue("1stTargetY", "NA");
				 Robot.table.putValue("2ndTargetX", "NA");
				 Robot.table.putValue("2ndTargetX", "NA");
				 Robot.table.putValue("CalculatedXCenter", "NA");
				 Robot.table.putValue("CalculatedYCenter", "NA");
				 VHcheck = 'N';
				 detectedVerticalTargetXCenter = -1;
				 detectedVerticalTargetYCenter = -1;
				 detectedHorizontalTargetXCenter = -1;
				 detectedHorizontalTargetYCenter = -1;
				 avgDetectedTargetArea = -1;
			 }
			 	 Robot.table.putValue("avgArea", avgDetectedTargetArea);
			 	 Robot.table.putValue("VorH", VHcheck);
		}
}
	

	public void setCamFPSvalue(int fps) {
			myFPS = fps;
	}

	public double getXCenter(){
		if(VHcheck=='V'){
			return detectedVerticalTargetXCenter;
		}else if(VHcheck=='H'){
			return detectedHorizontalTargetXCenter;
		}else{
			return -1;
		}
	}
	
	public double getYCenter(){
		if(VHcheck=='V'){
			return detectedVerticalTargetYCenter;
		}else if(VHcheck=='H'){
			return detectedHorizontalTargetYCenter;
		}else{
			return -1;
		}
	}
	
	public char VorH(){
		return VHcheck;
	}
	
	public static double getArea(){
		return avgDetectedTargetArea;
	}
	
	public static boolean checkIfTargetsDetected(){
		return targetsDetected;
	}
	
	public static boolean checkIfXCenterInRange(){
		if(VHcheck=='V'){
			if(detectedVerticalTargetXCenter > VisionValues.inRangeXminV &&
					detectedVerticalTargetXCenter < VisionValues.inRangeXmaxV){
				return true;
			}else{
				return false;
			}
		}else if(VHcheck=='H'){
			//TODO work on this if we want high goal in autonomous
			return false;
		}else{
			return false;
		}
	}
	
	/**
	 * Since the camera is fixed, we don't need the y check for now
	 */
//	public boolean checkIfYCenterInRange(){
//		if(VHcheck=='V'){
//			if(detectedVerticalTargetYCenter > VisionValues.inRangeYminV &&
//					detectedVerticalTargetYCenter < VisionValues.inRangeYmaxV){
//				return true;
//			}else{
//				return false;
//			}
//		}else if(VHcheck=='H'){
//			//TODO work on this if we want high goal in autonomous
//			return false;
//		}else{
//			return false;
//		}
//	}
}