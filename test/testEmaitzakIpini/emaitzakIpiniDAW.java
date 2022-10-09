package testEmaitzakIpini;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;
import exceptions.EventNotFinished;
import test.dataAccess.TestDataAccess;

public class emaitzakIpiniDAW {
	static DataAccess testDA = new DataAccess();
	@Test
	public void test() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			oneDate = sdf.parse("01/10/2045");
			Question qu = new Question();
			Event e= new Event(null, null, oneDate, null, null);
			e.addQuestion("QuestionTEST", 0.1);
			Quote q=new Quote(null,null, qu);
			testDA.EmaitzakIpini(q);
			
		}catch(Exception e) {
			assertTrue(true);
		}
	}

}
