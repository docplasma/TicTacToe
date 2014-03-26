package tictactoe.agent;

public class Agent {
	
	//PrincipleVariation is the bundled limit and path to solution
	/**
	 * 
	 * @author Dexter Parks
	 * @version 1.0
	 * @param node is the starting point of the search
	 * @param depth is the maximum depth of recursion
	 * @param a is the smallest hvalue (lower limit)
	 * @param b is the largest hvalue (upper limit)
	 * @param maximizingPlayer, true if trying to pick the lower limit
	 * @return Returns the estimated HValue of a certain node
	 */
	float abSearch(ABNode node, int depth, float a, float b, Boolean maximizingPlayer) {
		if(depth == 0 || node.isTerminal()) {
			return node.getHVal();
		}
		
		if (maximizingPlayer) {
			for(ABNode child : node.getChildNodes(1)) { //Each child node
				a = Math.max(a, abSearch(child, depth -1, a, b, false));
				if(b <= a) {
					break; //B cut-off
				}
				return a;
			}
		} else {
			for(ABNode child : node.getChildNodes(0))	{ //Each child node
				b = Math.min(b, abSearch(child, depth - 1, a, b, true));
				if (b <= a) {
					break; //a cut-off
				}
				return b;
			}
		}
		return -1;
	}
}
//abSearch(origin, depth, -infinity, +infinity, true)