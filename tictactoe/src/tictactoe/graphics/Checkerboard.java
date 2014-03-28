package tictactoe.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class Checkerboard extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BufferedImage checkerboard;
	int x;
	int y;
	int width;
	int height;
	
	public Checkerboard() {
		checkerboard = loadCheckerBoard();
		x = 0;
		y = 0;
		this.setPreferredSize(new Dimension(800,800));
	}
	private BufferedImage loadCheckerBoard() {
		BufferedImage checkerboard = null;
		try {
			checkerboard = ImageIO.read(new File("resources/checkerboard.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return checkerboard;
	}
	public void paintComponent(Graphics g) {
		g.drawImage(checkerboard, 0, 0, null);
	}
}
