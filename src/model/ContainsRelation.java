package model;

/**
 * Contains.
 * Maintains request and exam id.
 */

public class ContainsRelation {
	
	/**
	 *Variables. 
	 * @param requestRCID is the name of the rc request.
	 * @param examID is the name of the exam.
	 */
	
	private int requestRCID;
	private int examID;
	
	/**
	 *Empty Constructor. 
	 * 
	 */
	public ContainsRelation() {}

	/**
	 * Constructor. 
	 * @param name is the name of the University.
	 */
	public ContainsRelation(int requestRCID, int examID) {
		super();
		this.requestRCID = requestRCID;
		this.examID = examID;
	}

	/**
	* Get the id of the request.
	* @return the id of the RC request.
	*/
	public int getRequestRCID() {
		return requestRCID;
	}

	/**
	 * Set the id of the RC request.
     * @param requestRCID is the id of the RC request.
     * @return void.
	*/
	
	public void setRequestRCID(int requestRCID) {
		this.requestRCID = requestRCID;
	}

	/**
	* Get the id of exam.
	* @return the id of exam.
	*/
	public int getExamID() {
		return examID;
	}

	@Override
	public String toString() {
		return "ContainsRelation [requestRCID=" + requestRCID + ", examID=" + examID + "]";
	}

	/**
	 * Set the id of exam.
     * @param name is the id of exam.
     * @return void.
	*/
	public void setExamID(int examID) {
		this.examID = examID;
	}
	
	
	
	
	
}
