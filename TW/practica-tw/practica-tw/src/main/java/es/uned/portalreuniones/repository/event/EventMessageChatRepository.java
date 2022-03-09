package es.uned.portalreuniones.repository.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.event.EventMessageChat;

@Repository
public interface EventMessageChatRepository extends JpaRepository<EventMessageChat, Long> {

	@Query("SELECT t FROM EventMessageChat t where t.event.id = :idEvent ORDER BY date desc")
	List<EventMessageChat> findByIdEvent(@Param("idEvent") Long idEvent);
}
