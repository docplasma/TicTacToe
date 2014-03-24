package tictactoe.agent;

import java.util.Collection;
import java.util.Vector;

public class State {

	boolean validState;
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
		validState = true;
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
		return validState;
	}
	public void setValid(boolean val) {
		validState = val;
	}
	public float getHVal() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Boolean isGoalState() {
		// TODO Auto-generated method stub
		return null;
	}
	public static Vector<State> getChildren(State state, int player) {
		Vector<State> childStates = new Vector<State>();
		childStates.addAll(moveNW(state, childStates, player));
		childStates.addAll(moveNE(state, childStates, player));
		childStates.addAll(moveSW(state, childStates, player));
		childStates.addAll(moveSE(state, childStates, player));
		childStates.addAll(jump(state, childStates, player));
		return childStates;
	}
	private static Vector<State> moveNW(State state, Vector<State> childStates, int player) {	
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;
		//TODO Determine way to know whos children we are generating?
		
		//Generate states for all pieces of current side
		while(start++<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			if(start%4%2 == 0) {
				if (start - 4 < 0) {
					possibleState.isValid();
				} else {
					swap(tiles, start-4, start);
				}
				
			} else {
				if (start - 5 < 0) {
					possibleState.isValid();
				} else {
					swap(tiles, start-5, start);
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
		}
		return childStates;
	}
	private static Vector<State> moveNE(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;
		//TODO Determine way to know whos children we are generating

		//Generate states for all pieces of current side
		while(start++<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			if(start%4%2 == 0) {
				if (start - 3 < 0) {
					possibleState.isValid();
				} else {
					swap(tiles, start - 3, start);
				}
				
			} else {
				if (start - 4 < 0) {
					possibleState.isValid();
				} else {
					swap(tiles, start - 4, start);
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
		}
		return childStates;
	}
	private static Vector<State> moveSW(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;
		//TODO Determine way to know whos children we are generating

		//Generate states for all pieces of current side
		while(start++<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			if(start%4%2 == 0) {
				if (start + 4 > tiles.size() - 1) {
					possibleState.isValid();
				} else {
					swap(tiles, start + 4, start);
				}
				
			} else {
				if (start + 3 > tiles.size() - 1) {
					possibleState.isValid();
				} else {
					swap(tiles, start + 3, start);
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
		}
		return childStates;
	}
	private static Vector<State> moveSE(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;

		//Generate states for all pieces of current side
		while(start++<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			if(start%4%2 == 0) {
				if (start + 5 > tiles.size() - 1) {
					possibleState.isValid();
				} else {
					swap(tiles, start + 5, start);
				}
				
			} else {
				if (start + 4 > tiles.size() - 1) {
					possibleState.isValid();
				} else {
					swap(tiles, start + 4, start);
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
		}
		return childStates;
	}
	private static Vector<State> jump(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;
		
		//Generate states for all pieces of current side
		while(start++<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			if(start%4%2 == 0) {
				if (start - 4 < 0) {
					possibleState.isValid();
				} else {
					swap(tiles, start-4, start);
				}
				
			} else {
				if (start - 5 < 0) {
					possibleState.isValid();
				} else {
					swap(tiles, start-5, start);
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
		}
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
