package es.uned.portalreuniones.service.event;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.ForbiddenException;
import es.uned.portalreuniones.model.dto.ChatMessageDTO;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.model.event.EventMessageChat;
import es.uned.portalreuniones.repository.event.EventMessageChatRepository;
import es.uned.portalreuniones.service.user.UserService;
import es.uned.portalreuniones.util.Dates;

@Service
public class EventMessageChatServiceImpl implements EventMessageChatService {

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventMessageChatRepository eventMessageChatRepository;

	@Override
	public List<EventMessageChat> findByIdEvent(Long idEvent) {

		return eventMessageChatRepository.findByIdEvent(idEvent);
	}

	@Override
	public EventMessageChat findById(Long idEventMessageChat) throws ElementNotFoundException {
		Optional<EventMessageChat> eventMessageChatOpt = eventMessageChatRepository.findById(idEventMessageChat);
		if (eventMessageChatOpt.isPresent()) {
			return eventMessageChatOpt.get();
		} else {
			throw new ElementNotFoundException("Message Chat not found with id: " + idEventMessageChat);
		}
	}

	@Override
	public EventMessageChat saveMessage(ChatMessageDTO chatMessageDTO) throws ForbiddenException {
		EventMessageChat eventMessageChat = new EventMessageChat();
		try {
			Event event = eventService.findById(chatMessageDTO.getIdEvent());
			if (event.getFinished()) {
				throw new ForbiddenException("No puede guardar más mensajes, el evento finalizó");
			}
			eventMessageChat.setDate(Dates.now());
			eventMessageChat.setMessage(chatMessageDTO.getContent());
			eventMessageChat.setEvent(event);
			eventMessageChat.setUser(userService.findByNickName(chatMessageDTO.getSender()));
		} catch (ElementNotFoundException e) {
			// TODO Ya veremos cómo manejar esto

		}
		return save(eventMessageChat);
	}

	@Override
	public EventMessageChat save(EventMessageChat eventMessageChat) {
		return eventMessageChatRepository.save(eventMessageChat);
	}

	@Override
	public void delete(Long idMessage) {
		try {
			eventMessageChatRepository.delete(findById(idMessage));
		} catch (ElementNotFoundException e) {
			// TODO Ya veremos cómo trabajamos con esto
		}

	}

}
