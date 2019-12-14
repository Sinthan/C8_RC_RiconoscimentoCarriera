package model;

/*
 * PCDDAOInterface
 * Interface for the PCDDAO that
 * retrieve and manages informations from the PCDDAO
 */
public interface PCDDAOInterface {
	
	
	/*
	 * Retrieve all of PCD information using email as ID
	 */
	public PCD doRetrievePCDByEmail();

}
