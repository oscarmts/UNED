package es.uned.portalreuniones.service.event;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.dto.EventPreferenceScheduleDTO;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.model.event.EventPreferenceSchedule;
import es.uned.portalreuniones.model.user.User;
import es.uned.portalreuniones.repository.event.EventPreferenceScheduleRepository;
import es.uned.portalreuniones.service.user.UserService;

@Service
public class EventPreferenceScheduleServiceImpl implements EventPreferenceScheduleService {

	@Autowired
	private EventPreferenceScheduleRepository eventPreferenceScheduleRepository;

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventInvitedService eventInvitedService;

	@Override
	public List<EventPreferenceSchedule> findAll() {
		return eventPreferenceScheduleRepository.findAll();
	}

	@Override
	public List<EventPreferenceSchedule> findByIdEvent(Long idEvent) {
		return eventPreferenceScheduleRepository.findByIdEvent(idEvent);
	}

	@Override
	public List<Integer> findHoursFromOwner(Long idEvent) {
		return eventPreferenceScheduleRepository.findHoursFromOwner(idEvent);
	}

	@Override
	public EventPreferenceSchedule findById(Long idEventPreferenceSchedule) throws ElementNotFoundException {
		Optional<EventPreferenceSchedule> eventPreferenceScheduleOpt = eventPreferenceScheduleRepository
				.findById(idEventPreferenceSchedule);
		if (eventPreferenceScheduleOpt.isPresent()) {
			return eventPreferenceScheduleOpt.get();
		} else {
			throw new ElementNotFoundException(
					"EventPreferenceSchedule not found with ID " + idEventPreferenceSchedule);
		}
	}

	@Override
	public List<EventPreferenceScheduleDTO> findUserWithHoursByIdEvent(Long idEvent) {
		List<EventPreferenceScheduleDTO> list = new ArrayList<EventPreferenceScheduleDTO>();

		List<User> users = findUsersWritePreferences(idEvent);
		for (User user : users) {
			EventPreferenceScheduleDTO dto = new EventPreferenceScheduleDTO();
			dto.setDate(findDateUserPreferences(idEvent, user.getId()));
			dto.setHoursList(findHoursFromUser(idEvent, user.getId()));
			dto.setUser(user);
			list.add(dto);
		}

		return list;
	}

	@Override
	public EventPreferenceSchedule save(EventPreferenceSchedule eventPreferenceSchedule) {
		return eventPreferenceScheduleRepository.save(eventPreferenceSchedule);
	}

	@Override
	public List<EventPreferenceSchedule> create(EventPreferenceScheduleDTO eventPreferenceScheduleDTO) {
		List<EventPreferenceSchedule> eventPreferenceScheduleList = new ArrayList<EventPreferenceSchedule>();
		Event event = null;
		User user = null;
		Date date = eventPreferenceScheduleDTO.getDate();
		// Recuperación de evento y usuario de base de datos
		try {
			event = eventService.findById(eventPreferenceScheduleDTO.getIdEvent());
			user = userService.findById(eventPreferenceScheduleDTO.getIdUser());
		} catch (ElementNotFoundException e) {

		}

		// Asignamos las horas que ha seleccionado el usuario
		for (int hour : eventPreferenceScheduleDTO.getHours()) {
			EventPreferenceSchedule eventPreferenceSchedule = new EventPreferenceSchedule();
			eventPreferenceSchedule.setEvent(event);
			eventPreferenceSchedule.setUser(user);
			eventPreferenceSchedule.setHour(hour);
			eventPreferenceSchedule.setDate(date);
			save(eventPreferenceSchedule);
			eventPreferenceScheduleList.add(eventPreferenceSchedule);
		}

		// Marcamos que el usuario ya ha votado sus preferencias de horario
		eventInvitedService.setUserVoted(eventPreferenceScheduleDTO.getIdEvent(), user.getId());
		return eventPreferenceScheduleList;
	}

	@Override
	public List<Integer> findHoursFromUser(Long idEvent, Long idUser) {
		return eventPreferenceScheduleRepository.findHoursFromUser(idEvent, idUser);
	}

	@Override
	public List<User> findUsersWritePreferences(Long idEvent) {
		return eventPreferenceScheduleRepository.findUsersWritePreferences(idEvent);
	}

	@Override
	public Date findDateUserPreferences(Long idEvent, Long idUser) {
		Date date = null;
		List<Date> dateList = eventPreferenceScheduleRepository.findDateUserPreferences(idEvent, idUser);
		if (!dateList.isEmpty()) {
			date = dateList.get(0);
		}
		return date;
	}

	@Override
	public List<Integer> findHoursFromDateAndEvent(Long idEvent, Date date) {
		return eventPreferenceScheduleRepository.findHoursFromDateAndEvent(idEvent, date);
	}

}
