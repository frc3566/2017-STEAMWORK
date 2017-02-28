package org.usfirst.frc3566l.MecanumDriveJan21.navigation;

/**
 * An enumerated type to describe our possible bearing relative to the
 * vision targets (i.e. is the target to our left, above where we need it to
 * be or -- as with orientation -- are there no targets visible, so our
 * bearing is Bearing.NA?)
 */
public enum Bearing {
	LEFT, RIGHT, UP, DOWN, CENTER, NA
}

