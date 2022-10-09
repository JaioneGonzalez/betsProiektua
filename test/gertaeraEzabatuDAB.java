import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import test.dataAccess.TestDataAccess;

public class gertaeraEzabatuDAB {
	// sut:system under test
	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();

	private Event ev;

	@Test
	public void test1() {
		try {
			// define paramaters
			String eventText = "event1";
			String queryText = "query1";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;

			try {
				oneDate = sdf.parse("01/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}

			ev = ev = testDA.addEventWithQuestion(null, null, null, null, 0, null, null);
			
			Boolean q = sut.gertaeraEzabatu(ev);

			// verify the results
			assertTrue(q == false);

		} catch (Exception e) {
			fail();
		}
	}

}
