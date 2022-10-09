import static org.junit.Assert.assertTrue;

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

	@Test
	public void test1() {
		try {
			// define paramaters
			String eventText = "event1";
			String queryText = "query1";
			Float betMinimum = new Float(2);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// configure Mock
			Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			Mockito.doReturn(new Event()).when(dataAccess).gertaerakSortu(Mockito.anyString(), Mockito.any(Date.class), Mockito.anyString());

			// invoke System Under Test (sut)
			boolean q = sut.gertaeraEzabatu(mockedEvent);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(true);
		}
	}

	@Test
	public void test2() {
		try {
			// define paramaters
			String eventText = "event1";
			String queryText = "query1";
			Float betMinimum = new Float(2);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}

			// configure Mock
			Event ev = new Event(null, null, null, null, null);
			// invoke System Under Test (sut)
			boolean q = sut.gertaeraEzabatu(ev);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertTrue(true);
		}
	}
}
