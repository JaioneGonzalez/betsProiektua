package emaitzakIpini;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Team;

@RunWith(MockitoJUnitRunner.class)
public class emaitzakIpiniMockInt {
	
DataAccess dataAccess = Mockito.mock(DataAccess.class);
	
	
	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date eventDate = null;
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
			
			sut.EmaitzakIpini(null);
			ArgumentCaptor<Quote> quoteCaptor = ArgumentCaptor.forClass(Quote.class);
			Mockito.verify(dataAccess, Mockito.times(0)).EmaitzakIpini(quoteCaptor.capture());
			
			System.out.println("El test 1 ha funcionado como deberia");
			
		}catch(Exception e) {
			fail("El metodo ha funcionado cuando no deberia");
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
			
			sut.EmaitzakIpini(quote);
			ArgumentCaptor<Quote> quoteCaptor = ArgumentCaptor.forClass(Quote.class);
			Mockito.verify(dataAccess, Mockito.times(0)).EmaitzakIpini(quoteCaptor.capture());
			
			System.out.println("El test 2 ha funcionado como deberia");
		
			
		}catch(Exception e) {
			fail("El test 2 ha fallado: " + e.getMessage());
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
			
			sut.EmaitzakIpini(quote);
			ArgumentCaptor<Quote> quoteCaptor = ArgumentCaptor.forClass(Quote.class);
			Mockito.verify(dataAccess, Mockito.times(0)).EmaitzakIpini(quoteCaptor.capture());
			
			System.out.println("El test 3 ha funcionado como deberia");
		
			
		}catch(Exception e) {
			fail("El test 3 ha fallado: " + e.getMessage());
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
			Quote quote = new Quote(3.0, "team1", question);
			
			sut.EmaitzakIpini(quote);
			ArgumentCaptor<Quote> quoteCaptor = ArgumentCaptor.forClass(Quote.class);
			Mockito.verify(dataAccess, Mockito.times(1)).EmaitzakIpini(quoteCaptor.capture());
			
			System.out.println("El test 4 ha funcionado como deberia");
		
			
		}catch(Exception e) {
			fail("El test 4 ha fallado: " + e.getMessage());
		}
	}
}
