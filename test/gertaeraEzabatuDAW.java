import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Team;
import test.dataAccess.TestDataAccess;

public class gertaeraEzabatuDAW {

	// sut:system under test
	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test
	static TestDataAccess testDA = new TestDataAccess();

	private Event ev;
	Date eventDate;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	public void cambiarFecha(String fecha) {
		try {
			eventDate = sdf.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/*
	// Evento que a�n no ha ocurrido
	@Test
	public void test1() {
		try {
			Team t1 = new Team("t1");
			Team t2 = new Team("t2");
			cambiarFecha("21/07/2023");

			sut.open(true);
			Event ev1 = testDA.addEventWithQuestion(32, " ", eventDate, " ", 0, t1, t2);
			boolean result = sut.gertaeraEzabatu(ev1);
			sut.close();

			assertFalse(result);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			fail("FAIL" + e.getMessage());
		}
	}

	// Evento que ya se ha celebrado
	@Test
	public void test2() {
		try {
			Team t1 = new Team("t1");
			Team t2 = new Team("t2");
			cambiarFecha("21/07/2022");

			sut.open(true);
			Event ev1 = testDA.addEventWithQuestion(32, " ", eventDate, " ", 0, t1, t2);
			ev1.getQuestions().get(0).setResult(" ");
			boolean result = sut.gertaeraEzabatu(ev1);
			sut.close();

			assertTrue(result);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			fail("FAIL" + e.getMessage());
		}
	}
	*/
}