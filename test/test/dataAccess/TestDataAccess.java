package test.dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DiruaSartuParameter;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
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
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {
			Calendar today = Calendar.getInstance();
			   
			int month=today.get(Calendar.MONTH);
			month+=1;
			int year=today.get(Calendar.YEAR);
			if (month==12) { month=0; year+=1;}
			Team team1= new Team("Atletico");
			Team team2= new Team("Athletic");
			Event ev1=new Event(1, "Atletico-Athletic", UtilDate.newDate(year,month,17), team1, team2);
			Sport sp1=new Sport("Futbol");
			sp1.addEvent(ev1);
			ev1.setSport(sp1);
			Question q1;
			String pregunta1 = "¿Quién ganará el partido?";
			q1=ev1.addQuestion(pregunta1,1);
			Quote quote1 = q1.addQuote(1.3, "1", q1);
			Registered reg1 =new Registered("registered", "123", 1234);
			ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
			Apustua ap1 = new Apustua(apA1, quote1);
			apA1.addApustua(ap1);
			quote1.addApustua(ap1);
			ap1.eguneratuApustuKant(sp1);
			String a = "ApustuaEgin";
			Transaction t1 = new Transaction(reg1, apA1.getBalioa(), new Date(), a);
			reg1.addTransaction(t1);
			team1.addEvent(ev1);
			
			db.persist(team1);
			db.persist(apA1);
			db.persist(q1);
			db.persist(ev1);
			db.persist(sp1);
			db.persist(reg1);
			db.persist(quote1);
			db.persist(ap1);
			db.persist(t1);
			db.getTransaction().commit();
			
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public void Clear() {
		db.clear();
	}
	public void DiruaSartu(DiruaSartuParameter parameterObject) {
		Registered user = (Registered) db.find(Registered.class, parameterObject.u.getUsername()); 
		db.getTransaction().begin();
		Transaction t = new Transaction(user, parameterObject.dirua, parameterObject.data, parameterObject.mota); 
		System.out.println(t.getMota());
		user.addTransaction(t);
		user.updateDiruKontua(parameterObject.dirua);
		db.persist(t);
		db.getTransaction().commit();
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

