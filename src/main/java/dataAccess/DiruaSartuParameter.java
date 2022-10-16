package dataAccess;

import java.util.Date;

import domain.Registered;

public class DiruaSartuParameter {
	public Registered u;
	public Double dirua;
	public Date data;
	public String mota;

	public DiruaSartuParameter(Registered u, Double dirua, Date data, String mota) {
		this.u = u;
		this.dirua = dirua;
		this.data = data;
		this.mota = mota;
	}
}