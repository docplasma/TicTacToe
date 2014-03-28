package Engine;

import javax.swing.JFrame;

import tictactoe.agent.State;
import tictactoe.graphics.SurfaceWindow;

public class Engine {
	SurfaceWindow gameWindow;
	
	public Engine() {
		State initialState = new State();
		initGraphics(gameWindow, initialState);
	}
	private void initGraphics(SurfaceWindow gameWindow, State initialState) {
		JFrame gameFrame = new JFrame();
		gameWindow = new SurfaceWindow(initialState);
		gameFrame.add(gameWindow);
		gameFrame.pack();
		gameFrame.setVisible(true);
	}
	public void rerender() {
		gameWindow.repaint();
	}
}
