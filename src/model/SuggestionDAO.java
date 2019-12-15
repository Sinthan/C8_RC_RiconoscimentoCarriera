package model;
import model.Suggestion;

	/**
	 * SuggestionDAO is used for communication between Student and DB
	 *
	 */
public class SuggestionDAO implements SuggestionDAOInterface {

	/**
	 * Insert new suggestion  
	 * 
	 * @return -1 if insert failed, 0 if insert ok
	 */
	public int insertSuggestion(Suggestion suggestion) {
		
		int flag = 0;
		return flag;
	}
	
	/**
	 * Retrieve the suggestion through his name      
	 * @return student object
	 */
	public Suggestion doRetrieveSuggestionByName(String univerityName,String examName) {
		
		Suggestion sug = null;
		return sug;
	}
	
	/**
	 * Delete old suggest       
	 * @return -1 if delete failed, 0 if delete ok
	 */
	public int deleteOldSuggestions() {
	
		int flag = 0;
		return flag;	
	}
	
}
