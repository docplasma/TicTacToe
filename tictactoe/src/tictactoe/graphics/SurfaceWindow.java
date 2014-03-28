package tictactoe.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

import tictactoe.agent.State;

public class SurfaceWindow extends JPanel {

	int TILEWIDTH = 100;
	int TILEHEIGHT = 100;
	int NUMXTILES = 8;
	int NUMYTILES = 8;
	Checkerboard board;
	Vector<CheckerPiece> checkerPieces;
	
	public SurfaceWindow() {
		board = new Checkerboard();
		checkerPieces = new Vector<CheckerPiece>();
		//board.setVisible(true);
		this.setVisible(true);
		//JLabel checkerboardLabel = board.getCheckerboard();
		//this.add(checkerboardLabel);
		this.repaint();
	}
	public SurfaceWindow(State state) {
		board = new Checkerboard();
		checkerPieces = new Vector<CheckerPiece>();
		loadPieces(state.getTiles());
		repaint();
	}
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		if(checkerPieces != null) {
			int num = 0;
			for(CheckerPiece piece : checkerPieces) {
				System.out.println("Painting checker number:" + num++);
				piece.setBounds(piece.getXPos(), piece.getYPos(), 100, 100);
				add(piece);
			}
			board.setBounds(0,0, 800, 800);
			add(board);
		}
	}
	public void loadPieces(Vector<Integer> tiles) {
		//Position to render piece (Top Left Corner)
		int xDrawHead = 100;
		int yDrawHead = 0;
		int row = 0;
		for(int tile : tiles) {
			if(tile == 0) {
			} else if(tile == 1) {
				checkerPieces.add(new CheckerPiece(1, xDrawHead, yDrawHead));
			} else if(tile == 2) {
				checkerPieces.add(new CheckerPiece(2, xDrawHead, yDrawHead));
			} else if (tile == 3) {
				checkerPieces.add(new CheckerPiece(3, xDrawHead, yDrawHead));
			} else if (tile == 4) {
				checkerPieces.add(new CheckerPiece(4, xDrawHead, yDrawHead));
			}
			
			if(row % 2 == 0 && xDrawHead == (NUMXTILES - 1) * TILEWIDTH) {
				/*Move the Drawhead to the first black tile on the next row if xDrawHead
					is on the last Black Tile (2nd to last tile) and this is an odd numbered row*/
				xDrawHead = 0;
				yDrawHead += TILEHEIGHT;
				row++;
			} else if(row % 2 == 1 && xDrawHead == (NUMXTILES - 2) * TILEWIDTH) {
				/*Move the Drawhead to the first black tile on the next row if xDrawHead
				is on the last Black Tile (last tile) and this is an even numbered row*/
				xDrawHead = TILEWIDTH;
				yDrawHead += TILEHEIGHT;
				row++;
			} else {
				//Move Drawhead to the next tile
				xDrawHead += TILEWIDTH * 2;
			}
		}	
	}
}
