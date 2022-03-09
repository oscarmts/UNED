package es.uned.portareuniones.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.uned.portalreuniones.util.Maps;

public class MapsTest {

	@DisplayName("Recuperar la hora más repetida")
	@Test
	public void getMaxHourTest() {
		Map<Integer, Integer> countHours = new HashMap<Integer, Integer>();
		countHours.put(10, 2);
		countHours.put(11, 3);
		countHours.put(12, 5);
		countHours.put(13, 1);
		countHours.put(13, 3);

		assertEquals(12, Maps.getMaxHour(countHours));

		Map<Integer, Integer> countHours2 = new HashMap<Integer, Integer>();
		countHours2.put(1, 6);
		countHours2.put(2, 4);
		countHours2.put(3, 5);
		countHours2.put(15, 12);
		countHours2.put(8, 13);
		countHours2.put(4, 3);
		countHours2.put(5, 2);
		countHours2.put(6, 3);

		assertEquals(8, Maps.getMaxHour(countHours2));

		Map<Integer, Integer> countHours3 = new HashMap<Integer, Integer>();
		countHours3.put(1, 1);
		countHours3.put(2, 2);
		countHours3.put(3, 1);
		countHours3.put(4, 2);

		assertEquals(2, Maps.getMaxHour(countHours3));

	}
}
