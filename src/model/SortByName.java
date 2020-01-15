package model;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Comparator for the University Class: It compares the attributes "name" of the class ordering the
 * strings by the cities. (It works only if the cities are written all in capital letters - example:
 * MILANO)
 */

public class SortByName implements Comparator<University> {

  Pattern p = Pattern.compile("\\b[A-Z']{2,}\\b");

  @Override
  public int compare(University o1, University o2) {
    // TODO Auto-generated method stub
    String firstName = o1.getName();
    String secondName = o2.getName();
    String firstNameUpper = "", secondNameUpper = "";
    Matcher m = p.matcher(firstName);
    while (m.find())
      firstNameUpper = m.group();
    m = p.matcher(secondName);
    while (m.find())
      secondNameUpper = m.group();
    return firstNameUpper.compareTo(secondNameUpper);
  }

}
