package es.uned.portalreuniones.util;

import java.sql.Date;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;

public class Maps {

	/**
	 * Recupera el value más alto del mapa
	 * 
	 * @param map
	 * @return
	 */
	public static Integer getMaxHour(Map<Integer, Integer> map) {
		if (map.size() > 0) {
			Stream<Map.Entry<Integer, Integer>> sorted = map.entrySet().stream()
					.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
			return sorted.iterator().next().getKey();
		} else {
			return 0;
		}
	}

	/**
	 * Recupera el value más alto del mapa
	 * 
	 * @param map
	 * @return
	 */
	public static Date getMaxDate(Map<Date, Integer> map) {
		if (map.size() > 0) {
			Stream<Map.Entry<Date, Integer>> sorted = map.entrySet().stream()
					.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()));
			return sorted.iterator().next().getKey();
		} else {
			return null;
		}
	}
}
