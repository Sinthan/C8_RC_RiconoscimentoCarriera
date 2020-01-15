package model;

/**
 * Object UC represents a UC employee.
 *
 */


public class UC {

  private String email;
  private String password;
  private String telephone;
  private String fax;


  /**
   * Empty Costructor.
   */

  public UC() {
    
  }

  /**
   * Contructor.
   * 
   * @param email is the address that the UC uses to Log in the site.
   * @param password is the password that the UC uses to Log in the site.
   * @param telehone numbers of uc.
   * @param fax numbers of uc.
   */

  public UC(String email, String password, String telephone, String fax) {

    this.email = email;
    this.password = password;
    this.telephone = telephone;
    this.fax = fax;
  }

  /**
   * getter and setter.
   */

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getTelephone() {
    return telephone;
  }

  public String getFax() {
    return fax;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  @Override
  public String toString() {
    return "UC [email=" + email + ", password=" + password + ", telephone=" + telephone + ", fax="
        + fax + "]";
  }
}
