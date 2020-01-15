package model;

import model.Suggestion;

/**
 * interface for SuggestDAO.
 *
 */
public interface SuggestionDAOInterface {

  public int insertSuggestion(Suggestion suggestion);

  public Suggestion doRetrieveSuggestionByName(String univerityName, String examName);

  // public int deleteOldSuggestions();
  public Suggestion doRetrieveSuggestionByName(String universityName, String examName, int CFU);

  // int updateSuggestion(Suggestion sugg);
}
