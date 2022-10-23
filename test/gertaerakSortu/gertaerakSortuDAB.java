package gertaerakSortu;


import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.*;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Sport;
import domain.Team;
import test.dataAccess.TestDataAccess;

public class gertaerakSortuDAB {
	
	static DataAccess dataAccess = new DataAccess();
	static TestDataAccess testDA = new TestDataAccess();
	Date eventDate = null;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	public void cambiarFecha(String fecha) {
		try {
			eventDate = sdf.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		
	}
	
	@Test
	public void test1(){
		// Test con evento nulo deberia de devolver excepcion
		try {
			testDA.open();
			boolean result = testDA.gertaerakSortu("", null, "");
			testDA.close();
			assertFalse(result);
			fail("----------> El test1 de DAB ha fallado");
		} 
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("----------> El test1 de DAB a funcionado correctamente");
		}
	}
	@Test
	public void test2(){
		try {
			// Test con deporte que no existe deberia devolver false
			cambiarFecha("17/11/2022");
			testDA.open();
			boolean result = testDA.gertaerakSortu("", eventDate, "DeporteQueNoExiste");
			testDA.close();
			assertFalse(result);
			
			System.out.println("----------> El test2 de DAB a funcionado correctamente");
		} 
		catch (Exception e) {
			// TODO: handle exception
			fail("----------> El test2 de DAB ha fallado: "+e.getMessage());
		}
	}
	@Test
	public void test3(){
		cambiarFecha("17/11/2022");
		String description = "Team1-Team2";
		String[] teams = description.split("-");
		Team team1 = new Team(teams[0]);
		Team team2 = new Team(teams[1]);
		Event ev1=new Event(1, "T", eventDate, team1, team2);
		try {
			// Test con desporte disponible pero sin eventos deberia devolver true y a�adirlo
			
			
			boolean result = dataAccess.gertaerakSortu("Team1-Team2", eventDate, "Futbol");
			testDA.open();
			boolean expect = testDA.existEvent(ev1);
			testDA.close();
			assertEquals(result,expect);
			System.out.println("----------> El test3 de DAB a funcionado correctamente");
		} 
		catch (Exception e) {
			// TODO: handle exception
			fail("----------> El test3 de DAB ha fallado: "+e.getMessage());
		}
		finally {
			testDA.open();
			testDA.removeEvent(ev1);
			testDA.close();
		}
	}
}
