package es.uned.portalreuniones.service.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.model.event.EventFile;

@SpringBootTest
public class EventFileServiceTest {

	private static String filesFolder = "/files/";

	@Autowired
	private EventFileService eventFileService;

	@Autowired
	private EventService eventService;

	@DisplayName("Guardado de un fichero en un evento")
	@Test
	public void saveTest() throws ElementNotFoundException, IOException {
		EventFile eventFileSaved = newFileEvent(eventService.findById(1l), "eventFile1.txt");
		assertNotNull(eventFileSaved);
	}

	@DisplayName("Recuperación de ficheros de un evento")
	@Test
	public void findByIdEventTest() throws ElementNotFoundException, IOException {
		Event event = eventService.findById(2l);
		newFileEvent(event, "eventFile1.txt");
		newFileEvent(event, "eventFile2.txt");
		newFileEvent(event, "eventFile3.txt");
		newFileEvent(event, "eventFile4.txt");
		newFileEvent(event, "eventFile5.txt");

		List<EventFile> eventFiles = eventFileService.findByIdEvent(2l);

		assertEquals(5, eventFiles.size());
	}

	@DisplayName("Recuperación de ficheros de la entity Event")
	@Test
	public void findFilesFromEvent() throws ElementNotFoundException, IOException {
		Event event = eventService.findById(4l);
		newFileEvent(event, "eventFile1.txt");
		newFileEvent(event, "eventFile2.txt");

		Event eventWithFiles = eventService.findById(4l);

		assertEquals(2, eventFileService.findByIdEvent(eventWithFiles.getId()).size());
	}

	@DisplayName("Borrado de un fichero")
	@Test
	public void deleteFileTest() throws ElementNotFoundException, IOException {
		Event event = eventService.findById(3l);
		newFileEvent(event, "eventFile1.txt");
		newFileEvent(event, "eventFile2.txt");
		EventFile eventFileToDelete = newFileEvent(event, "eventFile3.txt");
		List<EventFile> eventFilesBeforeDelete = eventFileService.findByIdEvent(3l);
		assertEquals(3, eventFilesBeforeDelete.size());
		eventFileService.delete(eventFileToDelete.getId());
		List<EventFile> eventFilesAfterDelete = eventFileService.findByIdEvent(3l);
		assertEquals(2, eventFilesAfterDelete.size());
	}

	private EventFile newFileEvent(Event event, String fileName) throws IOException {
		EventFile eventFile = new EventFile();
		eventFile.setEvent(event);
		eventFile.setFileName(fileName);
		eventFile.setFileType("text/plain");
		eventFile.setData(getDataFile(fileName));

		return eventFileService.save(eventFile);

	}

	private byte[] getDataFile(String fileName) throws IOException {
		String absolutePath = Paths.get("src", "test", "resources").toFile().getAbsolutePath().concat(filesFolder)
				.concat(fileName);

		return Files.readAllBytes(Paths.get(absolutePath));
	}

}
