package gertaerakSortu;


import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;

import domain.Event;
import domain.Question;
import domain.Team;
import test.dataAccess.TestDataAccess;

public class gertaerakSortuDAB {
	
	TestDataAccess testDataAccess;
	Event ev;
	@Before
	public void init() {
		testDataAccess = new TestDataAccess();
	}
	@Test
	public void test1() {
		try {
			//Parametros
			int num = 5;
			String description = "description";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date eventDate = null;
			try {
				eventDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String question = "pregunta";
			float qty = 0;
			Team team1 = new Team("Atletico");
			Team team2 = new Team("Athletic");
			ev = testDataAccess.addEventWithQuestion(num, description, eventDate, question, qty, team1, team2);
			Event eventTest = new Event(num, description, eventDate, team1, team2);
			Question q = ev.getQuestions().lastElement();
			//Comprobar que se ha creado el evento
			assertEquals(ev, eventTest);
			//Comprobar que se ha añadido la pregunta al evento
			assertTrue(testDataAccess.existQuestion(ev, q));
			//Comprobar que se ha eliminado de forma correcta el evento
			assertTrue(testDataAccess.removeEvent(ev));
		}catch (Exception e) {
			System.out.println("----------> Fallo test1 DAB: "+e.getMessage());
		}finally{
			testDataAccess.close();
		}
	}
}
