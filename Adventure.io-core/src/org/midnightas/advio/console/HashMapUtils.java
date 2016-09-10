package org.midnightas.advio.console;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class HashMapUtils {

	public static <K, V> HashMap<K, V> removeByValue(HashMap<K, V> hm, V v) {
		for (Iterator<Entry<K, V>> it = hm.entrySet().iterator(); it.hasNext();) {
			Entry<K, V> e = it.next();
			if (e.getValue().equals(v)) {
				it.remove();
			}
		}
		return hm;
	}

}
