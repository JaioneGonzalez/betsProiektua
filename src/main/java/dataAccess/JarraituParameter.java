package dataAccess;

import domain.Registered;

public class JarraituParameter {
	public Registered jabea;
	public Registered jarraitua;
	public Double limit;

	public JarraituParameter(Registered jabea, Registered jarraitua, Double limit) {
		this.jabea = jabea;
		this.jarraitua = jarraitua;
		this.limit = limit;
	}
}