package Sketch;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

public class Visualizer extends PApplet {
	Minim minim;
	AudioPlayer player;
	FFT fft;
	float[] real;
	float[] img;

	/**
	 * Only called once at runtime, and configure the size of the window, as well as
	 * how how smooth graphics show.
	 */
	public void settings() {
		size(800, 600);
		smooth(8);

	}

	/*
	 * We specify how many times the draw() class will be called using frameRate,
	 * and we can instantiate class objects to be used on our draw class.
	 */
	public void setup() {
		minim = new Minim(this);
		// CHANGEME choose an audio file from your system or the web
		player = minim.loadFile("src/Sketch/example1.mp3");
		player.play();

		// setup the FFT to process this audio file
		fft = new FFT(player.bufferSize(), player.sampleRate());

	}

	/**
	 * From what I've learned, this class is called repeatedly, and so it acts as a
	 * loop of a sort. This is called once per frame, so if frame rate is 60, then
	 * it will be called 60 times in one second
	 */
	public void draw() {
		background(13421772); // reset the display window
		// rgba format
		stroke(200, 100, 0, 70);
		strokeWeight(3); // Thicker

		// perform a forward fft on a buffer of audio
		fft.forward(player.mix);
		real = fft.getSpectrumReal();
		img = fft.getSpectrumImaginary();

		// use for centering the rendering in the window
		int xOffset = (width - fft.specSize()) / 2;

		// get the number of frequency bands and draw each one
		for (int i = 0; i < fft.specSize(); i++) {
			// line API is for point to point drawing using these coordniates:
			// x1, y1, x2, y2
			stroke(real[i] * 25, img[i] * 25, fft.getBand(i) * 7, 170);
			// use polar coordinates
			// can use real(x) and imaginary(y) data for this
			line(i + xOffset, height / 2, i + xOffset + real[i] * 20, height / 2 + img[i] * 20);

		}

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
		String[] processingArgs = { "Visualizer" };
		Visualizer visualizer = new Visualizer();
		PApplet.runSketch(processingArgs, visualizer);
	}

}
