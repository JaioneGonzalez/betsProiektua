package gertaerakSortu;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import exceptions.EventFinished;

//@RunWith(MockitoJUnitRunner.Silent.class)
public class gertaerakSortuMockInt {

//	DataAccess dataAccess = Mockito.mock(DataAccess.class);
//	@InjectMocks
//	BLFacade fb = new BLFacadeImplementation(dataAccess);
//	
//	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//	Date eventDate = null;
//	
//	
//	public void cambiarFecha(String fecha) {
//		try {
//			eventDate = sdf.parse(fecha);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void test1() {
//		try {
//			// Fecha nula
//			cambiarFecha("17/11/1999");
//			Mockito.doReturn(false).when(dataAccess).gertaerakSortu(" ", null, " ");
//			boolean result = fb.gertaerakSortu(" ", null, " ");
//			assertFalse(result);
//			fail("----------> El test1 de MOCKITO ha fallado");
//		}catch(Exception e) {
//			System.out.println("----------> El test1 de MOCKITO a funcionado correctamente");
//		}
//	}
//	
//	@Test
//	public void test2() {
//		try {
//			// Deporte no incluido
//			cambiarFecha("17/11/2022");
//			Mockito.doReturn(false).when(dataAccess).gertaerakSortu(" ", eventDate, "PingPong");
//			boolean result = fb.gertaerakSortu(" ", eventDate, "PingPong");
//			assertFalse(result);
//			System.out.println("----------> El test2 de MOCKITO a funcionado correctamente");
//		}catch(Exception e) {
//			fail("----------> El test2 de MOCKITO ha fallado: "+e.getMessage());
//		}
//	}
//	@Test
//	public void test3() {
//		try {
//			//Fecha sin ningun evento
//			cambiarFecha("05/05/2023");
//			
//			Mockito.doReturn(true).when(dataAccess).gertaerakSortu("PartidoNuevo", eventDate, "Futbol");
//			boolean result = fb.gertaerakSortu("PartidoNuevo", eventDate, "Futbol");
//			assertTrue(result);
//			System.out.println("----------> El test3 de MOCKITO a funcionado correctamente");
//		}catch(Exception e) {
//			fail("----------> El test3 de MOCKITO ha fallado: "+e.getMessage());
//		}
//	}
//	@Test
//	public void test4() {
//		try {
//			// Deporte correcto con eventos sin concidencia en la descripcion
//			cambiarFecha("17/11/2022");
//			Mockito.doReturn(true).when(dataAccess).gertaerakSortu("EventoNuevo", eventDate, "Futbol");
//			boolean result = fb.gertaerakSortu("EventoNuevo", eventDate, "Futbol");
//			assertTrue(result);
//			System.out.println("----------> El test4 de MOCKITO a funcionado correctamente");
//		}catch(Exception e) {
//			fail("----------> El test4 de MOCKITO ha fallado: "+e.getMessage());
//		}
//	}
//	@Test
//	public void test5() {
//		try {
//			// Deporte correcto con eventos sin concidencia en la descripcion
//			cambiarFecha("17/11/2022");
//			Mockito.doReturn(true).when(dataAccess).gertaerakSortu("Atletico-Athletic", eventDate, "Futbol");
//			boolean result = fb.gertaerakSortu("Atletico-Athletic", eventDate, "Futbol");
//			assertTrue(result);
//			System.out.println("----------> El test5 de MOCKITO a funcionado correctamente");
//		}catch(Exception e) {
//			fail("----------> El test5 de MOCKITO ha fallado: "+e.getMessage());
//		}
//	}
	
}
