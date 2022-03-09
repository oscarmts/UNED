package es.uned.portalreuniones.service.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.EventPreferenceDateSchedule;
import es.uned.portalreuniones.repository.event.EventPreferenceDateScheduleRepository;

@Service
public class EventPreferenceDateScheduleServiceImpl implements EventPreferenceDateScheduleService {

	@Autowired
	private EventPreferenceDateScheduleRepository eventPreferenceDateScheduleRepository;

	@Override
	public EventPreferenceDateSchedule findById(Long id) throws ElementNotFoundException {
		Optional<EventPreferenceDateSchedule> eventPreferenceDateOpt = eventPreferenceDateScheduleRepository
				.findById(id);
		if (eventPreferenceDateOpt.isPresent()) {
			return eventPreferenceDateOpt.get();
		} else {
			throw new ElementNotFoundException("EventPreferenceDateSchedule not found with id: " + id);
		}

	}

	@Override
	public List<EventPreferenceDateSchedule> findByIdEvent(Long idEvent) {
		return eventPreferenceDateScheduleRepository.findByIdEvent(idEvent);
	}

	@Override
	public EventPreferenceDateSchedule save(EventPreferenceDateSchedule eventPreferenceDateSchedule) {
		return eventPreferenceDateScheduleRepository.save(eventPreferenceDateSchedule);
	}

}
