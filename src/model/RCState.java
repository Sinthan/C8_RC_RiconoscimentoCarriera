package model;

enum RCState {
	needsUCValidation,
	isBeingDiscussed,
	approved,
	refused;
	
	public static RCState fromInteger(int x) {
		switch(x) {
		case 0:
			return needsUCValidation;
		case 1:
			return isBeingDiscussed;
		case 2: 
			return approved;
		case 3: 
			return refused;
		}
		System.err.println("Integer");
		return null;
	}
}
