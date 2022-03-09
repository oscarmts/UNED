package es.uned.portalreuniones.service.event;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.model.event.EventInvited;
import es.uned.portalreuniones.model.user.User;
import es.uned.portalreuniones.repository.event.EventInvitedRepository;
import es.uned.portalreuniones.service.user.UserService;

@Service
public class EventInvitedServiceImpl implements EventInvitedService {

	@Autowired
	private EventInvitedRepository eventInvitedRepository;

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventWaitingListService eventWaitingListService;

	@Override
	public List<EventInvited> findEventInvitedByIdUser(Long idUser) {
		return eventInvitedRepository.findEventInvitedByIdUser(idUser);
	}

	@Override
	public List<EventInvited> findEventInvitedByIdEvent(Long idEvent) {
		return eventInvitedRepository.findEventInvitedByIdEvent(idEvent);
	}

	@Override
	public List<EventInvited> findEventInvitedWithoutVoteByIdEvent(Long idEvent) {
		return eventInvitedRepository.findEventInvitedWithoutVoteByIdEvent(idEvent);
	}

	@Override
	public List<EventInvited> findEventInvitedFinishedByIdUser(Long idUser) {
		return eventInvitedRepository.findEventInvitedFinishedByIdUser(idUser);
	}

	@Override
	public EventInvited save(EventInvited eventInvited) {
		return eventInvitedRepository.save(eventInvited);
	}

	@Override
	public void deleteByIdEvent(Long idEvent, Long idUser) {
		eventInvitedRepository.deleteByIdEventAndIdUser(idEvent, idUser);
	}

	@Override
	public EventInvited findEventInvitedByIdEventAndIdUser(Long idEvent, Long idUser) {
		return eventInvitedRepository.findEventInvitedByIdEventAndIdUser(idEvent, idUser);
	}

	@Override
	public EventInvited transferFromEventWaitingList(Long idEvent, Long idUser) throws ElementNotFoundException {
		Event event = eventService.findById(idEvent);
		User user = userService.findById(idUser);
		eventWaitingListService.deleteByIdEvent(idEvent, idUser);
		EventInvited eventInvited = new EventInvited();
		eventInvited.setEvent(event);
		eventInvited.setUser(user);
		eventInvited.setEnrolled(false);
		eventInvited.setVoted(false);
		return save(eventInvited);
	}

	@Override
	public EventInvited setUserVoted(Long idEvent, Long idUser) {
		EventInvited eventInvited = findEventInvitedByIdEventAndIdUser(idEvent, idUser);
		eventInvited.setVoted(true);
		return save(eventInvited);
	}

	@Override
	public EventInvited setUserEnrolled(Long idEvent, Long idUser, boolean enrolled) {
		EventInvited eventInvited = findEventInvitedByIdEventAndIdUser(idEvent, idUser);
		eventInvited.setEnrolled(enrolled);
		return save(eventInvited);
	}

}
