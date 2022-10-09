package gertaerakSortu;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;

@RunWith(MockitoJUnitRunner.class)
public class gertaerakSortuMockInt {
	@Mock
	DataAccess dataAccess;
	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);
	
	
	//Test spo==null
	@Test
	public void test1() {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String description = "Futbol";
			Date eventDate = null;
			try {
				eventDate = sdf.parse("05/10/2022");
			}catch(Exception e) {
				fail("Fail en el parse");
			}
			Mockito.doReturn(null).when(dataAccess).gertaerakSortu(description,eventDate,null);
			
		}catch(Exception e) {
			fail("Test dont run");
		}
	}

}
