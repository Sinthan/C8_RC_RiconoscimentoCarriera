package model;

import java.util.List;

/**
 * UniversityDAOInterface.
 */

public interface UniversityDAOInterface {

	
	/**
	* @return returns a set of university name.
	*/
	public List<University> doRetrieveAllUniversity();
}
