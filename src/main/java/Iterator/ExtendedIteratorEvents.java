package Iterator;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import domain.Event;

public class ExtendedIteratorEvents implements ExtendedIterator {
	private Vector<Event> eventos;
	private int index;
	public ExtendedIteratorEvents(Vector<Event> eventos) {
		this.eventos = eventos;
		index = 0;
	}
	@Override
	public boolean hasNext() {
		try {
			if (eventos.get(index) != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Event next() {
		index += 1;
		return eventos.get(index-1);
	}

	@Override
	public Event previous() {
		index -= 1;
		return eventos.get(index+1);
	}

	@Override
	public boolean hasPrevious() {
		return hasNext();
	}

	@Override
	public void goFirst() {
		index = 0;
	}

	@Override
	public void goLast() {
		index = eventos.size()-1;
	}

	
}
