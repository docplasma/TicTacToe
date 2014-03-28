package tictactoe.graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class CheckerPiece extends JComponent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int pieceType;
	int xPos;
	int yPos;
	
	public CheckerPiece() {
		
	}
	public CheckerPiece(int pieceType, int xDrawHead, int yDrawHead) {
		this.pieceType = pieceType;
		xPos = xDrawHead;
		yPos = yDrawHead;
		this.setPreferredSize(new Dimension(100, 100));
	}
	public void paintComponent(Graphics g) {
		System.out.println("Hey I am being drawn...");
		g.drawImage(loadImage(pieceType), 0, 0, null);
	}
	public BufferedImage loadImage(int pieceType) {
		File imageFile = null;
		if(pieceType == 1) {
			imageFile = new File("resources/black_man.png");
		} else if(pieceType == 2) {
			imageFile = new File("resources/white_man.png");
		} else if(pieceType == 3) {
			imageFile = new File("resources/black_king.png");
		} else {
			imageFile = new File("resources/white_king.png");
		}
		BufferedImage piece = null;
		try {
			piece = ImageIO.read(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return piece;
	}

	public static Component getJLabel(int pieceType, int xDrawHead, int yDrawHead) {
		File imageFile = null;
		if(pieceType == 1) {
			imageFile = new File("resources/black_man.png");
		} else if(pieceType == 2) {
			imageFile = new File("resources/white_man.png");
		} else if(pieceType == 3) {
			imageFile = new File("resources/black_king.png");
		} else {
			imageFile = new File("resources/white_king.png");
		}
		
		BufferedImage piece = null;
		try {
			piece = ImageIO.read(imageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel pieceLabel = new JLabel(new ImageIcon(piece));
		//pieceLabel.setLocation(xDrawHead, yDrawHead);
		return pieceLabel;
		
		
	}
	/**
	 * @return the x
	 */
	public int getXPos() {
		return xPos;
	}
	/**
	 * @param x the x to set
	 */
	public void setXPos(int x) {
		this.xPos = x;
	}
	/**
	 * @return the y
	 */
	public int getYPos() {
		return yPos;
	}
	/**
	 * @param y the y to set
	 */
	public void setYPos(int y) {
		this.yPos = y;
	}

}
