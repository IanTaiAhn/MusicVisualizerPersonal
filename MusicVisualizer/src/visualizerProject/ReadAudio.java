package visualizerProject;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/*
 * Jim Lotruglio
 * Ian Tai
 * 
 * *** Jonathan Simon WayBackMachine
 * 
 * Creates an object that takes in a file path of .wav file
 * 
 */

public class ReadAudio {
	private String file;

	/*
	 * Constructor
	 * file - file path to .wav file 
	 */
	public ReadAudio(String file) {
		super();
		this.file = file;
	}
	
	/*
	 * Create AudioInputStream object from file path
	 * Parses samples
	 * Returns int[][] with channels and their corresponding samples
	 */
	public int[][] getAudio(){
		
		File myFile = new File(file);
		AudioInputStream audioInputStream = null;
		
		//Creates AudioInputStream object from music file
		try {
			audioInputStream = AudioSystem.getAudioInputStream(myFile);
		} catch (UnsupportedAudioFileException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		//Gets Frame length and size
		int frameLength = (int) audioInputStream.getFrameLength();
		int frameSize = (int) audioInputStream.getFormat().getFrameSize();
		
		//Creates nested int array for audio data
		byte[] eightBitByteArray = new byte[frameLength * frameSize];
		
		
		int result = 0;
		try {
			result = audioInputStream.read(eightBitByteArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		int sampleIndex = 0;
		int numChannels = audioInputStream.getFormat().getChannels();
		int[][] samples = new int[numChannels][frameLength];
		
		
		//Puts samples in appropriate channels 
		for (int t = 0; t < eightBitByteArray.length;) {
			for (int channel = 0; channel < numChannels; channel++) {
				int low =  eightBitByteArray[t];
				t++;
				int high = eightBitByteArray[t];
				t++;
				int sample = getSixteenBitSample(high, low);
				samples[channel][sampleIndex] = sample;
			}
			sampleIndex++;
		}

		return samples;
	}
	
	
	//Does bit shifting to combine 8 bit high and low 
	private static int getSixteenBitSample(int high, int low) {
		return (high << 8) + (low & 0x00ff);
	}
	
}
