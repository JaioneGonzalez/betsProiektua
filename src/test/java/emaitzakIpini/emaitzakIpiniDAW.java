package emaitzakIpini;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import exceptions.EventNotFinished;
import dataAccess.TestDataAccess;

public class emaitzakIpiniDAW {
	Date eventDate = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	static TestDataAccess testDA = new TestDataAccess();
	static DataAccess dataAccess = new DataAccess();
	
	public void cambiarFecha(String fecha) {
        try {
            eventDate = sdf.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
	Quote quote1, QuotePrueba = null;
	Event ev1, evPrueba = null;
	Sport sp1, spPrueba = null;
	Registered reg1, regPrueba = null;
	ApustuAnitza apA1, apAPrueba = null;
	Apustua ap1, apPrueba = null;
	Question q1, qPrueba = null;
	Team teamPrueba1 = null;
	Team teamPrueba2 = null;
	
	@Before
	public void init() {
		// Parametros del evento
		Calendar today = Calendar.getInstance();
		int day = today.get(Calendar.DAY_OF_MONTH);
		int month=today.get(Calendar.MONTH);
		month+=1;
		int year=today.get(Calendar.YEAR);
		if (month==12) { month=0; year+=1;}
		cambiarFecha(day+"/"+month+"/"+year);
		Team team1= new Team("Atletico");
		Team team2= new Team("Athletic");
		
		ev1=new Event(1, "Atletico-Athletic", eventDate, team1, team2);
		sp1=new Sport("Futbol");
		sp1.addEvent(ev1);
		ev1.setSport(sp1);
		String pregunta1 = "Â¿QuiÃ©n ganarÃ¡ el partido?";
		q1 = new Question(1, pregunta1, 0.5, ev1);
		
		reg1 =new Registered("registered", "123", 1234);
		apA1 = new ApustuAnitza(reg1, 5.0);
		ap1 = new Apustua(apA1, quote1);
		ap1.setKuota(quote1);
		quote1= q1.addQuote(10.0, "Athletic", q1);
		quote1.setQuestion(q1);
		quote1.addApustua(ap1);
		apA1.addApustua(ap1);
		quote1.getApustuak().get(0).setKuota(quote1);
		
		
//		teamPrueba1 = new Team("Atletico");
//		teamPrueba2 = new Team("Athletic");
//		
//		evPrueba = new Event(1, "Atletico-Athletic", eventDate, teamPrueba1, teamPrueba2);
//		spPrueba=new Sport("Futbol");
//		sp1.addEvent(evPrueba);
//		ev1.setSport(spPrueba);
//		qPrueba=new Question(1, "Â¿QuiÃ©n ganarÃ¡ el partido?", 0.5, evPrueba);
//		QuotePrueba=new Quote(10.0, "Athletic", qPrueba);
//		regPrueba = new Registered("registered", "123", 1234);
//		
//		apAPrueba= new ApustuAnitza(regPrueba, 5.0);
//		apPrueba = new Apustua(apAPrueba, QuotePrueba);
//		qPrueba.addQuote(10.0, "Athletic", qPrueba);
//		QuotePrueba.setQuestion(qPrueba);
//		QuotePrueba.addApustua(apPrueba);
//		apAPrueba.addApustua(apPrueba);
	}
	

	
	
	@Test
	//Probando con q que tenga el evento con una fecha que aun no ha pasado
	public void test1() {
		cambiarFecha("20/10/2025");
		quote1.getQuestion().getEvent().setEventDate(eventDate);
		//QuotePrueba.getQuestion().getEvent().setEventDate(eventDate);
		testDA.open();
		testDA.cargarDatosIpini(apA1, q1, ev1, sp1, reg1, quote1, ap1);
		
		testDA.close();
		try {
			dataAccess.open(false);
			dataAccess.EmaitzakIpini(quote1);
			dataAccess.close();
			fail("El test 1 ha fallado");
			
		}catch(EventNotFinished e) {
			System.out.println("El test 1 ha funcionado como deberia");
		}finally{
			testDA.Clear();
		}
	}
	@Test
	//Probando con q que tenga el evento con una fecha que haya pasado
	public void test2() {
		try {
			
			cambiarFecha("20/10/2021");
			quote1.getQuestion().getEvent().setEventDate(eventDate);
			
			
			
			testDA.open();
			testDA.cargarDatosIpini(apA1, q1, ev1, sp1, reg1, quote1, ap1);
			testDA.close();
			
			dataAccess.open(false);
			dataAccess.EmaitzakIpini(quote1);
			dataAccess.close();
			
			
			
			testDA.open();
			ap1 = testDA.getApostua(ap1);
			testDA.close();
			assertEquals(ap1.getEgoera(), "galduta");
			System.out.println("El test 2 ha funcionado como deberia");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			fail("El test 2 ha fallado" + e.getMessage());
			
		}finally {
			testDA.open();
			// testDA.MetodoQueDeshaceElEmaitzakIpini();
			testDA.close();
		}
	}
	@Test
	//Probando con q que no tenga apustuak
	public void test3() {
		try {
			
			
			
			cambiarFecha("20/10/2021");
			quote1.getQuestion().getEvent().setEventDate(eventDate);
			testDA.open();
			testDA.cargarDatosIpini(apA1, q1, ev1, sp1, reg1, quote1, ap1);
			quote1 = testDA.getQuote(quote1);
			quote1.removeApustua(ap1);
			testDA.cargarQuote(quote1);
			testDA.close();
			dataAccess.open(false);
			dataAccess.EmaitzakIpini(quote1);
			dataAccess.close();
			testDA.open();
			ap1 = testDA.getApostua(ap1);
			testDA.close();
			assertEquals(ap1.getEgoera(), "jokoan");
			System.out.println("El test 3 ha funcionado como deberia");
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			fail("El test 3 ha fallado" + e.getMessage());
			
		}finally {
			testDA.open();
			
			// testDA.MetodoQueDeshaceElEmaitzakIpini();
			testDA.close();
		}
	}
	@Test
	//Probando con q cuya su pregunta no tenga quotes
	public void test4() {
		try {
			cambiarFecha("20/10/2021");
			quote1.getQuestion().getEvent().setEventDate(eventDate);
			//borrar el quote de la pregunta
			q1.getQuotes().removeAllElements();
			testDA.open();
			
			testDA.cargarDatosIpini(apA1, q1, ev1, sp1, reg1, quote1, ap1);
			
			testDA.close();
			dataAccess.open(false);
			dataAccess.EmaitzakIpini(quote1);
			dataAccess.close();
			testDA.open();
			apA1 = testDA.getApustuAnitza(apA1);
			testDA.close();
			assertEquals(apA1.getEgoera(),"jokoan");
			System.out.println("El test 4 ha funcionado como deberia");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			fail("El test 4 ha fallado" + e.getMessage());
			
		}finally {
			testDA.open();
			// testDA.MetodoQueDeshaceElEmaitzakIpini();
			testDA.close();
		}
	}
	@After
	public void after() {
		testDA.open();
		testDA.removeODBEvent(ev1);
		testDA.removeODBSport(sp1);
		testDA.removeODBQuote(quote1);
		testDA.removeODBQuestion(q1);
		testDA.removeODBRegistered(reg1);
		testDA.removeODBApustuAnitza(apA1);
		testDA.close();
	}
}
