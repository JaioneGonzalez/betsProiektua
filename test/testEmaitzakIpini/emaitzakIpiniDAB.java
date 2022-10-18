package testEmaitzakIpini;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Team;
import test.dataAccess.TestDataAccess;

public class emaitzakIpiniDAB {
	
	Date eventDate = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	static TestDataAccess testDA = new TestDataAccess();
	
	public void cambiarFecha(String fecha) {
        try {
            eventDate = sdf.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
	@Test
	//Probando con q==null
	public void test1() {
		
		try {
			
			
			testDA.EmaitzakIpini(null);
			fail("El metodo ha funcionado cuando no deberia");
			
		}catch(Exception e) {
			
			System.out.println("El test 1 ha funcionado como deberia");
		}
	}
	
	@Test
	//Probando con q que no este en la base de datos
	public void test2() {
		
		try {
			Team team1 = new Team("team1"); 
			Team team2 = new Team("team2"); 
			String descripcion = "partidazo";
			
			cambiarFecha("01/01/2025");
			Event event = new Event("description",eventDate, team1, team2);

			Question question = new Question(2,"", 0.3, event);
			Quote quote = new Quote(3.0, "equipo no existente", question);
			testDA.open();
			testDA.EmaitzakIpini(quote);
			testDA.close();
			fail("El test 2 ha fallado");
			
		
			
		}catch(Exception e) {
			System.out.println("El test 2 ha funcionado como deberia");
		}
	}
	@Test
	//Probando con q que tenga el evento con una fecha que aun no ha pasado
	public void test3() {
		
		try {
			Team team1 = new Team("team1"); 
			Team team2 = new Team("team2"); 
			String descripcion = "partidazo";
			
			cambiarFecha("01/01/2025");
			Event event = new Event("description",eventDate, team1, team2);

			Question question = new Question(2,"", 0.3, event);
			Quote quote = new Quote(3.0, "team1", question);
			testDA.open();
			testDA.addQuote(quote);
			testDA.EmaitzakIpini(quote);
			testDA.close();
			fail("El test 3 ha fallado");
			
		}catch(Exception e) {
			System.out.println("El test 3 ha funcionado como deberia");
		}
	}
	@Test
	//Probando con q que tenga el evento con una fecha que aun no ha pasado
	public void test4() {
		
		try {
			Team team1 = new Team("team1"); 
			Team team2 = new Team("team2"); 
			String descripcion = "partidazo";
			
			cambiarFecha("01/01/2021");
			Event event = new Event("description",eventDate, team1, team2);

			Question question = new Question(2,"", 0.3, event);
			
			Quote quote = question.addQuote(1.3, "1", question);
			
			testDA.open();
			
			testDA.EmaitzakIpini(quote);
			testDA.close();
			System.out.println("El test 4 ha funcionado como deberia");
		
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			fail("El test 4 ha fallado" + e.getMessage());
			
		}
	}
}
