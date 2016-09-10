package org.midnightas.advio;

import java.util.HashMap;

public class UserData {
	
	public HashMap<String, Object> objs = new HashMap<String, Object>();
	public String obj;
	
	public UserData(String obj) {
		this.obj = obj;
	}
	
	public UserData add(String key, Object value) {
		objs.remove(key);
		objs.put(key, value);
		return this;
	}
	
	public String toString() {
		return obj;
	}
	
	public boolean equals(Object o) {
		if(o instanceof String)
			return ((String) o).equalsIgnoreCase(obj);
		else
			return o == this;
	}
	
}
