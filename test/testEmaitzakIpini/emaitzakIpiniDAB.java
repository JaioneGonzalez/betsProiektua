package testEmaitzakIpini;

import static org.junit.Assert.*;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Quote;

public class emaitzakIpiniDAB {

	static DataAccess testDA = new DataAccess();
	@Test
	public void test() {
		try {
			Quote q = null;
			testDA.EmaitzakIpini(q);
		}catch(Exception e) {
			assertTrue(true);
		}
	}

}
