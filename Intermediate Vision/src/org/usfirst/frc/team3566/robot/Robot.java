package org.usfirst.frc.team3566.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

/**
 * This is a demo program showing the use of OpenCV to do vision processing. The
 * image is acquired from the USB camera, then a rectangle is put on the image and
 * sent to the dashboard. OpenCV has many methods for different types of
 * processing.
 */
public class Robot extends IterativeRobot {
	FishyThread[] cams;

	@Override
	public void robotInit() {
		FishyThread camA = new FishyThread (0, 12);
		FishyThread camB = new FishyThread (1, 12);
		cams = new FishyThread[2];
		cams[0] = camA; 
		cams[1] = camB;
		
		camA.setDaemon(true);
		camA.start();
		
		camB.setDaemon(true);
		camB.start();
	}
	
	public void adjustFPSofCams(int camNum, int fps){
		//we want the total fps on the dashboard to be a fixed number
		if(camNum==0){
		cams[0].setCamFPS(fps);
		cams[1].setCamFPS(FishyThread.FPStotal-fps);
		}else if (camNum == 1){
		cams[1].setCamFPS(fps);
		cams[0].setCamFPS(FishyThread.FPStotal-fps);
		}
	}
}
