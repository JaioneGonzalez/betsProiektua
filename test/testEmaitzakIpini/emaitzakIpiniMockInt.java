package testEmaitzakIpini;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Quote;

@RunWith(MockitoJUnitRunner.class)
public class emaitzakIpiniMockInt {
	
	DataAccess dataAccess = Mockito.mock(DataAccess.class);
	Quote mockedQuote = Mockito.mock(Quote.class);
	
	@InjectMocks
	BLFacade sut = new BLFacadeImplementation(dataAccess);
	@Test
	public void test() {

		try {
			sut.EmaitzakIpini(mockedQuote);
		}catch(Exception e) {
			
		}
	}

}
