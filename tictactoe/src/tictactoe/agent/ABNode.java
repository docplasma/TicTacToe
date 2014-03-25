package tictactoe.agent;

import java.util.Vector;

public class ABNode {
	
	State state;
	Vector<ABNode> childNodes;
	ABNode parent;
	
	public ABNode() {

	}

	public ABNode(ABNode abNode, State possibleState) {
		// TODO Auto-generated constructor stub
	}
	private void initChildNodes() {
		childNodes = new Vector<ABNode>();
		for(State s : ((Vector<State>) State.getChildren(state))) {
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
		//TODO Implement hcost generation
		return state.getHVal();
	}
	/**
	 * @return the childNodes
	 */
	public Vector<ABNode> getChildNodes() {
		if (childNodes == null) {
			initChildNodes();
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
}