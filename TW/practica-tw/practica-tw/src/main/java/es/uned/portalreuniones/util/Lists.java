package es.uned.portalreuniones.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lists {

	public static Integer getElementMaxFrequency(List<Integer> list) {
		int max = 0;
		int curr = 0;
		Integer currKey = null;
		Set<Integer> unique = new HashSet<Integer>(list);
		for (Integer key : unique) {
			curr = Collections.frequency(list, key);

			if (max < curr) {
				max = curr;
				currKey = key;
			}
		}
		return currKey;
	}
}
