package test;

import static org.junit.jupiter.api.Assertions.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import model.Student;

class StudentRCRequestRedirectorTest extends Mockito {
	
	HttpServletRequest request;
	HttpServletResponse response;
	HttpSession sessione;
	RequestDispatcher requestDispatcher;
	Student s;

	@BeforeEach
	void setUp() throws Exception {
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		
		
		
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
