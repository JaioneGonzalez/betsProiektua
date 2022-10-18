package testEmaitzakIpini;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import domain.Transaction;
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
			
			testDA.Clear();
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
			testDA.Clear();
			Calendar today = Calendar.getInstance();
			   
			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}
			Team team1= new Team("Atletico");
			Team team2= new Team("Athletic");
			Event ev1=new Event(1, "Atletico-Athletic", UtilDate.newDate(year,month,17), team1, team2);
			Sport sp1=new Sport("Futbol");
			sp1.addEvent(ev1);
			ev1.setSport(sp1);
			Question q1;
			String pregunta1 = "¿Quién ganará el partido?";
			q1=ev1.addQuestion(pregunta1,1);
			Quote quote1 = q1.addQuote(1.3, "1", q1);
			Registered reg1 =new Registered("registered", "123", 1234);
			ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
			Apustua ap1 = new Apustua(apA1, quote1);
			apA1.addApustua(ap1);
			quote1.addApustua(ap1);
			ap1.eguneratuApustuKant(sp1);
			String a = "ApustuaEgin";
			Transaction t1 = new Transaction(reg1, apA1.getBalioa(), new Date(), a);
			reg1.addTransaction(t1);
			team1.addEvent(ev1);
			
			
			testDA.EmaitzakIpini(quote1);
			
			fail("El test 2 ha fallado");
			
		
			
		}catch(Exception e) {
			System.out.println("El test 2 ha funcionado como deberia");
		}finally{
			testDA.Clear();
		}
	}
	@Test
	//Probando con q que tenga el evento con una fecha que aun no ha pasado
	public void test3() {
		
		try {
			testDA.Clear();
			testDA.initializeDB();
			Calendar today = Calendar.getInstance();
			   
			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}
			Team team1= new Team("Atletico");
			Team team2= new Team("Athletic");
			Event ev1=new Event(1, "Atletico-Athletic", UtilDate.newDate(year,month,17), team1, team2);
			Sport sp1=new Sport("Futbol");
			sp1.addEvent(ev1);
			ev1.setSport(sp1);
			Question q1;
			String pregunta1 = "¿Quién ganará el partido?";
			q1=ev1.addQuestion(pregunta1,1);
			Quote quote1 = q1.addQuote(1.3, "1", q1);
			Registered reg1 =new Registered("registered", "123", 1234);
			ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
			Apustua ap1 = new Apustua(apA1, quote1);
			apA1.addApustua(ap1);
			quote1.addApustua(ap1);
			ap1.eguneratuApustuKant(sp1);
			String a = "ApustuaEgin";
			Transaction t1 = new Transaction(reg1, apA1.getBalioa(), new Date(), a);
			reg1.addTransaction(t1);
			team1.addEvent(ev1);
			
			testDA.EmaitzakIpini(quote1);
			
			fail("El test 3 ha fallado");
			
		}catch(Exception e) {
			System.out.println("El test 3 ha funcionado como deberia");
		}finally{
			testDA.Clear();
		}
	}
	@Test
	//Probando con q que tenga el evento con una fecha que aun no ha pasado
	public void test4() {
		
		try {
			testDA.Clear();
			testDA.initializeDB();
			Calendar today = Calendar.getInstance();
			   
			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}
			Team team1= new Team("Atletico");
			Team team2= new Team("Athletic");
			Event ev1=new Event(1, "Atletico-Athletic", UtilDate.newDate(year,month,17), team1, team2);
			Sport sp1=new Sport("Futbol");
			sp1.addEvent(ev1);
			ev1.setSport(sp1);
			Question q1;
			String pregunta1 = "¿Quién ganará el partido?";
			q1=ev1.addQuestion(pregunta1,1);
			Quote quote1 = q1.addQuote(1.3, "1", q1);
			Registered reg1 =new Registered("registered", "123", 1234);
			ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
			Apustua ap1 = new Apustua(apA1, quote1);
			apA1.addApustua(ap1);
			quote1.addApustua(ap1);
			ap1.eguneratuApustuKant(sp1);
			String a = "ApustuaEgin";
			Transaction t1 = new Transaction(reg1, apA1.getBalioa(), new Date(), a);
			reg1.addTransaction(t1);
			team1.addEvent(ev1);
			
			testDA.EmaitzakIpini(quote1);
			
			System.out.println("El test 4 ha funcionado como deberia");
		
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			fail("El test 4 ha fallado" + e.getMessage());
			
		}finally{
			testDA.Clear();
		}
	}
}
