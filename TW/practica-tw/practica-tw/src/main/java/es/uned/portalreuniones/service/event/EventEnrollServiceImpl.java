package es.uned.portalreuniones.service.event;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.EventEnroll;
import es.uned.portalreuniones.model.event.EventPreferenceSchedule;
import es.uned.portalreuniones.repository.event.EventEnrollRepository;

@Service
public class EventEnrollServiceImpl implements EventEnrollService {

	@Autowired
	private EventEnrollRepository eventEnrollRepository;

	@Autowired
	private EventInvitedService eventInvitedService;

	@Autowired
	private EventPreferenceScheduleService eventPreferencesScheduleService;

	@Override
	public List<EventEnroll> findAll() {
		return eventEnrollRepository.findAll();
	}

	@Override
	public List<EventEnroll> findByIdUser(Long idUser) {
		return eventEnrollRepository.findByIdUser(idUser);
	}

	@Override
	public EventEnroll findById(Long idEventEnroll) throws ElementNotFoundException {
		Optional<EventEnroll> eventOpt = eventEnrollRepository.findById(idEventEnroll);
		if (eventOpt.isPresent()) {
			return eventOpt.get();
		} else {
			throw new ElementNotFoundException("EventEnroll not found with ID " + idEventEnroll);
		}
	}

	@Override
	public List<EventEnroll> findEventEnrollByIdEvent(Long idEvent) {
		return eventEnrollRepository.findByIdEvent(idEvent);
	}

	@Override
	public EventEnroll save(EventEnroll eventEnroll) {
		return eventEnrollRepository.save(eventEnroll);
	}

	@Override
	public List<EventEnroll> enrrolUsersToEvent(Long idEvent, Date date, Integer hour) {
		List<EventEnroll> eventEnrollList = new ArrayList<EventEnroll>();
		// Preferencias de horario de los usuarios
		List<EventPreferenceSchedule> preferencesSchedule = eventPreferencesScheduleService.findByIdEvent(idEvent);

		// Descartamos a los usuarios que no coinciden en el día
		for (EventPreferenceSchedule eventPreference : preferencesSchedule) {
			if (!eventPreference.getDate().equals(date)) {
				// Si no coinciden en la hora, ya les descartamos
				eventInvitedService.setUserEnrolled(idEvent, eventPreference.getUser().getId(), false);
			} else {
				// Coincide en el día
				// Inscribimos a los usuarios que les coincide la hora
				if (eventPreference.getHour().equals(hour)) {
					eventInvitedService.setUserEnrolled(idEvent, eventPreference.getUser().getId(), true);

					// Inscribimos a estos usuarios al evento
					EventEnroll eventEnroll = new EventEnroll();
					eventEnroll.setEvent(eventPreference.getEvent());
					eventEnroll.setUser(eventPreference.getUser());
					save(eventEnroll);
					eventEnrollList.add(eventEnroll);
				}

			}
		}
		return eventEnrollList;
	}

	@Override
	public void delete(Long idEventEnroll) {
		try {
			eventEnrollRepository.delete(findById(idEventEnroll));
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void deleteByIdEvent(Long idEvent, Long idUser) {
		eventEnrollRepository.deleteByIdEventAndIdUser(idEvent, idUser);
	}

}
