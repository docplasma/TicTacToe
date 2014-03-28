package tictactoe.agent;

import java.util.Vector;

import Engine.JumpPair;

public class State {

	boolean validState;
	int numWhitePieces;
	int numBlackPieces;
	int numWhiteKings;
	int numBlackKings;
	Vector<Integer> tiles;
	Vector<JumpPair> possibleJumpLocations;
	public State() {
		initState();
	}
	public State(State state) {
		validState = true;
		numWhitePieces = state.getNumWhitePieces();
		numBlackPieces = state.getNumBlackPieces();
		numWhiteKings = state.getNumWhiteKings();
		numBlackKings = state.getNumBlackKings();
		tiles = new Vector<Integer>(state.getTiles());
		possibleJumpLocations = new Vector<JumpPair>();
	}
	
	private void initState() {
		validState = true;
		numWhitePieces = 20;
		numBlackPieces = 20;
		numWhiteKings = 0;
		numBlackKings = 0;
		tiles = new Vector<Integer>(32);
		placePieces(0,0,0,31);
		placePieces(0,1, 0, 12); //Starting at pos(0), put 20 white pieces down
		placePieces(20,2, 0, 12);//Starting at pos(25), put 20 black pieces down
	}
	
	private void placePieces(int start, int piece, int a, int b) {
		for (int i = 0; i < b; i++) {
			tiles.add(start, piece);
			start++;
		}
		
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
					if(tiles.get(start - 4) == 0 && (tiles.get(start - 4) + 1) % 2 == player &&
							tiles.get(start - 9) == 0) {
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
				if (start - 5 < 0 || start - 9 < 0) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start - 5) == 0 && (tiles.get(start - 5) + 1) % 2 == player &&
							tiles.get(start - 9) == 0) {
						swap(tiles, start - 9, start);
						//take the jumped piece of the board
						tiles.set(start - 5, 0);
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
			if(possibleState.isValid() && jumpMade) {
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
					if(tiles.get(start - 3) == 0 && (tiles.get(start - 3) + 1) % 2 == player &&
							tiles.get(start - 7) == 0) {
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
				if (start - 4 < 0 || start - 7 < 0) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start - 4) == 0 && (tiles.get(start - 4) + 1) % 2 == player &&
							tiles.get(start - 7) == 0) {
						swap(tiles, start - 7, start);
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
			if(possibleState.isValid() && jumpMade) {
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
				if (start + 4 > tiles.size() -1 || start + 7 > tiles.size() - 1) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 4) == 0 && (tiles.get(start + 4) + 1) % 2 == player &&
							tiles.get(start + 7) == 0) {
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
				if (start + 5 > tiles.size() -1 || start + 7 > tiles.size() - 1) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 5) == 0 && (tiles.get(start + 5) + 1) % 2 == player &&
							tiles.get(start + 7) == 0) {
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
			if(possibleState.isValid() && jumpMade) {
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
				if (start + 5 > tiles.size() -1 || start + 9 > tiles.size() - 1) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 5) == 0 && (tiles.get(start + 5) + 1) % 2 == player &&
							tiles.get(start + 9) == 0) {
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
				if (start + 4 > tiles.size() -1 || start + 9 > tiles.size() - 1) {
					possibleState.setValid(false);
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 4) == 0 && (tiles.get(start + 4) + 1) % 2 == player &&
							tiles.get(start + 9) == 0) {
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
			if(possibleState.isValid() && jumpMade) {
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
	public void movePiece(int selection, int selectionMoveTo) {
		State.swap(tiles, selection, selectionMoveTo);
	}
	private static void swap(Vector<Integer> tiles, int a, int b) {
		int temp = tiles.get(a);
		tiles.add(a, tiles.get(b));
		tiles.add(b, temp);
		
	}
	private void blackLosePiece() {
		numBlackPieces--;
	}
	private void whiteLosePiece() {
		numWhitePieces--;	
	}
	public boolean isValid() {
		return validState;
	}
	public void setValid(boolean val) {
		validState = val;
	}
	public float getHVal() {
		// TODO Auto-generated method stub
		float wPiece = 1;
		float wKing = (float)1.5;
		float wDistance =(float) 0.01;
		float wCornerPiece = (float)0.1;
		
		return wPiece * (numWhitePieces - numBlackPieces) + wKing * (numWhiteKings - numBlackKings) +
				wDistance * getDistanceValue() + wCornerPiece * getNumCornerPieces();
	}
	private float getNumCornerPieces() {
		//Corner Tiles Index = 4, 11, 12, 19, 20
		float value = 0;
		if(tiles.get(4) == 2) {
			value += 1;
		}
		if(tiles.get(11) == 2) {
			value += 1;
		}
		if(tiles.get(12) == 2) {
			value += 1;
		}
		if(tiles.get(19) == 2) {
			value += 1;
		}
		if(tiles.get(20) == 2) {
			value += 1;
		}
		return value;
	}
	private float getDistanceValue() {
		float value = 0;
		for (int t : tiles) {
			if (t == 2) {
				value +=1;
			}
		}
		return value;
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
	public boolean checkValidSelection(int selection) {
		boolean valid = false;
		if(selection == 2 || selection == 4) {
			valid = true;
		} else {
			System.out.println("Please select one of the red pieces.");
		}
		if(jumpPossible()) {
			
		} else {
			valid = true;
		}
		return valid;
	}
	public boolean jumpPossible() {
		boolean valid = false;
		boolean result = false;
		int start = 0;
		int end = tiles.size() - 1;
		
		while(start<end) {
			if(tiles.get(start) == 0 || tiles.get(start) % 2 == 1) {
				start++;
				continue;
			}
			if(start%4%2 == 0) {
				//JumpNW Even
				result = jumpPossibleDirection(-4, -9, start);
				if(result == true) valid = true;
				//JumpNE Even
				result = jumpPossibleDirection(-3, -7, start);
				if(result == true) valid = true;
				//JumpSW Even
				result = jumpPossibleDirection(4, 7, start);
				if(result == true) valid = true;
				//JumpSE Even
				result = jumpPossibleDirection(5, 9, start);
				if(result == true) valid = true;
			} else {
				//JumpNW Odd
				result = jumpPossibleDirection(-5, -9, start);
				if(result == true) valid = true;
				//JumpNE Odd
				result = jumpPossibleDirection(-4, -7, start);
				if(result == true) valid = true;
				//JumpSW Odd
				result = jumpPossibleDirection(5, 7, start);
				if(result == true) valid = true;
				//JumpSE Odd
				result = jumpPossibleDirection(4, 9, start);
				if(result == true) valid = true;
			}
			start++;
		}
		return valid;
	}
	//This method recursively checks if there is a valid jump after the initial jump
	/*private boolean jumpGuanlet(int jumpLocation) {
		if(start%4%2 == 0) {
			//JumpNW Even
			result = jumpPossibleDirection(-4, -9, jumpLocation);
			if(result == true) valid = true;
			//JumpNE Even
			result = jumpPossibleDirection(-3, -7, jumpLocation);
			if(result == true) valid = true;
			//JumpSW Even
			result = jumpPossibleDirection(4, 7, jumpLocation);
			if(result == true) valid = true;
			//JumpSE Even
			result = jumpPossibleDirection(5, 9, jumpLocation);
			if(result == true) valid = true;
		} else {
			//JumpNW Odd
			result = jumpPossibleDirection(-5, -9, jumpLocation);
			if(result == true) valid = true;
			//JumpNE Odd
			result = jumpPossibleDirection(-4, -7, jumpLocation);
			if(result == true) valid = true;
			//JumpSW Odd
			result = jumpPossibleDirection(5, 7, jumpLocation);
			if(result == true) valid = true;
			//JumpSE Odd
			result = jumpPossibleDirection(4, 9, jumpLocation);
			if(result == true) valid = true;
		}
	}*/
	private boolean jumpPossibleDirection(int jumped, int jumpLocation, int start) {
		boolean valid = false;

		//Skip blank spaces and pieces not on current side
		
		//Skip black men since they cannot move backwards
		/*if(tiles.get(start) == 1 && player == 0) {
			continue;
		}*/
		//jump sw = 4/5/7 se = 5/4/9
		//jump nw = -4/-5/-9 ne = -3/-4/-7
	
		//JumpNW Starting on even row
		if (start - jumped < 0 || start - jumpLocation < 0) {
		} else {
			//If the tile that is being jumped is of the opposite player, jump
			if(tiles.get(start - jumped) == 0 && tiles.get(start - jumped) % 2 == 0 &&
					tiles.get(start - jumpLocation) == 0) {
				valid = true;
				possibleJumpLocations.add(new JumpPair(start, jumpLocation));
			}	
		}
		return valid;
	}
	public boolean checkValidMove(int selection, int moveLocation) {
		boolean valid = false;
		JumpPair userMove = new JumpPair(selection, moveLocation);
		for(JumpPair possibleJumps : possibleJumpLocations) {
			JumpPair.compareJumpPair(userMove, possibleJumps);
		}
		return valid;
	}
	/**
	 * @return the validState
	 */
	public boolean isValidState() {
		return validState;
	}
	/**
	 * @return the numWhitePieces
	 */
	public int getNumWhitePieces() {
		return numWhitePieces;
	}
	/**
	 * @return the numBlackPieces
	 */
	public int getNumBlackPieces() {
		return numBlackPieces;
	}
	/**
	 * @return the numWhiteKings
	 */
	public int getNumWhiteKings() {
		return numWhiteKings;
	}
	/**
	 * @return the numBlackKings
	 */
	public int getNumBlackKings() {
		return numBlackKings;
	}
	public Vector<Integer> getTiles() {
		return tiles;
	}
}
