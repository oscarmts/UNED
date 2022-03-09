package es.uned.portalreuniones.repository.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.event.EventFile;

@Repository
public interface EventFileRepository extends JpaRepository<EventFile, Long> {

	@Query("SELECT t FROM EventFile t where t.event.id = :idEvent")
	List<EventFile> findByIdEvent(@Param("idEvent") Long idEvent);
}
