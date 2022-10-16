package dataAccess;

import java.util.Vector;

import domain.Quote;
import domain.Registered;

public class ApustuaEginParameter {
	public Registered u;
	public Vector<Quote> quote;
	public Double balioa;
	public Integer apustuBikoitzaGalarazi;

	public ApustuaEginParameter(Registered u, Vector<Quote> quote, Double balioa, Integer apustuBikoitzaGalarazi) {
		this.u = u;
		this.quote = quote;
		this.balioa = balioa;
		this.apustuBikoitzaGalarazi = apustuBikoitzaGalarazi;
	}
}