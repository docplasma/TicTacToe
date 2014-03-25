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
		if(numWhitePieces == 0 || numBlackPieces == 0) {
			return true;
		}
		return false;
	}
	public static Vector<State> getChildren(State state, int player) {
		Vector<State> childStates = new Vector<State>();
		moveNW(state, childStates, player);
		moveNE(state, childStates, player);
		moveSW(state, childStates, player);
		moveSE(state, childStates, player);
		jump(state, childStates, player);
		return childStates;
	}
	private static Vector<State> moveNW(State state, Vector<State> childStates, int player) {	
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;
		//TODO Determine way to know whos children we are generating?
		
		//Generate states for all pieces of current side
		while(start<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				start++;
				continue;
			}
			//Skip black men since they cannot move backwards
			if(tiles.get(start) == 1 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			if(start%4%2 == 0) {
				if (start - 4 < 0) {
					possibleState.setValid(false);
				} else {
					swap(tiles, start-4, start);
				}
				
			} else {
				if (start - 5 < 0) {
					possibleState.setValid(false);
				} else {
					swap(tiles, start-5, start);
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
			start++;
		}
		return childStates;
	}
	private static Vector<State> moveNE(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;
		
		//Generate states for all pieces of current side
		while(start<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				start++;
				continue;
			}
			//Skip black men since they cannot move backwards
			if(tiles.get(start) == 1 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			if(start%4%2 == 0) {
				if (start - 3 < 0) {
					possibleState.setValid(false);
				} else {
					swap(tiles, start - 3, start);
				}
				
			} else {
				if (start - 4 < 0) {
					possibleState.setValid(false);
				} else {
					swap(tiles, start - 4, start);
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
			start++;
		}
		return childStates;
	}
	private static Vector<State> moveSW(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;

		//Generate states for all pieces of current side
		while(start<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				start++;
				continue;
			}
			//Skip white men since they cannot move backwards
			if(tiles.get(start) == 2 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			if(start%4%2 == 0) {
				if (start + 4 > tiles.size() - 1) {
					possibleState.setValid(false);
				} else {
					swap(tiles, start + 4, start);
				}
				
			} else {
				if (start + 5 > tiles.size() - 1) {
					possibleState.setValid(false);
				} else {
					swap(tiles, start + 5, start);
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
			start++;
		}
		return childStates;
	}
	private static Vector<State> moveSE(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;

		//Generate states for all pieces of current side
		while(start<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				start++;
				continue;
			}
			//Skip white men since they cannot move backwards
			if(tiles.get(start) == 2 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			if(start%4%2 == 0) {
				if (start + 5 > tiles.size() - 1) {
					possibleState.setValid(false);
				} else {
					swap(tiles, start + 5, start);
				}
				
			} else {
				if (start + 4 > tiles.size() - 1) {
					possibleState.setValid(false);
				} else {
					swap(tiles, start + 4, start);
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
			start++;
		}
		return childStates;
	}
	private static Vector<State> jump(State state, Vector<State> childStates, int player) {
		jumpNW(state, childStates, player);
		jumpNE(state, childStates, player);
		jumpSW(state, childStates, player);
		jumpSE(state, childStates, player);


		return childStates;
	}
	private static Vector<State> jumpNW(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;
		boolean jumpMade = false;
		
		//Generate states for all pieces of current side
		while(start<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				start++;
				continue;
			}
			//Skip black men since they cannot move backwards
			if(tiles.get(start) == 1 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			//jump sw = 7 se = 9
			//jump nw = -9 ne = -7
			if(start%4%2 == 0) {
				//JumpNW Starting on even row
				if (start - 4 < 0 || start - 9 < 0) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start - 4) == 0 && (tiles.get(start - 4) + 1) % 2 == player) {
						swap(tiles, start - 9, start);
						//take the jumped piece of the board
						tiles.set(start - 4, 0);
						jumpMade = true;
						if(player == 0) {
							possibleState.whiteLosePiece();
						} else {
							possibleState.blackLosePiece();
						}
					}	
				}
			} else {
				//JumpNW Starting on odd row
				if (start - 4 < 0 || start - 9 < 0) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start - 4) == 0 && (tiles.get(start - 4) + 1) % 2 == player) {
						swap(tiles, start - 9, start);
						//take the jumped piece of the board
						tiles.set(start - 4, 0);
						jumpMade = true;
						if(player == 0) {
							possibleState.whiteLosePiece();
						} else {
							possibleState.blackLosePiece();
						}
					}	
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
			start++;
		}
		
		if(!jumpMade) {
			return childStates;
		}
		//If a jump was made, see if another jump is possible
		return jump(state, childStates, player);
	}
	private static Vector<State> jumpNE(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;
		boolean jumpMade = false;
		
		//Generate states for all pieces of current side
		while(start<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				start++;
				continue;
			}
			//Skip black men since they cannot move backwards
			if(tiles.get(start) == 1 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			//jump sw = 7 se = 9
			//jump nw = -9 ne = -7
			if(start%4%2 == 0) {
				//JumpNE
				if (start - 3 < 0 || start - 7 < 0) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start - 3) == 0 && (tiles.get(start - 3) + 1) % 2 == player) {
						swap(tiles, start - 7, start);
						//take the jumped piece of the board
						tiles.set(start - 3, 0);
						jumpMade = true;
						if(player == 0) {
							possibleState.whiteLosePiece();
						} else {
							possibleState.blackLosePiece();
						}
					}	
				}
			} else {
				//JumpNE
				if (start - 3 < 0 || start - 7 < 0) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start - 3) == 0 && (tiles.get(start - 3) + 1) % 2 == player) {
						swap(tiles, start - 7, start);
						//take the jumped piece of the board
						tiles.set(start - 3, 0);
						jumpMade = true;
						if(player == 0) {
							possibleState.whiteLosePiece();
						} else {
							possibleState.blackLosePiece();
						}
					}	
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
			start++;
		}
		if(!jumpMade) {
			return childStates;
		}
		//If a jump was made, see if another jump is possible
		return jump(state, childStates, player);
	}
	private static Vector<State> jumpSW(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;
		boolean jumpMade = false;
		
		//Generate states for all pieces of current side
		while(start<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				start++;
				continue;
			}
			//Skip white men since they cannot move backwards
			if(tiles.get(start) == 2 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			//jump sw = 7 se = 9
			//jump nw = -9 ne = -7
			if(start%4%2 == 0) {
				//JumpSW
				if (start + 4 > tiles.size() -1 || start + 7 > tiles.size() -1) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 4) == 0 && (tiles.get(start + 4) + 1) % 2 == player) {
						swap(tiles, start + 7, start);
						//take the jumped piece of the board
						tiles.set(start + 4, 0);
						jumpMade = true;
						if(player == 0) {
							possibleState.whiteLosePiece();
						} else {
							possibleState.blackLosePiece();
						}
					}	
				}
			} else {
				//JumpSW
				if (start + 5 > tiles.size() -1 || start + 7 > tiles.size() -1) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 5) == 0 && (tiles.get(start + 5) + 1) % 2 == player) {
						swap(tiles, start + 7, start);
						//take the jumped piece of the board
						jumpMade = true;
						tiles.set(start + 5, 0);
						if(player == 0) {
							possibleState.whiteLosePiece();
						} else {
							possibleState.blackLosePiece();
						}
					}	
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
			start++;
		}
		if(!jumpMade) {
			return childStates;
		}
		//If a jump was made, see if another jump is possible
		return jump(state, childStates, player);
	}
	private static Vector<State> jumpSE(State state, Vector<State> childStates, int player) {
		Vector<Integer> tiles = state.getTiles();
		//Init start/end to moving sides pieces
		int start = 0;
		int end = tiles.size() - 1;
		boolean jumpMade = false;
		
		//Generate states for all pieces of current side
		while(start<end) {
			//Skip blank spaces and pieces not on current side
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 1 ||
					tiles.get(start) % 2 == 1 && player == 0) {
				start++;
				continue;
			}
			//Skip white men since they cannot move backwards
			if(tiles.get(start) == 2 && player == 0) {
				continue;
			}
			State possibleState = new State(state);
			//jump sw = 7 se = 9
			//jump nw = -9 ne = -7
			if(start%4%2 == 0) {
				//JumpSE
				if (start + 5 > tiles.size() -1 || start + 9 > tiles.size() -1) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 5) == 0 && (tiles.get(start + 5) + 1) % 2 == player) {
						swap(tiles, start + 9, start);
						//take the jumped piece of the board
						tiles.set(start + 5, 0);
						jumpMade = true;
						if(player == 0) {
							possibleState.whiteLosePiece();
						} else {
							possibleState.blackLosePiece();
						}
					}	
				}
				
			} else {
				
				//JumpSE
				if (start + 4 > tiles.size() -1 || start + 9 > tiles.size() -1) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 4) == 0 && (tiles.get(start + 4) + 1) % 2 == player) {
						swap(tiles, start + 9, start);
						//take the jumped piece of the board
						tiles.set(start + 4, 0);
						jumpMade = true;
						if(player == 0) {
							possibleState.whiteLosePiece();
						} else {
							possibleState.blackLosePiece();
						}
					}	
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState.isValid()) {
				childStates.add(possibleState);
			}
			start++;
		}
		if(!jumpMade) {
			return childStates;
		}
		//If a jump was made, see if another jump is possible
		return jump(state, childStates, player);
	}
	private static void swap(Vector<Integer> tiles, int a, int b) {
		int temp = tiles.get(a);
		tiles.add(a, tiles.get(b));
		tiles.add(b, temp);
		
	}
	public Vector<Integer> getTiles() {
		return tiles;
	}
	private void blackLosePiece() {
		numBlackPieces--;
	}
	private void whiteLosePiece() {
		numWhitePieces--;
		
	}
}
