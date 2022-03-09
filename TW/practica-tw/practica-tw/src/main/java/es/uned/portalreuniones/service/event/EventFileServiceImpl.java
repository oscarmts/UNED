package es.uned.portalreuniones.service.event;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.EventFinishedException;
import es.uned.portalreuniones.model.dto.EventFileDTO;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.model.event.EventFile;
import es.uned.portalreuniones.repository.event.EventFileRepository;

@Service
public class EventFileServiceImpl implements EventFileService {

	@Autowired
	private EventFileRepository eventFileRepository;

	@Autowired
	private EventService eventService;

	@Override
	public EventFile findById(Long idFile) throws ElementNotFoundException {
		Optional<EventFile> eventFileOpt = eventFileRepository.findById(idFile);
		if (eventFileOpt.isPresent()) {
			return eventFileOpt.get();
		} else {
			throw new ElementNotFoundException("EventFile not found with ID " + idFile);
		}
	}

	@Override
	public List<EventFile> findByIdEvent(Long idEvent) {
		return eventFileRepository.findByIdEvent(idEvent);
	}

	@Override
	public EventFile save(EventFile eventFile) {
		return eventFileRepository.save(eventFile);
	}

	@Override
	public void delete(Long idFile) throws ElementNotFoundException {
		EventFile eventFile = findById(idFile);
		eventFileRepository.delete(eventFile);
	}

	@Override
	public EventFile addFileToEvent(EventFileDTO eventFileDTO) throws ElementNotFoundException, EventFinishedException {
		Event event = eventService.findById(eventFileDTO.getIdEvent());
		if (event.getFinished()) {
			throw new EventFinishedException("No puede adjuntar más ficheros, el evento ya finalizó");
		}
		MultipartFile file = eventFileDTO.getMultipartFile();
		EventFile eventFile = new EventFile();
		eventFile.setEvent(event);
		eventFile.setFileName(StringUtils.cleanPath(file.getOriginalFilename()));
		eventFile.setFileType(file.getContentType());
		try {
			eventFile.setData(file.getBytes());
		} catch (IOException e) {
			// TODO Manejar esta excepción
		}
		return save(eventFile);
	}

}
