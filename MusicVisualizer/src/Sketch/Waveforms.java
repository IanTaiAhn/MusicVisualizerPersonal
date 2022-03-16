package Sketch;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import processing.core.PApplet;

/**
 * This class demonstrates what waveforms are, and how we can create them using
 * Minim's library, and Processing's Core
 * 
 * @author Ian Tai Ahn
 *
 */
public class Waveforms extends PApplet {
	int windowWidth = 500;
	int windowHeight = 500;
	Minim minim;
	AudioPlayer player;
	FFT fft;

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
		minim = new Minim(this);
		player = minim.loadFile("src/Sketch/example1.mp3");
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

		// start of minim library jazz CANT BELIEVE IT WORKED. HAHA Tomorrow more shall
		// be done!
		// draw the waveforms
		// the values returned by left.get() and right.get() will be between -1 and 1,
		// so we need to scale them up to see the waveform
		// note that if the file is MONO, left.get() and right.get() will return the
		// same value
		for (int i = 0; i < player.bufferSize() - 1; i++) {
			float x1 = map(i, 0, player.bufferSize(), 0, width);
			float x2 = map(i + 1, 0, player.bufferSize(), 0, width);
			line(x1, 50 + player.left.get(i) * 50, x2, 50 + player.left.get(i + 1) * 50);
			line(x1, 150 + player.right.get(i) * 50, x2, 150 + player.right.get(i + 1) * 50);
		}

		// draw a line to show where in the song playback is currently located
		float posx = map(player.position(), 0, player.length(), 0, width);
		stroke(0, 200, 0);
		line(posx, 0, posx, height);

		if (player.isPlaying()) {
			text("Press any key to pause playback.", 10, 20);
		} else {
			text("Press any key to start playback.", 10, 20);
		}
	}

	/**
	 * Works like an action or event listener.
	 */
	public void keyPressed() {
		if (player.isPlaying()) {
			player.pause();
		}
		// if the player is at the end of the file,
		// we have to rewind it before telling it to play again
		else if (player.position() == player.length()) {
			player.rewind();
			player.play();
		} else {
			player.play();
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
		// We have to give the name of our class as a string array to the Papplet
		String[] processingArgs = { "Waveforms" };
		// Here he delcar, and initilize the Waveforms class, which is the class we are
		// in, and we need to pass this to our PApplet.
		Waveforms waveforms = new Waveforms();
		// The proccessing library takes in the created waveforms class object, and the
		// name of the class as a string array, does its magic, and handles the
		// jFrame/jPanel stuff for us.
		PApplet.runSketch(processingArgs, waveforms);
	}
}