package visualizerProject;

import java.awt.Color;

import javax.swing.DebugGraphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testRead {

	public static void main(String[] args) {
		ReadAudio newAudio = new ReadAudio("src/visualizerProject/testWav.wav");
		
		int[][] audioArray = newAudio.getAudio();
		
		// rows length is 2 (number of channels)
		// columns length is 268960 (frame length number)
		// so we have a two dimensional array of 2 x 268960
		// this nested for loop iterates through every element in our audioArray
		int counter = 0;
		for (int i = 0; i < audioArray.length; i++) {
			for (int j = i; j < audioArray[i].length; j++) {
//				System.out.println("count: " + counter++ + " "  + audioArray[i][j]);
			}
		}
		
//		Graphics g = new Graphics();
		JFrame frame = new JFrame("testing");
		JPanel pane = new JPanel();
		pane.setBounds(10,10,10,10);
		pane.setBackground(Color.gray);
		
		frame.add(pane);
		frame.setSize(800,500);
		frame.setVisible(true);
		

	}

	public void SingleWavefromPanels (DebugGraphics g)	{
		g.setColor(Color.black);
		g.drawLine(0, 0, 0, 0);
		
	}

}
