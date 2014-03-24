package tictactoe.agent;

import java.util.Collection;
import java.util.Vector;

public class State {

	int numWhitePieces;
	int numBlackPieces;
	Vector<Integer> tiles;
	public State() {
		initState();
	}
	public State(State state) {
		initState();
	}
	
	private void initState() {
		numWhitePieces = 20;
		numBlackPieces = 20;
		tiles = new Vector<Integer>(32);
		placePieces(0,1); //Starting at pos(0), put 20 white pieces down
		placePieces(20,2);//Starting at pos(25), put 20 black pieces down
	}
	
	private void placePieces(int start, int piece) {
		for (int i = 0; i < 11; i++) {
			tiles.add(start++, piece);
		}
		
	}
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}
	public float getHVal() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Boolean isGoalState() {
		// TODO Auto-generated method stub
		return null;
	}
	public static Vector<State> getChildren(State state) {
		Vector<State> childStates = new Vector<State>();
		childStates.addAll(moveNW(state, childStates));
		childStates.addAll(moveNE(state, childStates));
		childStates.addAll(moveSW(state, childStates));
		childStates.addAll(moveSE(state, childStates));
		childStates.addAll(jump(state, childStates));
		return childStates;
	}
	private static Vector<State> moveNW(State state, Vector<State> childStates) {
		//Init start/end to moving sides pieces
		int start;
		int end;
		//TODO Determine way to know whos children we are generating
		//If player 1 children
		if(true) {
			start = 0;
			end = 0;
		} else { //If player 2 children
			start = 0;
			end = 0;
		}
		
		//Generate states for all pieces of current side
		while(start++<end) {
			State possibleState = new State(state);
			swap(possibleState.getTiles(), start-5, start); //TODO get correct offset value
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
		}
		return childStates;
	}
	private static Vector<State> moveNE(State state, Vector<State> childStates) {
		// TODO Auto-generated method stub
		return childStates;
	}
	private static Vector<State> moveSW(State state, Vector<State> childStates) {
		// TODO Auto-generated method stub
		return childStates;
	}
	private static Vector<State> moveSE(State state, Vector<State> childStates) {
		// TODO Auto-generated method stub
		return childStates;
	}
	private static Vector<State> jump(State state, Vector<State> childStates) {
		// TODO Auto-generated method stub
		return childStates;
	}
	private static void swap(Vector<Integer> tiles, int a, int b) {
		int temp = tiles.get(a);
		tiles.add(a, tiles.get(b));
		tiles.add(b, temp);
		
	}
	public Vector<Integer> getTiles() {
		return tiles;
	}

}
