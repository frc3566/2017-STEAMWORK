package org.usfirst.frc3566.MecanumDriveJan21.navigation;

import java.util.Iterator;
import java.util.Vector;

/**
 * Store a double value as a "buffered" value -- that is, as a rolling average
 * of the most recent n samples of that value
 */
public class BufferedDouble {
	public static final int DEFAULT_BUFFER_DEPTH = 20;
	
	/**
	 * How many samples to average
	 */
	private int bufferDepth;

	/**
	 * The history of the samples
	 */
	private Vector<Double> history;

	/**
	 * Have we updated the sample since the last time we computed an average?
	 */
	private boolean updated;

	/**
	 * Our current average
	 */
	private double average;

	public BufferedDouble() {
		this(DEFAULT_BUFFER_DEPTH);
	}
	
	public BufferedDouble(int bufferDepth) {
		this.bufferDepth = bufferDepth;
		history = new Vector<Double>();
		updated = true;
	}

	/**
	 * Enqueue a new location, dequeueing the oldest value if we have maxed out
	 * our samples
	 * 
	 * @param x
	 * @param y
	 */
	public void add(double value) {
		if (history.size() == bufferDepth) {
			history.remove(0);
		}
		history.add(value);
		updated = true;
	}

	/**
	 * Compute the average of all values currently in the history
	 */
	private void computeAverage() {
		Iterator<Double> i = history.iterator();
		double finger;
		average = 0;
		while (i.hasNext()) {
			finger = i.next();
			average += finger;
		}
		average = average / (double) history.size();
		updated = false;
	}

	public double getValue() {
		if (updated) {
			computeAverage();
		}
		return average;
	}
}
