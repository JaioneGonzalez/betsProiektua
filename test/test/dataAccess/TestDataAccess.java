package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Team;
import domain.Transaction;
import exceptions.EventNotFinished;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Event addEventWithQuestion(Integer num, String desc, Date d, String question, float qty, Team lok, Team kanp) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(num, desc, d, lok, kanp);
				    ev.addQuestion(question, qty);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		
		public boolean existQuestion(Event ev,Question q) {
			System.out.println(">> DataAccessTest: existQuestion");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				return e.DoesQuestionExists(q.getQuestion());
			} else 
			return false;
			
		}
		public Event addQuote(Quote q) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    db.persist(q);
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		public void ApustuaIrabazi(ApustuAnitza apustua) {
			ApustuAnitza apustuAnitza = db.find(ApustuAnitza.class, apustua.getApustuAnitzaNumber());
			Registered reg = (Registered) apustuAnitza.getUser();
			Registered r = (Registered) db.find(Registered.class, reg.getUsername());
			db.getTransaction().begin();
			apustuAnitza.setEgoera("irabazita");
			Double d=apustuAnitza.getBalioa();
			for(Apustua ap: apustuAnitza.getApustuak()) {
				d = d*ap.getKuota().getQuote();
			}
			r.updateDiruKontua(d);
			r.setIrabazitakoa(r.getIrabazitakoa()+d);
			r.setZenbat(r.getZenbat()+1);
			Transaction t = new Transaction(r, d, new Date(), "ApustuaIrabazi"); 
			db.persist(t);
			db.getTransaction().commit();
		}
		public void IrabazitakoApustuakMarkatu(Quote q){
			Vector<Apustua> listApustuak = q.getApustuak();
			for(Apustua a : listApustuak) {
				db.getTransaction().begin();
				Boolean bool=a.getApustuAnitza().irabazitaMarkatu();
				db.getTransaction().commit();
				if(bool) {
					this.ApustuaIrabazi(a.getApustuAnitza());
				}
			}
		}
		
		public void EmaitzakIpini(Quote quote) throws EventNotFinished{
			
			Quote q = db.find(Quote.class, quote); 
			String result = q.getForecast();
			
			if(new Date().compareTo(q.getQuestion().getEvent().getEventDate())<0)
				throw new EventNotFinished();

			
			db.getTransaction().begin();
			Question que = q.getQuestion(); 
			Question question = db.find(Question.class, que); 
			question.setResult(result);
			for(Quote quo: question.getQuotes()) {
				for(Apustua apu: quo.getApustuak()) {
					
					Boolean b=apu.galdutaMarkatu(quo);
					if(b) {
						apu.getApustuAnitza().setEgoera("galduta");
					}else {
						apu.setEgoera("irabazita");
					}
				}
			}
			db.getTransaction().commit();
			IrabazitakoApustuakMarkatu(q);
		}
}

