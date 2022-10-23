import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import dataAccess.DataAccess;
import domain.Event;
import domain.Team;
import test.dataAccess.TestDataAccess;

public class gertaeraEzabatuDAB {
	// sut:system under test
	static DataAccess sut = new DataAccess();

	static DataAccess dataAccess = new DataAccess();
	static TestDataAccess testDA = new TestDataAccess();
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
	// Evento NULL
	@Test
	public void test1() {
		try {
			dataAccess.open(true);
			boolean result = sut.gertaeraEzabatu(null);
			dataAccess.close();
			assertFalse(result);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			System.out.println("FAIL");
		}
	}

	// Evento que a�n no ha ocurrido
	@Test
	public void test2() {
		try {
			Team t1 = new Team("t1");
			Team t2 = new Team("t2");
			cambiarFecha("21/07/2023");

			dataAccess.open(true);
			Event ev1 = testDA.addEventWithQuestion(32, " ", eventDate, " ", 0, t1, t2);
			boolean result = sut.gertaeraEzabatu(ev1);
			dataAccess.close();

			assertFalse(result);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			fail("FAIL" + e.getMessage());
		}
	}

	// Evento que ya ha ocurrido y tiene p�blicos todos los resultados
	@Test
	public void test3() {
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
