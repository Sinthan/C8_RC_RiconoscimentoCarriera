package model;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Univerity.
 * Maintains University name.
 */

public class University {
	
	/**
	 *Variables. 
	 * @param name is the name of the University.
	 */
	private String name;

	/**
	 *Empty Constructor. 
	 * 
	 */
	public University() {}

	/**
	 * Constructor. 
	 * @param name is the name of the University.
	 */
	public University(String name) {
		super();
		this.name = name;
	}
	
	/**
	* Get the name of the University.
	* @return the name of the University
	*/
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the University
     * @param name is the name of the University
     * @return void.
	*/
	public void setName(String name) {
		this.name = name;
	}
	
	

}


