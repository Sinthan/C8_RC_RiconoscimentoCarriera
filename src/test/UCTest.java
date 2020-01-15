package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.UC;

class UCTest {
	
		UC uc ;
		UC uc2;

	@BeforeEach
	void setUp() throws Exception {
		uc2 = new UC(); 
		uc = new UC("uc@unisa.it","123456789","123456789","123456789");
	}

	@Test
	void testGetSetMail() {
		String mail =uc.getEmail();
		uc.setEmail(mail);
		assertEquals("uc@unisa.it", mail);
	}
	
	@Test
	void testGetSetPass() {
		String pass =uc.getPassword();
		uc.setPassword(pass);
		assertEquals("123456789", pass);
	}
	
	@Test
	void testGetSetTelephone() {
		String telephone =uc.getTelephone();
		uc.setTelephone(telephone);
		assertEquals("123456789", telephone);
	}
	
	@Test
	void testGetSetFax() {
		String fax =uc.getFax();
		uc.setFax(fax);
		assertEquals("123456789", fax);
	}
	
	
	
	

}
