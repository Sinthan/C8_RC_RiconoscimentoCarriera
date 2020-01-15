package model;

/**
 * Interface for UCDAO.
 */
import model.UC;

public interface UCDAOInterface {
  /**
   * 
   * @param email of uc
   * @param password of uc
   * @return Uc object
   */
  public UC doRetrieveUc(String email, String password);


}
