package gertaerakSortu;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doThrow;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import javax.persistence.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import dataAccess.DataAccess;
import domain.Event;
import domain.Sport;
import domain.Team;

@RunWith(MockitoJUnitRunner.Silent.class)
public class gertaerakSortuMockInt {
	
	@InjectMocks
	DataAccess dataAccess;
	@Mock
	EntityManager  db;
	
	/*
	 * Test1 => Comprobar el funcionamiento de db.find(....)
	 * Test2 => Comprobar el funcionamiento de db.createQuery(....) y getResult()
	 * Test3 => Comprobar el funcionamiento de db.persist(....)
	 * Test4 =>
	 */
	
	
	String description;
	String sport;
	SimpleDateFormat sdf;
	Date eventDate;
	
	@Before
	public void init() {
		dataAccess  = new DataAccess();
		
		//Parametros
		description = "description";
		sport = "sport";
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		eventDate = null;
		try {
			eventDate = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test1() {
		try {
			//configure mock
			Mockito.doReturn(null).when(db).find(Mockito.any(), Mockito.anyString());
			//test de prueba
			assertFalse(dataAccess.gertaerakSortu(description, eventDate, sport));
			System.out.println("El test de db.find(....) a funcionado");
			
		}catch(Exception e) {
			fail("Test2 dont run");
		}
	}
	@Test
	public void test2() {
		try {
			TypedQuery<Event> Equery = (TypedQuery<Event>) Mockito.mock(TypedQuery.class);
			List<Event> expected = new ArrayList<>();
			//configure mock
			Mockito.doReturn(Equery).when(db).createQuery(Mockito.anyString(), Mockito.any());
			Mockito.when(Equery.getResultList()).thenReturn(expected);
			//test de prueba
			assertFalse(dataAccess.gertaerakSortu(description, eventDate, sport));
			System.out.println("El test de db.createQuery(....) y getResult() a funcionado");
			
		}catch(Exception e) {
			fail("Test2 dont run");
		}
	}
	
	@Test
	public void test3() {
		try {
			//Param
			Sport spo = new Sport();
			Event Equery = null;
			
			//configure mock
			Mockito.doReturn(spo).when(db).find(Mockito.any(), Mockito.anyString());
			Mockito.doThrow(new IllegalArgumentException()).when(db).createQuery(Mockito.anyString(), Mockito.any());
			//Test de prueba
			boolean result = dataAccess.gertaerakSortu(description, eventDate, sport);
			//Verificacion de metodo void
			
			ArgumentCaptor<Event> eve = ArgumentCaptor.forClass(Event.class);
			Mockito.verify(db, Mockito.times(1)).persist(eve.capture());
			assertTrue(result);
			System.out.println("El test de db.persist(....) a funcionado");
			
		}catch(Exception e) {
			fail("Test3 dont run");
		}
	}
	

}
