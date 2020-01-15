package model;

public interface AdminDAOInterface {

  /**
   * 
   * @param email of admin
   * @param password of admin
   * @return admin object
   */
  public Admin doRetrieveAdmin(String email, String password);
}
