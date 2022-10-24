package gertaeraEzabatu;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;

@RunWith(MockitoJUnitRunner.class)
public class GertaeraEzabatuMockInt {
	DataAccess dataAccess = Mockito.mock(DataAccess.class);
	Event mockedEvent = Mockito.mock(Event.class);

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
		
	// Evento NULL
	@Test
	public void test1() {
		try {
			Mockito.doReturn(false).when(dataAccess).gertaeraEzabatu(null);
			boolean result = sut.gertaeraEzabatu(null);
			assertFalse(result);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			System.out.println("FAIL");
		}
	}
	
	// Evento que aún no ha ocurrido
	@Test
	public void test2() {
		try {
			Event ev = new Event();
			cambiarFecha("21/07/2023");
			ev.setEventDate(eventDate);
			Mockito.doReturn(false).when(dataAccess).gertaeraEzabatu(ev);
			boolean result = sut.gertaeraEzabatu(ev);
			assertFalse(result);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			fail("FAIL" + e.getMessage());
		}
	}
	
	// Evento que ya ha ocurrido y tiene públicos todos los resultados
	@Test
	public void test3() {
		try {
			Event ev = new Event();
			cambiarFecha("21/07/2022");
			ev.setEventDate(eventDate);
			Mockito.doReturn(true).when(dataAccess).gertaeraEzabatu(ev);
			boolean result = sut.gertaeraEzabatu(ev);
			assertTrue(result);
			System.out.println("SUCCESS");
		} catch (Exception e) {
			fail("FAIL" + e.getMessage());
		}
	}
	
	
}
