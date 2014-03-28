package Engine;

public class JumpPair {
	
	int selection;
	int jumpLocation;
	
	JumpPair() {
		
	}
	public JumpPair(int s, int jL) {
		selection = s;
		jumpLocation = jL;
	}
	public static boolean compareJumpPair(JumpPair pair1, JumpPair pair2) {
		boolean match = false;
		
		if(pair1.getSelection() == pair2.getSelection() &&
				pair1.getJumpLocation() == pair2.getJumpLocation()) {
			match = true;
		}
		
		return match;
	}
	/**
	 * @return the selection
	 */
	public int getSelection() {
		return selection;
	}
	/**
	 * @param selection the selection to set
	 */
	public void setSelection(int selection) {
		this.selection = selection;
	}
	/**
	 * @return the jumpLocation
	 */
	public int getJumpLocation() {
		return jumpLocation;
	}
	/**
	 * @param jumpLocation the jumpLocation to set
	 */
	public void setJumpLocation(int jumpLocation) {
		this.jumpLocation = jumpLocation;
	}
}
