package es.uned.portalreuniones.repository.event;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.uned.portalreuniones.model.event.EventInvited;

@Repository
public interface EventInvitedRepository extends JpaRepository<EventInvited, Long> {

	@Query("SELECT t FROM EventInvited t where t.user.id = :idUser and t.voted = false")
	List<EventInvited> findEventInvitedByIdUser(@Param("idUser") Long idUser);

	@Query("SELECT t FROM EventInvited t where t.event.id = :idEvent")
	List<EventInvited> findEventInvitedByIdEvent(@Param("idEvent") Long idEvent);

	@Query("SELECT t FROM EventInvited t where t.event.id = :idEvent and t.voted = false")
	List<EventInvited> findEventInvitedWithoutVoteByIdEvent(@Param("idEvent") Long idEvent);

	@Query("SELECT t FROM EventInvited t where t.user.id = :idUser and t.event.finished = true")
	List<EventInvited> findEventInvitedFinishedByIdUser(@Param("idUser") Long idUser);

	@Query("SELECT t FROM EventInvited t where t.event.id = :idEvent and t.user.id = :idUser")
	EventInvited findEventInvitedByIdEventAndIdUser(@Param("idEvent") Long idEvent, @Param("idUser") Long idUser);

	@Transactional
	@Modifying
	@Query("DELETE EventInvited t where t.event.id = :idEvent AND t.user.id=:idUser")
	void deleteByIdEventAndIdUser(@Param("idEvent") Long idEvent, @Param("idUser") Long idUser);
}
