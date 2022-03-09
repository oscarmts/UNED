package es.uned.portalreuniones.service.event;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.EventFinishedException;
import es.uned.portalreuniones.exception.ForbiddenException;
import es.uned.portalreuniones.model.dto.EventDTO;
import es.uned.portalreuniones.model.dto.EventDateDTO;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.model.event.EventEnroll;
import es.uned.portalreuniones.model.event.EventInvited;
import es.uned.portalreuniones.model.event.EventPreferenceDateSchedule;
import es.uned.portalreuniones.model.event.EventPreferenceSchedule;
import es.uned.portalreuniones.model.event.EventWaitingList;
import es.uned.portalreuniones.model.user.User;
import es.uned.portalreuniones.model.user.UserNotRegistered;
import es.uned.portalreuniones.repository.event.EventRepository;
import es.uned.portalreuniones.service.user.UserNotRegisteredService;
import es.uned.portalreuniones.service.user.UserService;
import es.uned.portalreuniones.util.Dates;
import es.uned.portalreuniones.util.Lists;
import es.uned.portalreuniones.util.Maps;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private EventPreferenceScheduleService eventPreferenScheduleService;

	@Autowired
	private EventPreferenceDateScheduleService eventPreferenceDateScheduleService;

	@Autowired
	private EventInvitedService eventInvitedService;

	@Autowired
	private EventEnrollService eventEnrollService;

	@Autowired
	private UserNotRegisteredService userNotRegisteredService;

	@Autowired
	private EventWaitingListService eventWaitingListService;

	@Override
	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public Event findById(Long idEvent) throws ElementNotFoundException {
		Optional<Event> eventOpt = eventRepository.findById(idEvent);
		if (eventOpt.isPresent()) {
			return eventOpt.get();
		} else {
			throw new ElementNotFoundException("Event not found with ID " + idEvent);
		}
	}

	@Override
	public Event findByToken(String token) throws ElementNotFoundException {
		Event event = eventRepository.findByToken(token);
		if (event != null) {
			return event;
		} else {
			throw new ElementNotFoundException("Event not found with token " + token);
		}
	}

	@Override
	public List<Event> findByIdRoom(Long idRoom) {
		return eventRepository.findByIdRoom(idRoom);
	}

	@Override
	public List<Event> findByIdOwner(Long idOwner) {
		return eventRepository.findByIdOwner(idOwner);
	}

	@Override
	public List<Event> findPendingEventByIdOwner(Long idOwner) {
		return eventRepository.findPendingEventByIdOwner(idOwner);
	}

	@Override
	public List<Event> findFinishedByIdOwner(Long idOwner) {
		return eventRepository.findFinishedByIdOwner(idOwner);
	}

	@Override
	public EventDateDTO configureDateEvent(Long idEvent) {
		List<User> users = eventPreferenScheduleService.findUsersWritePreferences(idEvent);
		Map<Date, Integer> countDates = new HashMap<Date, Integer>();
		for (User user : users) {
			Date date = eventPreferenScheduleService.findDateUserPreferences(idEvent, user.getId());
			if (countDates.containsKey(date)) {
				countDates.put(date, countDates.get(date) + 1);
			} else {
				countDates.put(date, 1);
			}
		}

		// ¿Cuál es la fecha más votada?
		Date maxDate = Maps.getMaxDate(countDates);

		// Ya sabemos la fecha del evento más votada, ahora toca ir a por la hora
		List<Integer> hoursList = eventPreferenScheduleService.findHoursFromDateAndEvent(idEvent, maxDate);

		Integer maxHour = Lists.getElementMaxFrequency(hoursList);

		return new EventDateDTO(maxDate, maxHour);
	}

	@Override
	public Event save(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public Event createEvent(EventDTO eventDTO) {
		Event event = new Event();
		event.setTopic(eventDTO.getTopic());
		event.setOwner(eventDTO.getOwner());
		event.setRoom(eventDTO.getRoom());
		event.setConfigured(false);
		event.setFinished(false);
		event.setToken(UUID.randomUUID().toString());
		Event eventPersist = save(event);
		Integer[] users = eventDTO.getUsers();
		Integer[] waitingList = eventDTO.getWaitingList();
		Date[] dates = eventDTO.getDates();
		int[] hours = eventDTO.getHours();
		try {
			// Guardamos los usuarios del evento
			if (users != null) {
				for (int i = 0; i < users.length; i++) {
					// Los invitamos para que sepan que tienen un evento que aceptar
					EventInvited eventInvited = new EventInvited();
					eventInvited.setEvent(eventPersist);
					eventInvited.setUser(userService.findById(Long.valueOf(users[i])));
					eventInvited.setVoted(false);
					eventInvited.setEnrolled(false);
					eventInvitedService.save(eventInvited);

				}
			}
			if (waitingList != null) {
				for (int i = 0; i < waitingList.length; i++) {
					// Haremos una lista de espera de usuarios
					EventWaitingList eventWaitingList = new EventWaitingList();
					eventWaitingList.setEvent(eventPersist);
					eventWaitingList.setUser(userService.findById(Long.valueOf(waitingList[i])));
					eventWaitingListService.save(eventWaitingList);
				}
			}
			// Guardamos las fechas seleccionadas
			if (dates != null) {
				for (int i = 0; i < dates.length; i++) {
					EventPreferenceDateSchedule eventPreferenceDateSchedule = new EventPreferenceDateSchedule();
					eventPreferenceDateSchedule.setDate(dates[i]);
					eventPreferenceDateSchedule.setEvent(eventPersist);
					eventPreferenceDateScheduleService.save(eventPreferenceDateSchedule);
				}
			}

			// Guardamos las horas seleccionadas
			if (hours != null) {
				for (int i = 0; i < hours.length; i++) {
					EventPreferenceSchedule eventPreferenceSchedule = new EventPreferenceSchedule();
					eventPreferenceSchedule.setHour(hours[i]);
					eventPreferenceSchedule.setEvent(eventPersist);
					eventPreferenceSchedule.setUser(userService.findById(eventDTO.getOwner().getId()));
					eventPreferenceSchedule.setOwnerHours(true);
					eventPreferenScheduleService.save(eventPreferenceSchedule);
				}
			}
			return findById(eventPersist.getId());
		} catch (ElementNotFoundException e) {
			// Todo veremos cómo gestionar esta excepción
		}
		return null;
	}

	@Override
	public Event update(Event event) throws ElementNotFoundException {
		Event eventPersist = findById(event.getId());
		if (event.getRoom() != null) {
			eventPersist.setRoom(event.getRoom());
		}
		if (event.getDate() != null) {
			eventPersist.setDate(event.getDate());
		}
		if (event.getHour() != null) {
			eventPersist.setHour(event.getHour());
		}
		if (event.getTopic() != null) {
			eventPersist.setTopic(event.getTopic());
		}
		return save(eventPersist);

	}

	@Override
	public Event configureEventWithDate(Long idEvent, Date date, Integer hour) {
		Event eventUpdated = null;
		try {
			Event eventPersist = findById(idEvent);
			eventPersist.setDate(date);
			eventPersist.setHour(hour);
			eventPersist.setConfigured(true);

			eventUpdated = save(eventPersist);

			// Inscribimos a los usuarios que encajan con la fecha y hora al evento
			eventEnrollService.enrrolUsersToEvent(idEvent, date, hour);

		} catch (ElementNotFoundException e) {
			// TODO Veremos cómo tratar a estas excepciones
		}
		return eventUpdated;
	}

	@Override
	public Event finishEvent(Long idEvent) {
		Event eventFinished = null;
		try {
			eventFinished = findById(idEvent);
			eventFinished.setFinished(true);
			save(eventFinished);
		} catch (ElementNotFoundException e) {
			// TODO Ver como controlar esto
		}
		return eventFinished;
	}

	@Override
	public void delete(Long idEvent) {
		try {
			Event event = findById(idEvent);
			eventRepository.delete(event);
		} catch (ElementNotFoundException e) {
			// TODO Veremos cómo gestionar esta excepción
		}

	}

	@Override
	public boolean checkPermission(Long idEvent, Long idUser, boolean withToken)
			throws ForbiddenException, EventFinishedException {
		try {
			Event event = findById(idEvent);
			if (event.getFinished()) {
				throw new EventFinishedException("El evento ya ha finalizado.");
			}
			if (!event.getConfigured()) {
				throw new ForbiddenException("El evento está todavía en proceso de votación.");
			}

			if (checkUser(event, idUser, withToken)) {
				boolean isDatePermitted = false;
				// Es un usuario que puede entrar al evento, compramos si es hora
				if (Dates.isValidDate(event.getDate())) {
					// Estamos en el día del evento, ¿faltan cinco minutos o menos?
					if (Dates.isValidTime(event.getHour())) {
						isDatePermitted = true;
					}
				}
				if (!isDatePermitted) {
					Integer finishHour = event.getHour() + 1;
					throw new ForbiddenException(
							"El evento no comenzará hasta cinco minutos antes de las " + event.getHourFormated()
									+ " del " + event.getDateFormated() + " y finalizará a las " + finishHour + ":00");
				}
			}

		} catch (ElementNotFoundException e) {
			throw new ForbiddenException("No existe este evento");
		}
		return false;
	}

	@Override
	public boolean checkDetailPermission(Long idEvent, Long idUser, boolean withToken) throws ForbiddenException {
		try {
			Event event = findById(idEvent);
			boolean isUserPermited = false;
			try {
				isUserPermited = checkUser(event, idUser, withToken);
			} catch (ForbiddenException e) {
				isUserPermited = isUserInvitedPermited(idEvent, idUser);
			}
			return isUserPermited;
		} catch (ElementNotFoundException e) {
			throw new ForbiddenException("No existe este evento");
		}
	}

	private boolean checkUser(Event event, Long idUser, boolean withToken) throws ForbiddenException {
		if (event.getOwner().getId().equals(idUser)) {
			// Es el creador del evento, tiene permiso
			return true;
		}
		boolean isUserPermited = false;
		Long idEvent = event.getId();
		if (withToken) {
			isUserPermited = isUserUnregisteredPermited(idEvent, idUser);
		} else {
			// Si es el creador del evento tiene permiso para entrar, si no, comprobamos
			// invitación
			isUserPermited = event.getOwner().getId().equals(idUser) || isUserPermited(idEvent, idUser);
		}
		return isUserPermited;
	}

	private boolean isUserPermited(Long idEvent, Long idUser) throws ForbiddenException {
		boolean isUserPermitted = false;

		List<EventEnroll> eventEnrolls = eventEnrollService.findEventEnrollByIdEvent(idEvent);
		// Comprobamos que el usuario es un usuario registrado y que está suscrito al
		// evento
		for (EventEnroll eventEnroll : eventEnrolls) {
			if (eventEnroll.getUser().getId().equals(idUser)) {
				isUserPermitted = true;
			}
		}

		if (!isUserPermitted) {
			throw new ForbiddenException("Lo lamentamos, pero no tiene permiso para acceder a este evento");
		}
		return isUserPermitted;
	}

	private boolean isUserUnregisteredPermited(Long idEvent, Long idUser) throws ForbiddenException {
		boolean isUserPermitted = false;
		// No es un usuario registrado suscrito, comprobamos si es un usuario no
		// registrado
		List<UserNotRegistered> usersNotRegistered = userNotRegisteredService.findByIdEvent(idEvent);
		for (UserNotRegistered userNotRegistered : usersNotRegistered) {
			if (userNotRegistered.getId().equals(idUser)) {
				isUserPermitted = true;
			}
		}
		if (!isUserPermitted) {
			throw new ForbiddenException("Lo lamentamos, pero no tiene permiso para acceder a este evento");
		}
		return isUserPermitted;
	}

	private boolean isUserInvitedPermited(Long idEvent, Long idUser) throws ForbiddenException {
		boolean isUserPermitted = false;
		List<EventInvited> eventInvitedList = eventInvitedService.findEventInvitedByIdEvent(idEvent);
		for (EventInvited eventInvited : eventInvitedList) {
			if (eventInvited.getUser().getId().equals(idUser)) {
				isUserPermitted = true;
			}
		}
		if (!isUserPermitted) {
			throw new ForbiddenException("Lo lamentamos, pero no tiene permiso para acceder a este evento");
		}
		return isUserPermitted;
	}

}
