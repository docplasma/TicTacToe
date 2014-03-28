package Engine;

import java.awt.Dimension;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;

import tictactoe.agent.ABNode;
import tictactoe.agent.Agent;
import tictactoe.agent.State;
import tictactoe.graphics.SurfaceWindow;

public class Engine {
	SurfaceWindow gameWindow;
	State currentState;
	Boolean gameRunning;
	
	public Engine() {
		State initialState = new State();
		initGraphics(gameWindow, initialState);
		currentState = initialState;
		gameRunning = true;
		GameLoop();
	}
	public void GameLoop() {
		Scanner scanner = new Scanner(System.in);
		int selection = 0;
		int selectionMoveTo = 0;
		while(gameRunning) {
			System.out.println("Please enter the checker location to move");
			selection = scanner.nextInt();
			currentState.checkValidSelection(selection);
			System.out.println("Please enter the location to move the checker");
			selectionMoveTo = scanner.nextInt();
			while(!currentState.checkValidMove(selection, selectionMoveTo)) {
				System.out.println("Please enter a valid move. You must jump if you can.");
			}
			movePiece(selection, selectionMoveTo);
			rerender();
			while(currentState.jumpPossible()) {
				System.out.println("Please make another jump.");
				selection = scanner.nextInt();
				currentState.checkValidSelection(selection);
				System.out.println("Please enter the location to move the checker");
				selectionMoveTo = scanner.nextInt();
				while(!currentState.checkValidMove(selection, selectionMoveTo)) {
					System.out.println("Please enter a valid move. You must jump if you can.");
					movePiece(selection, selectionMoveTo);
					rerender();
				}
			}
			rerender();
			computerMove();
			rerender();
			
		}
		scanner.close();
	}
	private void computerMove() {
		Agent agent = new Agent();
		Vector<ABNode> possibleMoves = new Vector<ABNode>();
		PriorityQueue<ABNode> sortedMoves = new PriorityQueue<ABNode>();
		for(ABNode move : possibleMoves) {
			//Find out how this move ranks, then multiply by zero since the list is sorted by ascending
			move.setMoveValue(agent.abSearch(move, 5, Integer.MIN_VALUE, Integer.MAX_VALUE, true) * -1);
			sortedMoves.add(move);
		}
		currentState = sortedMoves.poll().getState();
	}
	private void movePiece(int selection, int selectionMoveTo) {
		currentState.movePiece(selection, selectionMoveTo);
		
	}
	private void initGraphics(SurfaceWindow gameWindow, State initialState) {
		JFrame gameFrame = new JFrame();
		gameWindow = new SurfaceWindow(initialState);
		gameFrame.add(gameWindow);
		gameFrame.setPreferredSize(new Dimension(800, 800));
		gameFrame.pack();
		gameFrame.setVisible(true);
	}
	public void rerender() {
		gameWindow.repaint();
	}
}
