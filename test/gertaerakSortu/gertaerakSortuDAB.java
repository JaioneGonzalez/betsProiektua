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
	
	DataAccess dataAccess = new DataAccess();
	Event ev;
	Date eventDate;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	/*
	 * Test1 => Comprobar spo == null
	 * Test2 => Comprobar event concuerde con la descripcion
	 * Test3 => Comprobar event sea completamente nuevo y haya que anadirlo
	 */
	public void cambiarFecha(String fecha) {
		try {
			eventDate = sdf.parse(fecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test1() {
		try {
			cambiarFecha("17/11/2022");
			boolean result = dataAccess.gertaerakSortu("NuevoEvento", eventDate, "deporteQueNoExiste");
			assertFalse(result);
			System.out.println("----------> El test1 de DAB a funcionado correctamente");
		}catch (Exception e) {
			System.out.println("----------> Fallo test1 DAB: "+e.getMessage());
		}finally{
			dataAccess.close();
		}
	}
	
	@Test
	public void test2() {
		try {
			cambiarFecha("17/11/2022");
			boolean result = dataAccess.gertaerakSortu("Atletico-Athletic", eventDate, "Futbol");
			assertFalse(result);
			System.out.println("----------> El test2 de DAB a funcionado correctamente");
		}catch (Exception e) {
			System.out.println("----------> Fallo test2 DAB: "+e.getMessage());
		}finally{
			dataAccess.close();
		}
	}
	@Test
	public void test3() {
		try {
			cambiarFecha("20/05/2023");
			boolean result = dataAccess.gertaerakSortu("Atletico-Athletic", eventDate, "Futbol");
			assertTrue(result);
			System.out.println("----------> El test3 de DAB a funcionado correctamente");
		}catch (Exception e) {
			System.out.println("----------> Fallo test3 DAB: "+e.getMessage());
		}finally{
			dataAccess.close();
		}
	}
}
