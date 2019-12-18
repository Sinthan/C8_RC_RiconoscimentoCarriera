package model;

import java.util.ArrayList;

/**
 * UniversityDAOInterface.
 */

public interface UniversityDAOInterface {

	
	/**
	* @return returns a set of university name.
	*/
	public ArrayList<University> doRetrieveAllUniversity();
}
