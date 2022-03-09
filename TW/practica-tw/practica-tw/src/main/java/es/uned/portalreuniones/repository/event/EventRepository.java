package es.uned.portalreuniones.repository.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.event.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

	@Query("SELECT t FROM Event t where t.room.id = :idRoom")
	List<Event> findByIdRoom(@Param("idRoom") Long idRoom);

	@Query("SELECT t FROM Event t where t.owner.id = :idOwner and t.configured = true and t.finished = false")
	List<Event> findByIdOwner(@Param("idOwner") Long idOwner);

	@Query("SELECT t FROM Event t where t.owner.id = :idOwner and t.configured = true and t.finished = true")
	List<Event> findFinishedByIdOwner(@Param("idOwner") Long idOwner);

	@Query("SELECT t FROM Event t where t.owner.id = :idOwner and t.configured = false")
	List<Event> findPendingEventByIdOwner(@Param("idOwner") Long idOwner);

	@Query("SELECT t FROM Event t where t.token = :token")
	Event findByToken(@Param("token") String token);

}
