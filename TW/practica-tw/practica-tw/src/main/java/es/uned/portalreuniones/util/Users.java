package es.uned.portalreuniones.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import es.uned.portalreuniones.model.dto.EventDTO;

public class Users {

	public static Integer[] refreshUsersSelected(EventDTO eventDTO) {
		return refresh(eventDTO.getUsers(), eventDTO.getUsersSelected());
	}

	public static Integer[] refreshWaitingList(EventDTO eventDTO) {
		return refresh(eventDTO.getWaitingList(), eventDTO.getWaitingListSelected());
	}

	private static Integer[] refresh(Integer[] array, Integer[] arraySelected) {
		Set<Integer> usersTotals = new HashSet<Integer>();

		if (arraySelected != null) {
			usersTotals.addAll(Arrays.asList(arraySelected));
		}
		if (array != null) {
			usersTotals.addAll(Arrays.asList(array));
		}
		Integer[] usersArr = new Integer[usersTotals.size()];
		int count = 0;
		for (Integer user : usersTotals) {
			usersArr[count] = user;
			count++;
		}
		return usersArr;
	}

}
