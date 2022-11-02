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
import dataAccess.TestDataAccess;

public class gertaerakSortuDAB {
	
//	static DataAccess dataAccess = new DataAccess();
//	static TestDataAccess testDA = new TestDataAccess();
//	Date eventDate = null;
//	
//	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//	public void cambiarFecha(String fecha) {
//		try {
//			this.eventDate = sdf.parse(fecha);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}  
//		
//	}
//	Event ev1 = null;
//	Sport sport = null;
//	String description = "";
//	Team team1 = null;
//	Team team2 = null;
//	@Before
//	public void init() {
//		Calendar today = Calendar.getInstance();
//		int day = today.get(Calendar.DAY_OF_MONTH);
//		int month=today.get(Calendar.MONTH);
//		month+=1;
//		int year=today.get(Calendar.YEAR);
//		if (month==12) { month=0; year+=1;}
//		cambiarFecha(day+"/"+month+"/"+year);
//		description = "Team1-Team2";
//		String[] teams = description.split("-");
//		team1 = new Team(teams[0]);
//		team2 = new Team(teams[1]);
//		ev1=new Event(1, description, eventDate, team1, team2);
//		sport = new Sport("Futbol");
//		ev1.setSport(sport);
//		testDA.open();
//		testDA.cargarEventoYdeporte(ev1, sport);
//		testDA.close();
//	}
//	
//	@Test
//	public void test1() {
//		// Test con fecha nula deberia de devolver excepcion
//		try {
//			boolean result = dataAccess.gertaerakSortu("", null, "");
//			fail("----------> El test1 de DAB ha fallado");
//		} 
//		catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("----------> El test1 de DAB a funcionado correctamente");
//		}
//	}
//	@Test
//	public void test2(){
//		try {
//			// Test con deporte que no existe deberia devolver false
//			dataAccess.open(false);
//			boolean result = dataAccess.gertaerakSortu("", eventDate, "DeporteQueNoExiste");
//			dataAccess.close();
//			assertFalse(result);
//			
//			System.out.println("----------> El test2 de DAB a funcionado correctamente");
//		} 
//		catch (Exception e) {
//			// TODO: handle exception
//			fail("----------> El test2 de DAB ha fallado: "+e.getMessage());
//		}
//	}
//	@Test
//	public void test3(){
//		try {
//			// Test con desporte disponible pero sin eventos deberia devolver true y anadirlo
//			cambiarFecha("22/10/2025");
//			dataAccess.open(false);
//			boolean result = dataAccess.gertaerakSortu("Equipo1-Equipo2", eventDate, "Futbol");
//			dataAccess.close();
//			testDA.open();
//			boolean expect = testDA.existEvent(ev1);
//			testDA.close();
//			assertEquals(result,expect);
//			System.out.println("----------> El test3 de DAB a funcionado correctamente");
//		} 
//		catch (Exception e) {
//			// TODO: handle exception
//			fail("----------> El test3 de DAB ha fallado: "+e.getMessage());
//		}finally {
//			testDA.open();
//			testDA.removeEvent(new Event(ev1.getEventNumber()+1, "Equipo1-Equipo2", eventDate, team1, team2));
//			testDA.close();
//		}
//	}
//	
//	@Test
//	public void test4(){
//		try {
//			// Test deporte disponible, con eventos ese dia pero sin concidencia
//			
//			dataAccess.open(false);
//			boolean result = dataAccess.gertaerakSortu("Equipo1-Equipo2", eventDate, sport.getIzena());
//			dataAccess.close();
//			assertTrue(result);
//			System.out.println("----------> El test4 de DAB a funcionado correctamente");
//		} 
//		catch (Exception e) {
//			// TODO: handle exception
//			fail("----------> El test4 de DAB ha fallado: "+e.getMessage());
//		}finally {
//			testDA.open();
//			testDA.removeODBEvent(new Event(ev1.getEventNumber()+1, "Equipo1-Equipo2", eventDate, team1, team2));
//			testDA.close();
//		}
//	}
//	
//	@Test
//	public void test5(){
//		try {
//			// Test deporte disponible, con eventos ese dia pero coincide
//			dataAccess.open(false);
//			boolean result = dataAccess.gertaerakSortu(description, eventDate, "Futbol");
//			dataAccess.close();
//			assertFalse(result);
//			System.out.println("----------> El test5 de DAB a funcionado correctamente");
//		} 
//		catch (Exception e) {
//			// TODO: handle exception
//			fail("----------> El test5 de DAB ha fallado: "+e.getMessage());
//		}
//	}
//	@After
//	public void before() {
//		testDA.open();
//		testDA.removeODBEvent(ev1);
//		testDA.removeODBSport(sport);
//		testDA.close();
//	}
	
}