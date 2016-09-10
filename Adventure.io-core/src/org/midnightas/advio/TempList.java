package org.midnightas.advio;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class TempList {
	
	public List list;
	
	public TempList(List list) {
		this.list = list;
	}

	@SuppressWarnings("unchecked")
	public TempList exclude(Class<?> clazz) {
		List n = new LinkedList(list);
		for(Object o : list)
			if(o.getClass().equals(clazz))
				n.remove(o);
		list = n;
		return this;
	}
	
}
