package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.University;
import model.UniversityDAO;

class UniversityDAOTest {

  UniversityDAO uniDAO;
  int result;


  @BeforeEach
  void setUp() throws Exception {
    uniDAO = new UniversityDAO();
  }

  @Test
  void testdoRetrieveAllUniversityOK() {
    ArrayList<University> list = new ArrayList<University>();
    list = uniDAO.doRetrieveAllUniversity();
    if (list.isEmpty()) {
      result = 0;
    } else {
      result = 1;
    }
    assertEquals(1, result);
  }

}
