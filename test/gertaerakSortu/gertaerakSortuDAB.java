package gertaerakSortu;


import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.*;

import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
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
	/*@Test
	public void test1(){
		// Test con evento nulo deberia de devolver excepcion
		try {
			boolean result = dataAccess.gertaerakSortu("", null, "");
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
			dataAccess.open(true);
			boolean result = dataAccess.gertaerakSortu("", eventDate, "DeportQueNoExiste");
			assertFalse(result);
			dataAccess.close();
			System.out.println("----------> El test2 de DAB a funcionado correctamente");
		} 
		catch (Exception e) {
			// TODO: handle exception
			fail("----------> El test2 de DAB ha fallado: "+e.getMessage());
		}
	}*/
	@Test
	public void test3(){
		try {
			// Test con desporte disponible pero sin eventos deberia devolver true y a�adirlo
			cambiarFecha("17/11/2022");
			dataAccess.open(true);
			boolean result = dataAccess.gertaerakSortu("EventoQueNoExiste", eventDate, "Futbol");
			assertTrue(result);
			dataAccess.close();
			System.out.println("----------> El test3 de DAB a funcionado correctamente");
		} 
		catch (Exception e) {
			// TODO: handle exception
			fail("----------> El test3 de DAB ha fallado: "+e.getMessage());
		}
	}
}
