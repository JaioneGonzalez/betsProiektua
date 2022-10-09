import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import test.dataAccess.TestDataAccess;

public class gertaeraEzabatuDAW {

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

			// configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEventWithQuestion(null, eventText, oneDate, queryText, 0, null, null);
			testDA.close();

			// invoke System Under Test (sut)
			sut.gertaeraEzabatu(ev);

			// if the program continues fail
			fail();
		} catch (Exception e) {
			// if the program goes to this point OK
			// fail();
			assertTrue(true);
		} finally {
			System.out.println("Event deleted");
		}
	}

	
}
