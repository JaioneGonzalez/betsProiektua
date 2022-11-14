package Adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;

public class RegisteredAdapter extends AbstractTableModel{
	private Registered register;
	private String[] header = {"Event", "Question", "Event Date", "Bet(€)"};
	private Map<Integer, List<String>> contenido = new HashMap<Integer, List<String>>();
	int i = 0;
	public RegisteredAdapter(Registered register) {
		this.register = register;
		
		for (ApustuAnitza apu:register.getApustuAnitzak()) {
			for (Apustua ap:  apu.getApustuak()) {
				
				Quote quota = ap.getKuota();
				Question question = quota.getQuestion();
				Event eventoNuevo = question.getEvent();
				List<String> fila = new ArrayList<String>();
				fila.add(eventoNuevo.getDescription());
				fila.add(question.getQuestion());
				fila.add(eventoNuevo.getEventDate().toString());
				fila.add(quota.getQuote().toString());
				
				contenido.put(i, fila);
				i++;
			}
		}
	}
	@Override
	public int getRowCount() {
		return i;
	}
	@Override
	public int getColumnCount() {
		return 4;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return contenido.get(rowIndex).get(columnIndex);
	}
	@Override
    public String getColumnName(int column) {
        return header[column];
    }
}