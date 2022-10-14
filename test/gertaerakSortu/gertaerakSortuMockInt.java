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

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Sport;
import domain.Team;

@RunWith(MockitoJUnitRunner.Silent.class)
public class gertaerakSortuMockInt {
	
	@InjectMocks
	BLFacadeImplementation fb;
	
	@Mock
	DataAccess dataAccess = new DataAccess();
	
	
	/*
	 * Test1 => Comprobar el funcionamiento de db.find(....)
	 */
	
	SimpleDateFormat sdf;
	Date eventDate;
	
	
	@Before
	public void init() {
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		eventDate = null;
		try {
			eventDate = sdf.parse("17/11/2022");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test1() {
		try {
			fb.gertaerakSortu("Atletico-Athletic", eventDate, "deporteQueNoExiste");
			//configure mock
			Mockito.verify(dataAccess, Mockito.times(1)).open(true);
			Mockito.verify(dataAccess, Mockito.times(1)).initializeDB();
			Mockito.verify(dataAccess, Mockito.times(2)).close();
			//test de prueba
			assertFalse(dataAccess.gertaerakSortu("Atletico-Athletic", eventDate, "deporteQueNoExiste"));
			System.out.println("----------> El test1 de Mockito a funcionado correctamente");
			
		}catch(Exception e) {
			System.out.println("----------> Fallo test1 MOCKINT: "+e.getMessage());
		}
	}
	
}
