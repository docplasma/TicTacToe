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
		initGraphics(initialState);
		currentState = initialState;
		gameRunning = true;
		GameLoop();
	}
	//GameLoop With Checks
	/*public void GameLoop() {
		Scanner scanner = new Scanner(System.in);
		int selection = 0;
		int selectionMoveTo = 0;
		while(gameRunning) {
			System.out.println("Please enter the checker location to move");
			selection = scanner.nextInt();
			while(!currentState.checkValidSelection(selection)) {
				System.out.println("Please select a valid piece.");
				selection = scanner.nextInt();
			}
			System.out.println("Please enter the location to move the checker");
			selectionMoveTo = scanner.nextInt();
			while(!currentState.checkValidMove(selection, selectionMoveTo)) {
				System.out.println("Please enter a valid move. You must jump if you can.");
				selection = scanner.nextInt();
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
	}*/
	public void GameLoop() {
		Scanner scanner = new Scanner(System.in);
		int selection = 0;
		int selectionMoveTo = 0;
		while(gameRunning) {
			System.out.println("Please enter the checker location to move");
			selection = scanner.nextInt();
			System.out.println("Please enter the location to move the checker");
			selectionMoveTo = scanner.nextInt();
			movePiece(selection, selectionMoveTo);
			rerender();
			System.out.println("Is there another jump available? 0=no 1=yes");
			if(scanner.nextInt() == 1) {
				System.out.println("Please enter the checker location to move");
				selection = scanner.nextInt();
				System.out.println("Please enter the location to move the checker");
			}
			computerMove();
			rerender();
			
		}
		scanner.close();
	}
	private void computerMove() {
		Agent agent = new Agent();
		Vector<State> possibleStates = new Vector<State>();
		Vector<ABNode> possibleMoves = new Vector<ABNode>();
		PriorityQueue<ABNode> sortedMoves = new PriorityQueue<ABNode>();
		possibleStates = State.getChildren(currentState, 0);
		for(State s : ((Vector<State>) State.getChildren(currentState, 0))) {
			possibleMoves.add(new ABNode(null, s));
	}
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
	private void initGraphics(State initialState) {
		JFrame gameFrame = new JFrame();
		gameWindow = new SurfaceWindow(initialState);
		gameFrame.add(gameWindow);
		gameFrame.setPreferredSize(new Dimension(800, 800));
		gameFrame.pack();
		gameFrame.setVisible(true);
	}
	public void rerender() {
		if(gameWindow == null) System.out.println("gameWindow is null");
		gameWindow.repaint();
	}
}
