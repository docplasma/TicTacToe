package tictactoe.agent;

import java.util.Comparator;
import java.util.Vector;

public class ABNode implements Comparator {
	
	State state;
	Vector<ABNode> childNodes;
	ABNode parent;
	float moveValue;
	
	public ABNode() {

	}

	public ABNode(ABNode abNode, State possibleState) {
		parent = abNode;
		state = possibleState;
	}
	private void initChildNodes(int player) {
		childNodes = new Vector<ABNode>();
		for(State s : ((Vector<State>) State.getChildren(state, player))) {
				childNodes.add(new ABNode(this, s));
		}
	}
	/**
	 * @return the terminal
	 */
	public Boolean isTerminal() {
		return state.isGoalState();
	}
	/**
	 * @return the hCost
	 */
	public float getHVal() {
		return state.getHVal();
	}
	/**
	 * @return the childNodes
	 */
	public Vector<ABNode> getChildNodes(int player) {
		if (childNodes == null) {
			initChildNodes(player);
		}
		return childNodes;
	}
	/**
	 * @param childNodes the childNodes to set
	 */
	public void setChildNodes(Vector<ABNode> childNodes) {
		this.childNodes = childNodes;
	}
	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * @return the parent
	 */
	public ABNode getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(ABNode parent) {
		this.parent = parent;
	}

	/**
	 * @return the moveValue
	 */
	public float getMoveValue() {
		return moveValue;
	}

	/**
	 * @param moveValue the moveValue to set
	 */
	public void setMoveValue(float moveValue) {
		this.moveValue = moveValue;
	}

	@Override
	public int compare(Object node1, Object node2) {
		if(((ABNode) node1).getMoveValue() < ((ABNode) node2).getMoveValue()) {
			return -1;
		} else if(((ABNode) node1).getMoveValue() > ((ABNode) node2).getMoveValue()) {
			return 1;
		}
		
		return 0;
	}
}