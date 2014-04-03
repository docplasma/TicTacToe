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
	Vector<JumpPair> possibleMoveLocations;
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
	}
	
	private void initState() {
		validState = true;
		numWhitePieces = 20;
		numBlackPieces = 20;
		numWhiteKings = 0;
		numBlackKings = 0;
		tiles = new Vector<Integer>(32);
		placePieces(0,0,0,31);
		placePieces(0,1, 0, 12); //Starting at pos(0), put 20 black pieces down
		placePieces(20,2, 0, 12);//Starting at pos(25), put 20 white pieces down
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
		//Generate states for all pieces of current side
		while(start<end) {
			//Skip blank spaces and pieces not on current side+
			//Computer is 0, and has odd pieces
			if(tiles.get(start) == 0 ||
					tiles.get(start) % 2 == 0 && player == 0 ||
					tiles.get(start) % 2 == 1 && player == 1) {
				start++;
				continue;
			}
			//Skip black men since they cannot move backwards
			if(tiles.get(start) == 1 && player == 0) {
				start++;
				continue;
			}
			//Left corner pieces can't move NW
			if(start == 4 || start == 12 || start == 20 || start == 28) {
				start++;
				continue;
			}
			State possibleState = null;
			if(start%4%2 == 0) {
				if (start - 4 < 0) {
					start++;
					continue;
				} else {
					if(tiles.get(start - 4) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start - 4, start);
					} else {
						start++;
						continue;
					}
				}
				
			} else {
				if (start - 5 < 0) {
					start++;
					continue;
				} else {
					if(tiles.get(start - 5) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start - 5, start);
					} else {
						start++;
						continue;
					}
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
					tiles.get(start) % 2 == 0 && player == 0 ||
					tiles.get(start) % 2 == 1 && player == 1) {
				start++;
				continue;
			}
			//Skip black men since they cannot move backwards
			if(tiles.get(start) == 1 && player == 0) {
				start++;
				continue;
			}
			//Right corner pieces can't move NE
			if(start == 3 || start == 11 || start == 19 || start == 27) {
				start++;
				continue;
			}
			State possibleState = null;
			if(start%4%2 == 0) {
				if (start - 3 < 0) {
					start++;
					continue;
				} else {
					if(tiles.get(start - 3) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start - 3, start);
					} else {
						start++;
						continue;
					}
				}
				
			} else {
				if (start - 4 < 0) {
					start++;
					continue;
				} else {
					if(tiles.get(start - 4) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start - 4, start);
					} else {
						start++;
						continue;
					}
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
					tiles.get(start) % 2 == 0 && player == 0 ||
					tiles.get(start) % 2 == 1 && player == 1) {
				start++;
				continue;
			}
			//Skip white men since they cannot move backwards
			if(tiles.get(start) == 2 && player == 1) {
				start++;
				continue;
			}

			//Left corner pieces can't move SW
			if(start == 4 || start == 12 || start == 20 || start == 28) {
				start++;
				continue;
			}
			State possibleState = null;
			if(start%4%2 == 0) {
				if (start + 4 > tiles.size() - 1) {
					start++;
					continue;
				} else {
					if(tiles.get(start + 4) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start + 4, start);
					} else {
						start++;
						continue;
					}
				}
				
			} else {
				if (start + 3 > tiles.size() - 1) {
					start++;
					continue;
				} else {
					if(tiles.get(start + 3) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start + 5, start);
					} else {
						start++;
						continue;
					}
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
					tiles.get(start) % 2 == 0 && player == 0 ||
					tiles.get(start) % 2 == 1 && player == 1) {
				start++;
				continue;
			}
			//Skip white men since they cannot move backwards
			if(tiles.get(start) == 2 && player == 1) {
				start++;
				continue;
			}
			//Right corner pieces can't move NE
			if(start == 3 || start == 11 || start == 19 || start == 27) {
				start++;
				continue;
			}
			State possibleState = null;
			if(start%4%2 == 0) {
				if (start + 5 > tiles.size() - 1) {
					start++;
					continue;
				} else {
					if(tiles.get(start + 5) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start + 5, start);
					} else {
						start++;
						continue;
					}
				}
				
			} else {
				if (start + 4 > tiles.size() - 1) {
					start++;
					continue;
				} else {
					if(tiles.get(start + 4) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start + 4, start);
					} else {
						start++;
						continue;
					}
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
					tiles.get(start) % 2 == 0 && player == 0 ||
					tiles.get(start) % 2 == 1 && player == 1) {
				start++;
				continue;
			}
			//Skip black men since they cannot move backwards
			if(tiles.get(start) == 1 && player == 0) {
				start++;
				continue;
			}
			//Right corner pieces can't jump NW
			if(start == 0 || start == 4|| start == 8 || start == 12 || start == 16 || start == 20 || start == 24 || start == 28) {
				start++;
				continue;
			}
			State possibleState = null;
			//jump sw = 7 se = 9
			//jump nw = -9 ne = -7
			if(start%4%2 == 0) {
				//JumpNW Starting on even row
				if (start - 4 < 0 || start - 9 < 0) {
					start++;
					continue;
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start - 4) == 0 && (tiles.get(start - 4) + 1) % 2 == player &&
							tiles.get(start - 9) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start - 9, start);
						//take the jumped piece of the board
						possibleState.getTiles().set(start - 4, 0);
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
					start++;
					continue;
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start - 5) == 0 && (tiles.get(start - 5) + 1) % 2 == player &&
							tiles.get(start - 9) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start - 9, start);
						//take the jumped piece of the board
						possibleState.getTiles().set(start - 5, 0);
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
			if(possibleState != null && possibleState.isValid() && jumpMade) {
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
					tiles.get(start) % 2 == 0 && player == 0 ||
					tiles.get(start) % 2 == 1 && player == 1) {
				start++;
				continue;
			}
			//Skip black men since they cannot move backwards
			if(tiles.get(start) == 1 && player == 0) {
				start++;
				continue;
			}
			//Right corner pieces can't jump NE
			if(start == 3 || start == 7|| start == 11 || start == 15 || start == 19 || start == 23 || start == 27 || start == 31) {
				start++;
				continue;
			}
			State possibleState = null;
			//jump sw = 7 se = 9
			//jump nw = -9 ne = -7
			if(start%4%2 == 0) {
				//JumpNE
				if (start - 3 < 0 || start - 7 < 0) {
					start++;
					continue;
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start - 3) == 0 && (tiles.get(start - 3) + 1) % 2 == player &&
							tiles.get(start - 7) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start - 7, start);
						//take the jumped piece of the board
						possibleState.getTiles().set(start - 3, 0);
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
					start++;
					continue;
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start - 4) == 0 && (tiles.get(start - 4) + 1) % 2 == player &&
							tiles.get(start - 7) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start - 7, start);
						//take the jumped piece of the board
						possibleState.getTiles().set(start - 4, 0);
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
			if(possibleState != null && possibleState.isValid() && jumpMade) {
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
					tiles.get(start) % 2 == 0 && player == 0 ||
					tiles.get(start) % 2 == 1 && player == 1) {
				start++;
				continue;
			}
			//Skip white men since they cannot move backwards
			if(tiles.get(start) == 2 && player == 0) {
				start++;
				continue;
			}
			//Right corner pieces can't jump SW
			if(start == 0 || start == 4|| start == 8 || start == 12 || start == 16 || start == 20 || start == 24 || start == 28) {
				start++;
				continue;
			}
			State possibleState = null;
			//jump sw = 7 se = 9
			//jump nw = -9 ne = -7
			if(start%4%2 == 0) {
				//JumpSW
				if (start + 4 > tiles.size() -1 || start + 7 > tiles.size() - 1) {
					start++;
					continue;
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 4) == 0 && (tiles.get(start + 4) + 1) % 2 == player &&
							tiles.get(start + 7) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start + 7, start);
						//take the jumped piece of the board
						possibleState.getTiles().set(start + 4, 0);
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
					start++;
					continue;
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 5) == 0 && (tiles.get(start + 5) + 1) % 2 == player &&
							tiles.get(start + 7) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start + 7, start);
						//take the jumped piece of the board
						jumpMade = true;
						possibleState.getTiles().set(start + 5, 0);
						if(player == 0) {
							possibleState.whiteLosePiece();
						} else {
							possibleState.blackLosePiece();
						}
					}	
				}
			}
			//If the move was actually possible, add to list of states
			if(possibleState != null && possibleState.isValid() && jumpMade) {
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
					tiles.get(start) % 2 == 0 && player == 0 ||
					tiles.get(start) % 2 == 1 && player == 1) {
				start++;
				continue;
			}
			//Skip white men since they cannot move backwards
			if(tiles.get(start) == 2 && player == 0) {
				start++;
				continue;
			}
			//Right corner pieces can't jump NE
			if(start == 3 || start == 7|| start == 11 || start == 15 || start == 19 || start == 23 || start == 27 || start == 31) {
				start++;
				continue;
			}
			State possibleState = null;
			//jump sw = 7 se = 9
			//jump nw = -9 ne = -7
			if(start%4%2 == 0) {
				//JumpSE
				if (start + 5 > tiles.size() -1 || start + 9 > tiles.size() - 1) {
					start++;
					continue;
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 5) == 0 && (tiles.get(start + 5) + 1) % 2 == player &&
							tiles.get(start + 9) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start + 9, start);
						//take the jumped piece of the board
						possibleState.getTiles().set(start + 5, 0);
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
					start++;
					continue;
				} else {
					//If the tile that is being jumped is of the opposite player, jump
					if(tiles.get(start + 4) == 0 && (tiles.get(start + 4) + 1) % 2 == player &&
							tiles.get(start + 9) == 0) {
						possibleState = new State(state);
						swap(possibleState.getTiles(), start + 9, start);
						//take the jumped piece of the board
						possibleState.getTiles().set(start + 4, 0);
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
			if(possibleState != null && possibleState.isValid() && jumpMade) {
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
		tiles.set(a, tiles.get(b));
		tiles.set(b, temp);
		
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
		if(tiles.get(selection) == 2 || tiles.get(selection) == 4) {
			valid = true;
		} else {
			System.out.println("Please select one of the red pieces.");
		}
		if(jumpPossible()) {
			for(JumpPair possibleJumps : possibleJumpLocations) {
				if(selection == possibleJumps.getSelection()) {
					valid = true;
				}
			}
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
		possibleJumpLocations = new Vector<JumpPair>();
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
	private boolean jumpPossibleDirection(int jumped, int jumpLocationOffset, int start) {
		boolean valid = false;

		//Skip blank spaces and pieces not on current side
		
		//Skip black men since they cannot move backwards
		/*if(tiles.get(start) == 1 && player == 0) {
			continue;
		}*/
		//jump sw = 4/5/7 se = 5/4/9
		//jump nw = -4/-5/-9 ne = -3/-4/-7
	
		//JumpNW Starting on even row
		if (start + jumped < 0 || start + jumpLocationOffset > tiles.size()) {
		} else {
			//If the tile that is being jumped is of the opposite player, jump
			if(tiles.get(start + jumped) == 0 && tiles.get(start + jumped) % 2 == 0 &&
					tiles.get(start + jumpLocationOffset) == 0) {
				valid = true;
				JumpPair pair = new JumpPair(start, start + jumpLocationOffset);
				possibleJumpLocations.add(pair);
			}	
		}
		return valid;
	}
	public boolean checkValidMove(int selection, int moveLocation) {
		boolean valid = false;
		JumpPair userMove = new JumpPair(selection, moveLocation);
		getValidMoveList(selection);
		for(JumpPair possibleMoves : possibleMoveLocations) {
			if(JumpPair.compareJumpPair(userMove, possibleMoves)) {
				valid = true;
			}
		}
		for(JumpPair possibleJumps : possibleJumpLocations) {
			if(JumpPair.compareJumpPair(userMove, possibleJumps)) {
				valid = true;
			}
		}
		return valid;
	}
	public boolean getValidMoveList(int selection) {
		boolean valid = false;
		boolean result = false;
		int start = 0;
		int end = tiles.size() - 1;
		possibleMoveLocations = new Vector<JumpPair>();
		while(start<end) {
			if(tiles.get(start) == 0 || tiles.get(start) % 2 == 1) {
				start++;
				continue;
			}
			//jump sw = 4/5/7 se = 5/4/9
			//jump nw = -4/-5/-9 ne = -3/-4/-7
			if(start%4%2 == 0) {
				//JumpNW Even
				result = possibleMoveDirections(selection, -4);
				if(result == true) valid = true;
				//JumpNE Even
				result = possibleMoveDirections(selection, -3);
				if(result == true) valid = true;
				//JumpSW Even
				result = possibleMoveDirections(selection, 4);
				if(result == true) valid = true;
				//JumpSE Even
				result = possibleMoveDirections(selection, 5);
				if(result == true) valid = true;
			} else {
				//JumpNW Odd
				result = possibleMoveDirections(selection, -5);
				if(result == true) valid = true;
				//JumpNE Odd
				result = possibleMoveDirections(selection, -4);
				if(result == true) valid = true;
				//JumpSW Odd
				result = possibleMoveDirections(selection, 5);
				if(result == true) valid = true;
				//JumpSE Odd
				result = possibleMoveDirections(selection, 4);
				if(result == true) valid = true;
			}
			start++;
		}
		return valid;
	}
	private boolean possibleMoveDirections(int selection, int moveOffset) {
		boolean valid = false;
		//jump sw = 4/5/7 se = 5/4/9
		//jump nw = -4/-5/-9 ne = -3/-4/-7
	
		//JumpNW Starting on even row
		if (selection + moveOffset < 0 || selection + moveOffset > tiles.size()) {
		} else {
			//If the tile that is being jumped is of the opposite player, jump
			if(tiles.get(selection + moveOffset) == 0) {
				valid = true;
				JumpPair pair = new JumpPair(selection, selection + moveOffset);
				possibleMoveLocations.add(pair);
			}	
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
