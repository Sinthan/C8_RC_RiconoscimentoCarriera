package model;

import java.util.ArrayList;

/**
 * ContainsRelationInterface.
 */

public interface ContainsRelationDAOInterface {
	
	/**
	* @return returns a status.
	*/
	public int insertContainsRelation(ContainsRelation conRel);
	
	/**
	* @param idExam is the id of te exam
	* @return returns a set of Contains for an exam.
	*/
	public ArrayList<ContainsRelation> doRetrieveAllContainsRelationByIDExam(int idExam);

}
