import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Team;
import test.dataAccess.TestDataAccess;

public class gertaeraEzabatuDAW {

	// sut:system under test
	static DataAccess sut = new DataAccess();

	// additional operations needed to execute the test
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
	
	// Evento que aun no ha ocurrido
	@Test
	public void test1() {
		cambiarFecha("21/07/2023");
		String description = "Team1-Team2";
		String[] teams = description.split("-");
		Team team1 = new Team(teams[0]);
		Team team2 = new Team(teams[1]);
		Event ev1 = null;
		try {
			// Anadimos el evento con la pregunta
			testDA.open();
			ev1 = testDA.addEventWithQuestion(1, description, eventDate, "pregunta de prueba", 0, team1, team2);
			testDA.close();
			
			// Intentamos eliminar el evento 
			boolean result = sut.gertaeraEzabatu(ev1);
			
			// El metodo rechaza eliminar un evento que aun no ha ocurrido
			assertFalse(result);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			fail("FAIL" + e.getMessage());
		}finally {
			testDA.open();
			testDA.removeEvent(ev1);
			testDA.close();
		}
	}
	
	// Evento que ya se ha celebrado
	@Test
	public void test2() {
			cambiarFecha("21/07/2022");
			String description = "Team1-Team2";
			String[] teams = description.split("-");
			Team team1 = new Team(teams[0]);
			Team team2 = new Team(teams[1]);
			Event ev1 = null;
			try {
				
				testDA.open();
				ev1 = testDA.addEventWithQuestion(1, description, eventDate, "pregunta de prueba", 0, team1, team2);
				testDA.close();
				
				boolean result = sut.gertaeraEzabatu(ev1);
				
				// Comprobamos si existe dicho metodo
				testDA.open();
				boolean notExpect = testDA.existEvent(ev1);
				testDA.close();
				
				// El result deberia ser true (buena eliminacion) y el notExpect deberia ser false porque no encuentra el metodo
				assertNotEquals(result, notExpect);
				System.out.println("SUCCESS");
				
		} catch (Exception e) {
			fail("FAIL" + e.getMessage());
		}finally {
			testDA.open();
			testDA.removeEvent(ev1);
			testDA.close();
		}
	}
	
}