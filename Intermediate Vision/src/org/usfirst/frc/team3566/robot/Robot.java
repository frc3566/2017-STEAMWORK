package org.usfirst.frc.team3566.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * This is a demo program showing the use of OpenCV to do vision processing. The
 * image is acquired from the USB camera, then a rectangle is put on the image and
 * sent to the dashboard. OpenCV has many methods for different types of
 * processing.
 */
public class Robot extends IterativeRobot {

	private FishyThread camA, camB;
	public static NetworkTable table;
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
		NetworkTable.setIPAddress("roborio-3566-Frc.local");
		table = NetworkTable.getTable("datatable");

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

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
	}
	
	public NetworkTable getTable(){
		return table;
	}
	
	
}
