package musicVisualizer;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ReadAudioFiles {
	public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
		File file = new File("src/musicVisualizer/wavsample.wav");
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

		int frameLength = (int) audioInputStream.getFrameLength();
		int frameSize = (int) audioInputStream.getFormat().getFrameSize();

		byte[] bytes = new byte[frameLength * frameSize];

		byte[] eightBitByteArray = bytes;

		int result = 0;
		try {
			result = audioInputStream.read(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}

		int sampleIndex = 0;

		int numChannels = audioInputStream.getFormat().getChannels();
		// int frameLength = (int) audioInputStream.getFrameLength();
		int[][] toReturn = new int[numChannels][frameLength];

		for (int t = 0; t < eightBitByteArray.length;) {
			for (int channel = 0; channel < numChannels; channel++) {
				int low = (int) eightBitByteArray[t];
				t++;
				int high = (int) eightBitByteArray[t];
				t++;
				int sample = getSixteenBitSample(high, low);
				toReturn[channel][sampleIndex] = sample;
			}
			sampleIndex++;
		}
		System.out.println("run");
		System.out.println(Arrays.deepToString(toReturn));
		System.out.println("done");

//		System.out.println(toReturn[0][0]);
		for (int[] a : toReturn) {
//			 System.out.println(Arrays.toString(a)[]);
//			 System.out.println(a[200000]);
//			System.out.println(a);
		}

	}

	private static int getSixteenBitSample(int high, int low) {
		return (high << 8) + (low & 0x00ff);
	}

}
