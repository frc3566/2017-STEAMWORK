package org.usfirst.frc.team3566.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.vision.VisionThread;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * This is a demo program showing the use of OpenCV to do vision processing. The
 * image is acquired from the USB camera, then a rectangle is put on the image and
 * sent to the dashboard. OpenCV has many methods for different types of
 * processing.
 */
public class Robot extends IterativeRobot {

	private FishyThread camA, camB;
	//private VisionThread myVisionThread;
	//private double centerX = 0.0;
	
	@Override
	public void robotInit() {
		FishyThread camA = new FishyThread (0, FishyThread.defaultStart);
	//	FishyThread camB = new FishyThread (1, FishyThread.defaultStart);
		
		camA.setDaemon(true);
		camA.start();
		
	//	camB.setDaemon(true);
	//	camB.start();
		
		//camA.setFPS(12);
		//12 fps per camera is the maximum the rio can process without breaking.
		//otherwise one camera can have higher fps, but performance is about the same
		
//		//create visionThread to use the pipeline created
//		myVisionThread = new VisionThread(camera, pipeline, pipeline -> {
//	        if (!pipeline.filterContoursOutput().isEmpty()) {
//	            Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
//	            synchronized (imgLock) {
//	                centerX = r.x + (r.width / 2);
//	            }
//	        }
//	    });
//		myVisionThread.start(); 
		
		
	}
	
//	public void adjustFPSofCams(int camNum, int fps){
//		//we want the total fps on the dashboard to be a fixed number
//		if(camNum==0){
//		camA.setCamFPSvalue(fps);
//		camB.setCamFPSvalue(FishyThread.FPStotal-fps);
//		}else if (camNum == 1){
//		camB.setCamFPSvalue(fps);
//		camA.setCamFPSvalue(FishyThread.FPStotal-fps);
//		}
//	}
}
