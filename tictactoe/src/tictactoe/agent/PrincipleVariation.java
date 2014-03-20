package tictactoe.agent;

import java.util.LinkedList;

public class PrincipleVariation {
	
	float limit;
	
	LinkedList<Actions> principleVariation;

	
	public Actions pop() {
		return principleVariation.pop();
	}
	
	public void push(Actions a) {
		principleVariation.push(a);
	}
	
	/**
	 * @return the limit
	 */
	public float getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(float limit) {
		this.limit = limit;
	}
}
