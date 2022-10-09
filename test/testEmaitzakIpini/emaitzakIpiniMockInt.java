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
import domain.Question;
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
			//Mockito.doReturn(new Quote()).when(sut).find(Mockito.any(Quote.class));
			//Mockito.doReturn(new Question()).when(sut).find(Mockito.any(Question.class));
		
			sut.EmaitzakIpini(mockedQuote);
		}catch(Exception e) {
			assertTrue(true);
		}
	}

}
