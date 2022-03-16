package Sketch;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

/**
 * This is a demo class using Minim mostly to figure out what kind of
 * functionality it has.
 * 
 * @author Ian Tai Ahn
 *
 */
public class MySketch extends PApplet {

	/**
	 * Only called once at runtime, and configure the size of the window, as well as
	 * how how smooth graphics show.
	 */
	public void settings() {
		size(512, 200);
		smooth(8);

	}

	/*
	 * We specify how many times the draw() class will be called using frameRate,
	 * and we can instantiate class objects to be used on our draw class.
	 */
	public void setup() {
		frameRate(5);

	}

	/**
	 * From what I've learned, this class is called repeatedly, and so it acts as a
	 * loop of a sort. This is called once per frame, so if frame rate is 60, then
	 * it will be called 60 times in one second
	 */
	public void draw() {
		background(0);
		stroke(255);
//		line(0, random(500), 500, random(500));
//		triangle(80, random(300), windowWidth / 2, random(300), windowWidth - 80, random(300));

	}

	/**
	 * Main method where we create the sketch, and call it using Processing's
	 * libraries to handle all of that jFrame/jPanel business. Also, since we are
	 * using this in an IDE, and not their Processing environment, the argument
	 * passed to main must match the class name
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String[] processingArgs = { "MySketch" };
		MySketch mySketch = new MySketch();
		PApplet.runSketch(processingArgs, mySketch);
	}
}