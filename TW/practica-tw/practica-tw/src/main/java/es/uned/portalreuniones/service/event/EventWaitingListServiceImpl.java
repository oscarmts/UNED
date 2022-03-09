package es.uned.portalreuniones.service.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.model.event.EventWaitingList;
import es.uned.portalreuniones.model.user.User;
import es.uned.portalreuniones.repository.event.EventWaitingListRepository;
import es.uned.portalreuniones.service.user.UserService;

@Service
public class EventWaitingListServiceImpl implements EventWaitingListService {

	@Autowired
	private EventWaitingListRepository eventWaitingListRepository;

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventInvitedService eventInvitedService;

	@Override
	public List<EventWaitingList> findAll() {
		return eventWaitingListRepository.findAll();
	}

	@Override
	public EventWaitingList findById(Long idEventWaitingList) throws ElementNotFoundException {
		Optional<EventWaitingList> eventOpt = eventWaitingListRepository.findById(idEventWaitingList);
		if (eventOpt.isPresent()) {
			return eventOpt.get();
		} else {
			throw new ElementNotFoundException("EventWaitingList not found with ID " + idEventWaitingList);
		}
	}

	@Override
	public List<EventWaitingList> findByIdEvent(Long idEvent) {
		return eventWaitingListRepository.findByIdEvent(idEvent);
	}

	@Override
	public EventWaitingList save(EventWaitingList eventWaitingList) {
		return eventWaitingListRepository.save(eventWaitingList);
	}

	@Override
	public EventWaitingList transferFromEventInvited(Long idEvent, Long idUser) throws ElementNotFoundException {
		Event event = eventService.findById(idEvent);
		User user = userService.findById(idUser);
		eventInvitedService.deleteByIdEvent(idEvent, idUser);
		EventWaitingList eventWaitingList = new EventWaitingList();
		eventWaitingList.setEvent(event);
		eventWaitingList.setUser(user);
		return save(eventWaitingList);
	}

	@Override
	public void deleteByIdEvent(Long idEvent, Long idUser) {
		eventWaitingListRepository.deleteByIdEventAndIdUser(idEvent, idUser);
	}

}
